CREATE TABLE IF NOT EXISTS voting_token_batches(
    id SERIAL PRIMARY KEY,
    quantity INTEGER NOT NULL,
    dish_id BIGINT NOT NULL,
    is_active BOOLEAN DEFAULT TRUE NOT NULL,
    is_deleted BOOLEAN DEFAULT FALSE NOT NULL,
    created_by BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP,

    CONSTRAINT fk_restaurant_creator
        FOREIGN KEY(created_by)
        REFERENCES users(id)
);