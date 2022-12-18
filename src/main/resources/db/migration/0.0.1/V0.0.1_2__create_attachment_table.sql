CREATE TABLE IF NOT EXISTS attachment(
    id serial PRIMARY KEY,
    attachment_key TEXT NOT NULL,
    attachment_name VARCHAR(128) NOT NULL,
    attachment_type VARCHAR(32) NOT NULL
)