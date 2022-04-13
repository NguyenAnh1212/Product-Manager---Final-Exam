package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection {
    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/quan_ly_san_pham";
    public static final String JDBC_USER = "root";
    public static final String JDBC_PASSWORD = "123456";

    public static Connection getConnection(){
        Connection connection = null;

        try {
           Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL,
                    JDBC_USER, JDBC_PASSWORD);
            System.out.println("Thanh cong");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
