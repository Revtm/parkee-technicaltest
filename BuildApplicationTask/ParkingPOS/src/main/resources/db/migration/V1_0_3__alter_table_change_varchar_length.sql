ALTER TABLE ticket ALTER COLUMN parking_status TYPE varchar(12) USING parking_status::varchar(12);
