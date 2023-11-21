SELECT 1 FROM information_schema.columns
WHERE table_name = '<name_of_the_table>'
AND column_name = '<name_of_the_column>'
AND is_nullable = 'NO';
