CREATE TABLE IF NOT EXISTS voting_tokens(
    id SERIAL PRIMARY KEY,
    voting_token VARCHAR(8) UNIQUE,
    cpf VARCHAR(14),
    batch_id BIGINT NOT NULL,
    is_active BOOLEAN DEFAULT TRUE NOT NULL,
    is_deleted BOOLEAN DEFAULT FALSE NOT NULL,
    created_by BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP,

    CONSTRAINT fk_restaurant_creator
        FOREIGN KEY(created_by)
        REFERENCES users(id),

    CONSTRAINT fk_token_batch
            FOREIGN KEY(batch_id)
            REFERENCES voting_token_batches(id)
);