CREATE TABLE IF NOT EXISTS reaction(
    id serial PRIMARY KEY,
    reaction VARCHAR(16) NOT NULL,
    postId NUMERIC NOT NULL,
    created_on timestamp NOT NULL,
);