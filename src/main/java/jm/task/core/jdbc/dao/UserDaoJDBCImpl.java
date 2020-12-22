package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection;

    public UserDaoJDBCImpl() {
        Util util = new Util();
        this.connection = util.getConnection();
    }

    public void createUsersTable() {
        try (PreparedStatement statement = connection.prepareStatement(SQLUser.CREATE_TABLE.QUERY)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        try (PreparedStatement statement = connection.prepareStatement(SQLUser.DROP_TABLE.QUERY)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement statement = connection.prepareStatement(SQLUser.INSERT.QUERY)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setInt(3, age);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        try (PreparedStatement statement = connection.prepareStatement(SQLUser.REMOVE.QUERY)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        try (PreparedStatement statement = connection.prepareStatement(SQLUser.GET_ALL_USERS.QUERY)) {
            final ResultSet rs = statement.executeQuery();
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setAge(rs.getByte("age"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void cleanUsersTable() {
        try (PreparedStatement statement = connection.prepareStatement(SQLUser.CLEAN.QUERY)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
}
