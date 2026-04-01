ALTER TABLE accounts
ADD COLUMN status VARCHAR(20);

UPDATE accounts
SET status = case
    WHEN active = true THEN 'ACTIVE'
    ELSE 'INACTIVE'
END;

ALTER TABLE accounts
ALTER COLUMN status SET NOT NULL;

ALTER TABLE accounts
DROP COLUMN active;

ALTER TABLE category
ADD COLUMN status VARCHAR(20);

UPDATE category
SET status = case
    WHEN active = true THEN 'ACTIVE'
    ELSE 'INACTIVE'
END;

ALTER TABLE category
ALTER COLUMN status SET NOT NULL;

ALTER TABLE category
DROP COLUMN active;