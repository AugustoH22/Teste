CREATE TABLE IF NOT EXISTS invites(
    id SERIAL PRIMARY KEY,
    destination_email VARCHAR(64) NOT NULL,
    invite_token VARCHAR NOT NULL,
    expires_in TIMESTAMP NOT NULL,
    is_active BOOLEAN NOT NULL,
    created_by BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),

    CONSTRAINT fk_invite_creator
        FOREIGN KEY(created_by)
        REFERENCES users(id)
);