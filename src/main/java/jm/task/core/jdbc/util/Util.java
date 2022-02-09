package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public  Connection getSQLConnection() throws SQLException {
        String userName = "Ravv";
        String userPass = "5378144Kor!";
        String connectionURL = "jdbc:mysql://localhost:3306/kata";

        return DriverManager.getConnection(connectionURL, userName, userPass);
    }
}
