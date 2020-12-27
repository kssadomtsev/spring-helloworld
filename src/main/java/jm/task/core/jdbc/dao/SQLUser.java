package jm.task.core.jdbc.dao;

enum SQLUser {
    CREATE_TABLE("CREATE TABLE IF NOT EXISTS users (\n" +
            "    id BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,\n" +
            "    name VARCHAR(255) NOT NULL,\n" +
            "    lastName VARCHAR(255) NOT NULL,\n" +
            "    age TINYINT NOT NULL\n" +
            ");"),
    DROP_TABLE("DROP TABLE IF EXISTS users;"),
    INSERT("INSERT INTO users (id, name, lastName, age) VALUES (DEFAULT, (?), (?), (?))"),
    REMOVE("DELETE FROM users WHERE id = (?)"),
    GET_ALL_USERS("SELECT * FROM users"),
    CLEAN("TRUNCATE users");

    String QUERY;

    SQLUser(String QUERY) {
        this.QUERY = QUERY;
    }
}
