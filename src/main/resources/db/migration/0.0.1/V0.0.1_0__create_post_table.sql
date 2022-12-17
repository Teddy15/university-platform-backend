CREATE TABLE IF NOT EXISTS post(
    id serial PRIMARY KEY,
    title VARCHAR(64) NOT NULL,
    content TEXT NOT NULL,
    created_at timestamp NOT NULL,
    last_updated_at timestamp NOT NULL
);