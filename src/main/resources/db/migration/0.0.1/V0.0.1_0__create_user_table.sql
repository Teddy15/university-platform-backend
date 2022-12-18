CREATE TABLE IF NOT EXISTS "user" (
    ID SERIAL PRIMARY KEY NOT NULL,
    USERNAME VARCHAR(20) UNIQUE NOT NULL,
    EMAIL VARCHAR(30) UNIQUE NOT NULL,
    PASSWORD VARCHAR(60) NOT NULL,
    FULL_NAME VARCHAR (50) NOT NULL,
    ROLE VARCHAR(10) NOT NULL,
    REGISTERED_AT TIMESTAMP,
    LAST_UPDATED_AT TIMESTAMP
);
