CREATE TABLE IF NOT EXISTS attachment(
    id BIGSERIAL PRIMARY KEY,
    file_key TEXT NOT NULL,
    file_name VARCHAR(128) NOT NULL,
    file_type VARCHAR(32) NOT NULL
)