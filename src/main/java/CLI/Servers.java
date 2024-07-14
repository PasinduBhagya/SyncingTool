package CLI;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Servers {

    public static void addServers(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the IP Address of the Primary Server: ");
        String IP_Address_1 = scanner.nextLine();

        System.out.print("Enter the IP Address of the Secondary Server: ");
        String IP_Address_2 = scanner.nextLine();

        System.out.print("Enter the Username of the Primary Server: ");
        String username1 = scanner.nextLine();

        System.out.print("Enter the Username of the Secondary Server: ");
        String username2 = scanner.nextLine();

        System.out.print("Enter the Password of the Primary Server: ");
        String password_1 = scanner.nextLine();

        System.out.print("Enter the Password of the Secondary Server: ");
        String password_2 = scanner.nextLine();

        System.out.println("INFO: Adding the Server information to the system.");
        // Connecting to the mysql Server and running the insert query
        String insertQuery = String.format("""
                INSERT INTO Servers (IP_Address_1, IP_Address_2, username1, username2, password_1, password_2)\s
                VALUES ('%s', '%s', '%s', '%s', '%s', '%s');
               \s""", IP_Address_1, IP_Address_2, username1, username2, password_1, password_2);
        Database.addToDatabase(insertQuery);
    }

    public static void getServers(){
            ResultSet resultSet = null;
            String serverID;
            String IP_Address_1;
            String IP_Address_2;
            String username1;
            String username2;
            String password_1;
            String password_2;

            System.out.println("Getting Servers from the database.");
            String selectQuery = "SELECT * FROM Servers";
            resultSet = Database.getFromDatabase(selectQuery);
            System.out.println("------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-10s | %-15s | %-15s | %-10s | %-10s | %-10s | %-10s |\n",
                    "ServersID", "IP_Address_1", "IP_Address_2", "Username1", "Username2", "Password1", "Password2");

            try {
                while (resultSet.next()) {
                    serverID = resultSet.getString(1);
                    IP_Address_1 = resultSet.getString(2);
                    IP_Address_2 = resultSet.getString(3);
                    username1 = resultSet.getString(4);
                    username2 = resultSet.getString(5);
                    password_1 = resultSet.getString(6);
                    password_2 = resultSet.getString(7);

                    System.out.println("------------------------------------------------------------------------------------------------------");
                    System.out.printf("| %-10s | %-15s | %-15s | %-10s | %-10s | %-10s | %-10s |\n",
                            serverID, IP_Address_1, IP_Address_2, username1, username2, password_1, password_2);
                }
                System.out.println("------------------------------------------------------------------------------------------------------");
            } catch (SQLException e){
                System.out.println("Error: Failed to retrieve data from the database. " + e);
            }

    }

    public static void updateServers() {
        getServers();
        ResultSet resultSet = null;
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Select the Server to update");
            int updateServerID = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            String selectQuery = "SELECT * FROM Servers WHERE serversID=" + updateServerID;
            resultSet = Database.getFromDatabase(selectQuery);


            System.out.println("Enter the data needs to be updated. [Leave blank to skip]");

            if (resultSet.next()) {
                String[] currentValues = new String[6];
                String[] newValues = new String[6];

                for (int i = 0; i < 6; i++) {
                    currentValues[i] = resultSet.getString(i + 2);
                }

                String[] prompts = {
                        "IP_Address_1", "IP_Address_2", "username1", "username2", "password_1", "password_2"
                };

                for (int i = 0; i < 6; i++) {
                    System.out.printf("Current Value [%s] (%s): New Value:\t", currentValues[i], prompts[i]);
                    newValues[i] = scanner.nextLine();
                    if (newValues[i].isEmpty()) {
                        newValues[i] = currentValues[i];
                    }
                }

                String sqlQuery = String.format(
                        "UPDATE Servers SET IP_Address_1 = '%s', IP_Address_2 = '%s', username1 = '%s', username2 = '%s', password_1 = '%s', password_2 = '%s' WHERE serversID = %d",
                        newValues[0], newValues[1], newValues[2], newValues[3], newValues[4], newValues[5], updateServerID
                );
                Database.updateOnDatabase(sqlQuery);
            } else {
                System.out.println("Server with the given ID not found.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
            } catch (Exception e) {
                System.out.println("Warning: Error occurred " + e);
            }
        }
    }

    public static void deleteServers(){
            getServers();

            System.out.println("INFO: Enter the Servers ID you want to delete.");

            Scanner scanner = new Scanner(System.in);
            int deleteServerID = scanner.nextInt();

            String deleteQuery = "DELETE FROM Servers where serversID=" + deleteServerID;

            Database.deleteOnDatabase(deleteQuery);
        }
}
