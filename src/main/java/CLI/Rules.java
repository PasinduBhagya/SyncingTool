package CLI;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Rules {
    static void addRules(){
        Scanner scanner = new Scanner(System.in);
        Servers.getServers();
        System.out.print("Enter the IP ServerID: ");
        String ServersID = scanner.nextLine();

        System.out.print("Enter the Source Folder Path: ");
        String srcFolderPath = scanner.nextLine();

        System.out.print("Enter the Destination Folder Path: ");
        String dstFolderPath = scanner.nextLine();

        System.out.print("Enter the Extension names: ");
        String extNames = scanner.nextLine();

        System.out.print("Enable Monitoring [Y/N]: ");
        String enableMonitoring = scanner.nextLine();

        System.out.print("Enter the Scheduled time [24h]: ");
        String schTime = scanner.nextLine();

        System.out.print("Enter a Name for the Rule ");
        String ruleName = scanner.nextLine();

        System.out.println("INFO: Adding the Server information to the system.");

        String insertQuery = String.format("""
                INSERT INTO SyncingRules (ServersID, srcFolderPath, dstFolderPath, extNames, enableMonitoring, schTime, ruleName)\s
                VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s');
               \s""", ServersID, srcFolderPath, dstFolderPath, extNames, enableMonitoring, schTime, ruleName);
        Database.addToDatabase(insertQuery);
    }
    static void getRules(){
        ResultSet resultSet = null;

        System.out.println("Getting Rules from the database.");
        String selectQuery = "SELECT * FROM SyncingRules";
        resultSet = Database.getFromDatabase(selectQuery);
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-10s | %-20s | %-15s | %-15s | %-15s | %-15s | %-15s |\n",
                "ruleID", "serverID", "srcFolderPath", "dstFolderPath", "extNames", "Monitoring", "schTime", "ruleName");

        try {
            while (resultSet.next()) {
                String ruleID = resultSet.getString(1);
                String serverID = resultSet.getString(2);
                String srcFolderPath = resultSet.getString(3);
                String dstFolderPath = resultSet.getString(4);
                String extNames = resultSet.getString(5);
                String enableMonitoring = resultSet.getString(6);
                String schTime = resultSet.getString(7);
                String ruleName = resultSet.getString(7);

                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.printf("| %-10s | %-10s | %-20s | %-15s | %-15s | %-15s | %-15s | %-15s |\n",
                        ruleID, serverID, srcFolderPath, dstFolderPath, extNames, enableMonitoring, schTime, ruleName);
            }
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");
        } catch (SQLException e){
            System.out.println("Error: Failed to retrieve data from the database. " + e);
        }
    }

    static void updateRules(){
        getRules();
        ResultSet resultSet = null;
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Select the Rule to update");
            int updateRuleID = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            String selectQuery = "SELECT * FROM SyncingRules WHERE ruleID=" + updateRuleID;
            resultSet = Database.getFromDatabase(selectQuery);


            System.out.println("Enter the data needs to be updated. [Leave blank to skip]");

            if (resultSet.next()) {
                String[] currentValues = new String[7];
                String[] newValues = new String[7];

                for (int i = 0; i < 7; i++) {
                    currentValues[i] = resultSet.getString(i + 2);
                }

                String[] prompts = {
                        "ServersID", "srcFolderPath", "dstFolderPath", "extNames", "enableMonitoring", "schTime", "ruleName"
                };

                for (int i = 0; i < 7; i++) {
                    System.out.printf("Current Value [%s] (%s): New Value:\t", currentValues[i], prompts[i]);
                    newValues[i] = scanner.nextLine();
                    if (newValues[i].isEmpty()) {
                        newValues[i] = currentValues[i];
                    }
                }

                String sqlQuery = String.format(
                        "UPDATE SyncingRules SET ServersID = '%s', srcFolderPath = '%s', dstFolderPath = '%s', extNames = '%s', enableMonitoring = '%s', schTime = '%s', ruleName = '%s' WHERE ruleID = %d",
                        newValues[0], newValues[1], newValues[2], newValues[3], newValues[4], newValues[5], newValues[6], updateRuleID
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
            }        }
    }
    static void deleteRules(){
        getRules();

        System.out.println("INFO: Enter the Rule ID you want to delete.");

        Scanner scanner = new Scanner(System.in);
        int deleteRuleID = scanner.nextInt();

        String deleteQuery = "DELETE FROM SyncingRules where ruleID=" + deleteRuleID;

        Database.deleteOnDatabase(deleteQuery);
    }
}

