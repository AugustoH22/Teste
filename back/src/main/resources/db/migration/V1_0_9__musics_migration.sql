CREATE TABLE IF NOT EXISTS musics(
    id SERIAL PRIMARY KEY,
    title VARCHAR(64) NOT NULL,
    composer_id BIGINT NOT NULL,
    interpreter_id BIGINT NOT NULL,
    is_active BOOLEAN DEFAULT TRUE NOT NULL,
    is_deleted BOOLEAN DEFAULT FALSE NOT NULL,
    created_by BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP,

    CONSTRAINT fk_music_composer
        FOREIGN KEY(composer_id)
        REFERENCES artist(id),

    CONSTRAINT fk_music_interpreter
        FOREIGN KEY(interpreter_id)
            REFERENCES artist(id),

    CONSTRAINT fk_music_creator
        FOREIGN KEY(created_by)
        REFERENCES users(id)
);