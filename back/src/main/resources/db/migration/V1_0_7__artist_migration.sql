CREATE TABLE IF NOT EXISTS artist(
    id SERIAL PRIMARY KEY,
    name VARCHAR(64) NOT NULL,
    cpf VARCHAR(15) NOT NULL,
    rg VARCHAR(15) NOT NULL,
    city VARCHAR(64) NOT NULL,
    uf VARCHAR(2) NOT NULL,
    telephone VARCHAR(15) NOT NULL,
    email VARCHAR(64) NOT NULL,
    is_active BOOLEAN DEFAULT TRUE NOT NULL,
    is_deleted BOOLEAN DEFAULT FALSE NOT NULL,
    created_by BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP,

    CONSTRAINT fk_artist_creator
        FOREIGN KEY(created_by)
        REFERENCES users(id)
);