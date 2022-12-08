CREATE TABLE IF NOT EXISTS attachment(
    id serial PRIMARY KEY,
    file_name VARCHAR(128) NOT NULL,
    file_key VARCHAR(256) NOT NULL UNIQUE,
    file_type VARCHAR(32) NOT NULL
)