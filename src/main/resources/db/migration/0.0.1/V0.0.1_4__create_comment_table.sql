CREATE TABLE IF NOT EXISTS comment(
    id SERIAL PRIMARY KEY NOT NULL,
    content TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    last_updated_at TIMESTAMP NOT NULL,
    user_id INTEGER REFERENCES "user" (id),
    post_id INTEGER REFERENCES post (id),
    attachment_id INTEGER REFERENCES attachment (id)
);