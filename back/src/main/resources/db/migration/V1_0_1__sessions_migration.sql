CREATE TABLE IF NOT EXISTS sessions(
    id SERIAL PRIMARY KEY,
    refresh_token VARCHAR NOT NULL,
    user_id BIGINT NOT NULL,
    expiration_date TIMESTAMP NOT NULL,
    is_expired BOOLEAN DEFAULT FALSE,
    is_deleted BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT NOW(),

    CONSTRAINT fk_session_user
        FOREIGN KEY(user_id)
        REFERENCES users(id)
            ON DELETE CASCADE
);