alter table optfile add context integer;
update optfile set context = 
	CASE 
		WHEN name LIKE '%compilation.log' THEN 5 
		WHEN name LIKE '%.py' THEN 0 
		WHEN name LIKE '%.py3' THEN 0 
		WHEN name LIKE '%.cpp' THEN 0 
		WHEN name LIKE '%.cs' THEN 0 
		WHEN name LIKE '%.gz' THEN 0 
		WHEN name LIKE '%.java' THEN 0 
		WHEN name LIKE '%.in' THEN 2 
		WHEN name LIKE '%.out' THEN 3 
		WHEN name LIKE '%.err' THEN 8 
		WHEN name LIKE '%.tsp' THEN 2 
		WHEN name LIKE '%nstance%' THEN 2 
		WHEN name LIKE 'sourcenull' THEN 2 
		WHEN name LIKE 'Obrazek%' THEN 1 
		WHEN name LIKE '%Judge%' AND content LIKE '\177ELF%' THEN 10 
		WHEN name LIKE 'test%' THEN 7 
		WHEN name LIKE '%Output%' THEN 7 
		WHEN name LIKE 'null' THEN 13 
		WHEN id >= 40 AND id <= 46 THEN 12
		WHEN id >= 100 AND id <= 106 THEN 11
		WHEN id = 51 THEN 0
		WHEN id = 90 THEN 0
		
	end;

update optfile set type = 
	CASE 
		WHEN content LIKE '\177ELF%' THEN 1
		WHEN name LIKE '%.gz' THEN 2 
		WHEN name LIKE 'Obrazek%' THEN 4 
		ELSE 0
	end;
