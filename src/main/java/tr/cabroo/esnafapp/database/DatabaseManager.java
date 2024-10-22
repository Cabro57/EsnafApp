package tr.cabroo.esnafapp.database;

import java.sql.*;

public class DatabaseManager extends DatabaseConfiguration implements IDatabase {
    public Connection connect;
    //PreparedStatement preparedStatement = null;
    //Statement statement = null;
    //ResultSet resultSet = null;

    public void login() {

        try {
            connect = DriverManager.getConnection(getDbURL());
            if (connect != null) {
                System.out.println("Database connected successfully!");
            }

        } catch (SQLException e) {
            System.out.println("Oops, Bu bir sorun: " + e.getMessage());
        }
    }

    public void close() {
        try {
            connect.close();
        } catch (SQLException e) {
            System.out.println("Oops, Bu bir sorun: " + e);
        }
    }


}
