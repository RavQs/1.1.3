package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection conn = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        String sqlCreate = "CREATE TABLE IF NOT EXISTS users" +
                "(id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(30) NOT NULL," +
                "lastName VARCHAR(30) NOT NULL," +
                "age INT(255))";

        try {
            conn = getSQLconnection();
            statement = conn.createStatement();
            statement.execute(sqlCreate);
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
        String sqlDrop = "DROP TABLE IF EXISTS users";

        try {
            conn = getSQLconnection();
            statement = conn.createStatement();
            statement.executeUpdate(sqlDrop);
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
        String sqlSave = "INSERT INTO users(name, lastName, age) VALUES (?,?,?)";

        try {
            conn = getSQLconnection();
            preparedStatement = conn.prepareStatement(sqlSave);
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
            conn = getSQLconnection();
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
        String sqlSelectAll = "select * from users";
        int i = 0;
        List<User> userList = new ArrayList<>();

        try {
            conn = getSQLconnection();
            preparedStatement = conn.prepareStatement(sqlSelectAll);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong(1);
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
            conn = getSQLconnection();
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


    public static Connection getSQLconnection() throws SQLException {
        String userName = "Ravv";
        String userPass = "5378144Kor!";
        String connectionURL = "jdbc:mysql://localhost:3306/kata";

        return DriverManager.getConnection(connectionURL, userName, userPass);
    }
}
