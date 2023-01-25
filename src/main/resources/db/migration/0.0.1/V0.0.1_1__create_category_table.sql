CREATE TABLE IF NOT EXISTS category (
    ID SERIAL PRIMARY KEY NOT NULL,
    NAME VARCHAR(40) UNIQUE NOT NULL,
    CREATED_AT TIMESTAMP,
    LAST_UPDATED_AT TIMESTAMP
);
