BEGIN;

CREATE TYPE restaurant_categories AS ENUM('RESTAURANT', 'SIMILAR');

CREATE CAST (varchar AS restaurant_categories) WITH INOUT AS IMPLICIT;

ALTER TABLE restaurants ADD COLUMN category restaurant_categories;

UPDATE restaurants SET category = 'RESTAURANT';

ALTER TABLE restaurants ALTER COLUMN category SET NOT NULL;

COMMIT;