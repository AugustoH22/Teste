CREATE TABLE IF NOT EXISTS dishes(
    id SERIAL PRIMARY KEY,
    name VARCHAR(64) NOT NULL,
    restaurant_id BIGINT NOT NULL,
    is_active BOOLEAN DEFAULT TRUE NOT NULL,
    is_deleted BOOLEAN DEFAULT FALSE NOT NULL,
    created_by BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP,

    CONSTRAINT fk_dishes_creator
        FOREIGN KEY(restaurant_id)
        REFERENCES restaurants(id),

    CONSTRAINT fk_restaurant_creator
        FOREIGN KEY(created_by)
        REFERENCES users(id)
);