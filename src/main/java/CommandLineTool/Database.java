package CommandLineTool;

import java.sql.*;

public class Database {

    protected String host = "jdbc:mysql://192.168.8.105:3306/mydatabase";
    protected String username = "myuser";
    protected String password = "Abc@12345";
    protected static Connection connection = null;
    protected static Statement statement = null;

    public Database() {
        try {
            connection = DriverManager.getConnection(host, username, password);
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("Error: Failed to connect to MySQL database");
            System.out.println(e.getMessage());
        }
    }

    public static void addToDatabase(String insertQuery){
        new Database();
        try {
            int rowsInserted = statement.executeUpdate(insertQuery);

            if (rowsInserted > 0) {
                System.out.println("INFO: A new record was added to the system successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Error: Failed to execute insert query");
            System.out.println(e.getMessage());
        }
    }

    public static ResultSet getFromDatabase(String selectQuery){
        new Database();
        ResultSet resultSet = null;

        try {
            resultSet = statement.executeQuery(selectQuery);

        } catch (SQLException e) {
            System.out.println("Error: Failed to execute select query");
            System.out.println(e.getMessage());
        }
        return resultSet;
    }

    public static void updateOnDatabase(String updateQuery){
        new Database();
        try {
            int rowsInserted = statement.executeUpdate(updateQuery);

            if (rowsInserted > 0) {
                System.out.println("INFO: Record was updated on the system successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Error: Failed to execute insert query");
            System.out.println(e.getMessage());
        }
    }

    public static void deleteOnDatabase(String deleteQuery){
        new Database();
        try {
            int rowsInserted = statement.executeUpdate(deleteQuery);

            if (rowsInserted > 0) {
                System.out.println("INFO: Entry has been deleted from the system successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Error: Failed to execute insert query");
            System.out.println(e.getMessage());
        }
    }
}