package CLI;

import java.util.Objects;
import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome To Folder Sync Management Tool.");
        String welcomeMessage = """
               \s
                Please select an option to proceed.
                [1] - Add New Server
                [2] - Get all existing Servers
                [3] - Update existing Servers
                [4] - Delete existing Servers
                \s
                [5] - Add a new syncing rule
                [6] - Get existing syncing rules
                [7] - Update an existing syncing rule
                [8] - Delete an existing syncing rule
               \s
                [9] - Check logs
               \s
                [q] - To quit the program
               """;
        String[] options = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "q", "h"};
        System.out.print(welcomeMessage + "\nPlease select and option from above list.\n");
        Scanner scanner = new Scanner(System.in);

        String selectionOption = "";

        while (!Objects.equals(selectionOption, "q")){

            System.out.print("\nUser Input [Enter h for Helper Menu]: \t");
            selectionOption = scanner.nextLine();
            boolean validOption = Arrays.asList(options).contains(selectionOption);
            if (validOption){
                switch (selectionOption){

                    case "1": {
                        System.out.println("Option Selected: [1] - Add New Server");
                        Servers.addServers();
                    }
                        break;
                    case "2": {
                        System.out.println("Option Selected: [2] - Get all existing Servers");
                        Servers.getServers();
                    }
                        break;
                    case "3": {
                        System.out.println("Option Selected: [3] - Update existing Servers ");
                        Servers.updateServers();
                    }
                        break;
                    case "4": {
                        System.out.println("Option Selected: [4] - Delete existing Servers ");
                        Servers.deleteServers();
                    }
                        break;

                    case "5": {
                        System.out.println("Option Selected: [5] - Add a new syncing rule ");
                        Rules.addRules();
                    }
                        break;
                    case "6": {
                        System.out.println("Option Selected: [6] - Get existing syncing rules ");
                        Rules.getRules();
                    }
                        break;
                    case "7": {
                        System.out.println("Option Selected: [7] - Update an existing syncing rule ");
                        Rules.updateRules();
                    }
                        break;
                    case "8": {
                        System.out.println("Option Selected: [8] - Delete an existing syncing rule ");
                        Rules.deleteRules();
                    }
                        break;

                    case "9": System.out.println("Option Selected: [9] - Check logs");
                        break;
                    case "H":
                    case "h": {
                        System.out.println("Option Selected: [h] - Print the helper menu");
                        System.out.print(welcomeMessage + "\nPlease select and option from above list - ");
                    }
                        break;
                    case "Q":
                    case "q": {
                        System.out.println("Option Selected: [q] - To quit the program");
                    }
                        break;
                }
            }else{
                System.out.println("Error: Please select an option from the above list.");
            }

        }

    }
}