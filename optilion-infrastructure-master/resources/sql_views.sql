CREATE OR REPLACE VIEW v_recent_runs AS
SELECT DISTINCT ON (run.user_id, run.problem_definition_id)
    run.run_status                            AS vrr_run_status,
    run.id                                    AS vrr_run_id,
    run.user_id                               AS vrr_user_id,
    run.problem_definition_id                 AS vrr_problem_definition_id,
    run.run_score                             AS vrr_run_score,
    run.run_score_public                      AS vrr_run_score_public,
    problem_definition.problem_visibility     AS vrr_problem_definition_visibility
  FROM run
  LEFT JOIN problem_definition ON run.problem_definition_id = problem_definition.id
  WHERE run.run_status::text = 'Accepted'::text OR run.run_status::text ~~ '%Wrong Answer%'::text OR run.run_status::text = 'Runtime Error'::text
  ORDER BY run.user_id, run.problem_definition_id, run.id DESC;

CREATE VIEW v_recent_instances AS
SELECT
    v_recent_runs.vrr_run_status                AS vri_run_status,
    v_recent_runs.vrr_run_id                    AS vri_run_id,
    v_recent_runs.vrr_user_id                   AS vri_user_id,
    opttest.problem_instance_id                 AS vri_problem_instance_id,
    v_recent_runs.vrr_problem_definition_id     AS vri_problem_definition_id,
    v_recent_runs.vrr_run_score                 AS vri_run_score,
    opttest.score                               AS vri_instance_score,
    problem_instance.instance_visibility        AS vri_visibility
  FROM v_recent_runs
  LEFT JOIN opttest ON opttest.run_id = v_recent_runs.vrr_run_id
  LEFT JOIN problem_instance ON opttest.problem_instance_id = problem_instance.id;

CREATE VIEW v_recent_max AS
SELECT
    vri_problem_definition_id   AS vmax_problem_definition_id,
    vri_problem_instance_id     AS vmax_problem_instance_id,
    max(vri_instance_score)     AS vmax_max_score
  FROM v_recent_instances
  WHERE vri_instance_score > 0::double precision
  GROUP BY vri_problem_definition_id, vri_problem_instance_id;

CREATE VIEW v_recent_min AS
SELECT
    vri_problem_definition_id   AS vmin_problem_definition_id,
    vri_problem_instance_id     AS vmin_problem_instance_id,
    min(vri_instance_score)     AS vmin_min_score
  FROM v_recent_instances
  WHERE vri_instance_score > 0::double precision
  GROUP BY vri_problem_definition_id, vri_problem_instance_id;

CREATE VIEW v_instance_number AS
SELECT
    problem_instance.problem_definition_id      AS vin_problem_definition_id,
    count(problem_instance.id)                  AS vin_instance_number
  FROM problem_instance
  GROUP BY problem_instance.problem_definition_id;
  
CREATE VIEW v_instance_number_public AS
SELECT
    problem_instance.problem_definition_id      AS vinpu_problem_definition_id,
    count(problem_instance.id)                  AS vinpu_instance_number
  FROM problem_instance
  WHERE instance_visibility = 0
  GROUP BY problem_instance.problem_definition_id;
  
CREATE VIEW v_instance_number_private AS
SELECT
    problem_instance.problem_definition_id      AS vinpr_problem_definition_id,
    count(problem_instance.id)                  AS vinpr_instance_number
  FROM problem_instance
  WHERE instance_visibility = 1
  GROUP BY problem_instance.problem_definition_id;

CREATE VIEW v_ranking_data AS
SELECT
    vri_run_status              AS vrd_run_status,
    vri_run_id                  AS vrd_run_id,
    vri_user_id                 AS vrd_user_id,
    vri_instance_score          AS vrd_instance_score,
    vri_problem_instance_id     AS vrd_problem_instance_id,
    vri_problem_definition_id   AS vrd_problem_definition_id,
    vmax_max_score              AS vrd_max_score,
    vmin_min_score              AS vrd_min_score,
    vin_instance_number         AS vrd_instance_number,
    vinpr_instance_number       AS vrd_instance_number_private,
    vinpu_instance_number       AS vrd_instance_number_public,
    vri_visibility              AS vrd_visibility
  FROM v_recent_instances
  LEFT JOIN v_recent_min ON vri_problem_instance_id = vmin_problem_instance_id
  LEFT JOIN v_recent_max ON vri_problem_instance_id = vmax_problem_instance_id
  LEFT JOIN v_instance_number ON vri_problem_definition_id = vin_problem_definition_id
  LEFT JOIN v_instance_number_public ON vri_problem_definition_id = vinpu_problem_definition_id
  LEFT JOIN v_instance_number_private ON vri_problem_definition_id = vinpr_problem_definition_id;
  
CREATE VIEW v_global_ranking AS
SELECT 
  tmp_user_id        AS vgr_user_id,
  sum(tmp_score_sum) AS vgr_score_sum,
  sum(tmp_sub_count) AS vgr_sub_count
FROM(
  SELECT
      vrr_user_id          AS tmp_user_id,
      sum(vrr_run_score)   AS tmp_score_sum,
      count(vrr_user_id)   AS tmp_sub_count
    FROM v_recent_runs
    WHERE vrr_problem_definition_visibility = 1
    GROUP BY vrr_user_id
  UNION ALL
  SELECT
      vrr_user_id                AS tmp_user_id,
      sum(vrr_run_score_public)  AS tmp_score_sum,
      count(vrr_user_id)         AS tmp_sub_count
    FROM v_recent_runs
    WHERE vrr_problem_definition_visibility = 0
    GROUP BY vrr_user_id
)a
GROUP BY tmp_user_id

CREATE OR REPLACE VIEW group_owner_member_run AS 
SELECT 
	group_problem_definition.group_id, 
	group_problem_definition.problem_definition_id, 
	student_group.owner AS owner_id, 
	group_user.user_id AS member_id, 
	run.id AS run_id, 
	run.source_file AS source_file_id 
FROM group_problem_definition 
	join student_group on group_problem_definition.group_id = student_group.id 
	join group_user on group_problem_definition.group_id = group_user.group_id 
	join run on run.user_id = group_user.user_id;