ALTER TABLE users ADD COLUMN cpf VARCHAR(14);

UPDATE users SET cpf='0000000000';

ALTER TABLE users ALTER COLUMN cpf SET NOT NULL;