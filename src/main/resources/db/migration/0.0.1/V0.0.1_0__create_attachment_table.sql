CREATE TABLE IF NOT EXISTS attachment(
    file_key uuid,
    file_name VARCHAR(128) NOT NULL,
    file_type VARCHAR(32) NOT NULL,
    PRIMARY KEY (file_key)
)