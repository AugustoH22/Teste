CREATE TABLE IF NOT EXISTS dish_votes(
    id SERIAL PRIMARY KEY,
    name VARCHAR(64) NOT NULL,
    contact_number VARCHAR(15) NOT NULL,
    presentation NUMERIC(3, 1) NOT NULL,
    treatment NUMERIC(3, 1) NOT NULL,
    creativity NUMERIC(3, 1) NOT NULL,
    originality NUMERIC(3, 1) NOT NULL,
    flavor NUMERIC(3, 1) NOT NULL,
    token_id BIGINT NOT NULL,
    is_active BOOLEAN DEFAULT TRUE NOT NULL,
    is_deleted BOOLEAN DEFAULT FALSE NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP,

    CONSTRAINT fk_dish_token
        FOREIGN KEY(token_id)
        REFERENCES voting_tokens(id)
);