CREATE OR REPLACE FUNCTION calculateGlobalScore2()
  RETURNS void AS $$
  BEGIN 
    UPDATE appuser AS au SET standing_score = tmp.summ, problems_solved = tmp.countt 
    FROM v_global_ranking AS tmp(uid, summ, countt)
    WHERE tmp.uid = au.id;
  END;
$$ LANGUAGE plpgsql;

---------------------------------- RANKING CALCULATORS ---------------------------------------------
CREATE OR REPLACE FUNCTION updateRunScoresPrivateMax(problem_id int)
  RETURNS void as $$
  BEGIN
    update run as r set run_score = c.score from(
        select run_id, sum(ratio) from 
        (
          select vrd_run_id as run_id, 100*(vrd_instance_score)/(vrd_max_score)/vrd_instance_number_private as ratio from v_ranking_data
          where vrd_instance_score > 0
          and vrd_visibility = 1
          and vrd_problem_definition_id = problem_id
        ) as tmp group by run_id
      ) as c(run_id, score) where c.run_id = r.id;
  END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION updateRunScoresPublicMax(problem_id int)
  RETURNS void as $$
  BEGIN
    update run as r set run_score_public = c.score from(
        select run_id, sum(ratio) from 
        (
          select vrd_run_id as run_id, 100*(vrd_instance_score)/(vrd_max_score)/vrd_instance_number_public as ratio from v_ranking_data
          where vrd_instance_score > 0
          and vrd_visibility = 0
          and vrd_problem_definition_id = problem_id
        ) as tmp group by run_id
      ) as c(run_id, score) where c.run_id = r.id;
  END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION updateRunScoresPrivateMin(problem_id int)
  RETURNS void as $$
  BEGIN
    update run as r set run_score = c.score from(
        select run_id, sum(ratio) from 
        (
          select vrd_run_id as run_id, 100*(vrd_min_score)/(vrd_instance_score)/vrd_instance_number_private as ratio from v_ranking_data
          where vrd_instance_score > 0
          and vrd_visibility = 1
          and vrd_problem_definition_id = problem_id
        ) as tmp group by run_id
      ) as c(run_id, score) where c.run_id = r.id;
  END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION updateRunScoresPublicMin(problem_id int)
  RETURNS void as $$
  BEGIN
    update run as r set run_score_public = c.score from(
        select run_id, sum(ratio) from 
        (
          select vrd_run_id as run_id, 100*(vrd_min_score)/(vrd_instance_score)/vrd_instance_number_public as ratio from v_ranking_data
          where vrd_instance_score > 0
          and vrd_visibility = 0
          and vrd_problem_definition_id = problem_id
        ) as tmp group by run_id
      ) as c(run_id, score) where c.run_id = r.id;
  END;
$$ LANGUAGE plpgsql;
---------------------------------- END OF RANKING CALCULATORS ---------------------------------------------


CREATE OR REPLACE FUNCTION updateRunScores(var_problem_id int, var_visibility int)
  RETURNS void as $$
  DECLARE
    var_ranking_type text;
  BEGIN
    SELECT problem_definition.ranking_type INTO var_ranking_type FROM problem_definition where id = var_problem_id;
    
    IF var_ranking_type = 'max' THEN
        IF var_visibility = 0 THEN
            PERFORM updateRunScoresPublicMax(var_problem_id);
        ELSEIF var_visibility = 1 THEN
            PERFORM updateRunScoresPrivateMax(var_problem_id);
        ELSEIF var_visibility is NULL THEN
            PERFORM updateRunScoresPrivateMax(var_problem_id);
        ELSE
            raise notice 'Something weird occured';
        END IF;
    ELSIF (var_ranking_type = 'min' or var_ranking_type IS NULL) THEN
        IF var_visibility = 0 THEN
            PERFORM updateRunScoresPublicMin(var_problem_id);
        ELSEIF var_visibility = 1 THEN
            PERFORM updateRunScoresPrivateMin(var_problem_id);
        ELSEIF var_visibility is NULL THEN
            PERFORM updateRunScoresPrivateMin(var_problem_id);
        ELSE
            raise notice 'Something weird occured';
        END IF;
    ELSE
      raise notice 'Something weird occured';
    END IF;
    
  END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION updateContestStanding(current_run_id int, current_problem_id int, current_user_id int, current_contest_id int) RETURNS VOID AS $$
	DECLARE
		problem_in_contest BOOLEAN;
		current_run run;
	BEGIN
		SELECT * INTO current_run FROM run WHERE id = current_run_id;
		SELECT EXISTS(
			SELECT 1 FROM contest_problem_definition 
				WHERE contest_id = $4 AND
				problem_definition_id = $2
		) INTO problem_in_contest;
		
		IF problem_in_contest THEN
			IF NOT EXISTS(
				SELECT 1 FROM contest_standing
					WHERE user_id = $3 AND
					problem_id = $2 AND
					contest_id = $4
			) THEN
				INSERT INTO contest_standing
					VALUES((SELECT nextval('contest_standing_sequence')), $4, $2, $1, $3, current_run.run_score, current_run.public_run_score);
			END IF;
			
			UPDATE contest_standing AS cs
				SET score = c.score, run_id = c.run_id, public_score = c.public_score
					FROM standing_runs_scores as c
						WHERE cs.user_id = c.user_id AND cs.problem_id = c.problem_id;
		END IF;
		
		RETURN;
	END; 
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION updateContestsStandings(current_run_id int, problem_id int, user_id int) RETURNS VOID AS $$
	DECLARE
		current_run run;
		con contest;
	BEGIN
		SELECT * INTO current_run FROM run WHERE id = $1;
		FOR con IN 
			SELECT id FROM contest WHERE
				contest_end >= current_run.submit_date AND
				start <= current_run.submit_date
		LOOP
			PERFORM updateContestStanding($1, $2, $3, con.id);
		END LOOP;
		RETURN;
	END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION privateRankingTriggerFunction() RETURNS trigger AS $$
  BEGIN
    PERFORM updateRunScores(NEW.problem_definition_id, 1);
	PERFORM calculateGlobalScore2();  -- TODO move to other trigger
	PERFORM updateContestsStandings(NEW.id, NEW.problem_definition_id, NEW.user_id);   -- TODO move to other trigger
    RETURN NULL;
  END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION publicRankingTriggerFunction() RETURNS trigger AS $$
  BEGIN
    PERFORM updateRunScores(NEW.problem_definition_id, 0);
	PERFORM calculateGlobalScore2();  -- TODO move to other trigger
	PERFORM updateContestsStandings(NEW.id, NEW.problem_definition_id, NEW.user_id);  -- TODO move to other trigger
    RETURN NULL;
  END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER rankingTriggerPrivate AFTER UPDATE OF global_clock_ticks ON run FOR EACH ROW EXECUTE PROCEDURE privateRankingTriggerFunction();
CREATE TRIGGER rankingTriggerPublic AFTER UPDATE OF global_clock_ticks_public ON run FOR EACH ROW EXECUTE PROCEDURE publicRankingTriggerFunction();