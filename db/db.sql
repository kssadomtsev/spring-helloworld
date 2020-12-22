DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users (
    id BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL,
    age TINYINT NOT NULL
);

INSERT INTO users (id, name, lastName, age) VALUES (DEFAULT, 'John', 'Silver', 24);
INSERT INTO users (id, name, lastName, age) VALUES (DEFAULT, 'Johnny', 'Silverhand', 30);

DELETE FROM users WHERE id = 1;

SELECT * FROM users;

TRUNCATE users;

SELECT * FROM users;