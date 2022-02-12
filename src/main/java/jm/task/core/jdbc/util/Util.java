package jm.task.core.jdbc.util;
import jm.task.core.jdbc.model.User;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {

    private final static String USER_NAME = "Ravv";
    private final static String USER_PASS = "5378144Kor!";
    private final static String CONNECTION_URL = "jdbc:mysql://localhost:3306/kata";


    public static Connection getSQLConnection() throws SQLException {
        return DriverManager.getConnection(CONNECTION_URL, USER_NAME, USER_PASS);
    }


}
