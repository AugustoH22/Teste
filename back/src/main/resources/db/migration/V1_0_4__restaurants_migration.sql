CREATE TABLE IF NOT EXISTS restaurants(
    id SERIAL PRIMARY KEY,
    name VARCHAR(64) NOT NULL,
    responsible_name VARCHAR(64) NOT NULL,
    cep VARCHAR(8) NOT NULL,
    address VARCHAR(64) NOT NULL,
    neighborhood VARCHAR(32) NOT NULL,
    number VARCHAR(6) NOT NULL,
    is_active BOOLEAN DEFAULT TRUE NOT NULL,
    is_deleted BOOLEAN DEFAULT FALSE NOT NULL,
    created_by BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP,

    CONSTRAINT fk_restaurant_creator
        FOREIGN KEY(created_by)
        REFERENCES users(id)
);