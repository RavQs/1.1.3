package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Util util = new Util();
    private Connection conn = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        final String SQLCREATE = "CREATE TABLE IF NOT EXISTS users" +
                "(id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(30) NOT NULL," +
                "lastName VARCHAR(30) NOT NULL," +
                "age INT(255))";

        try {
            conn = util.getSQLConnection();
            statement = conn.createStatement();
            statement.execute(SQLCREATE);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        final String SQLDROP = "DROP TABLE IF EXISTS users";

        try {
            conn = util.getSQLConnection();
            statement = conn.createStatement();
            statement.executeUpdate(SQLDROP);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        final String SQLSAVE = "INSERT INTO users(name, lastName, age) VALUES (?,?,?)";

        try {
            conn = util.getSQLConnection();
            preparedStatement = conn.prepareStatement(SQLSAVE);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        String sqlRemove = "delete from users where id = ?";

        try {
            conn = util.getSQLConnection();
            preparedStatement = conn.prepareStatement(sqlRemove);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {
        final String SQLSELECTALL = "select * from users";
        int i = 0;
        List<User> userList = new ArrayList<>();

        try {
            conn = util.getSQLConnection();
            preparedStatement = conn.prepareStatement(SQLSELECTALL);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                byte age = resultSet.getByte(4);
                userList.add(new User(name, lastName, age));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userList;

    }

    public void cleanUsersTable() {
        String sqlClean = "TRUNCATE TABLE users";

        try {
            conn = util.getSQLConnection();
            statement = conn.createStatement();
            statement.execute(sqlClean);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



}
