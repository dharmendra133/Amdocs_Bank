import businesslayer.LogicLayer;
import businesslayer.LogicLayerImplementer;
import Databaseconnection.DatabaseConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.ResultSet;

public class Main {
    // Database connection details


    public static void main(String[] args) {
        try(Connection connection = DatabaseConnection.getConnection()) {
            // Establish a database connection


            LogicLayer logicLayer = new LogicLayerImplementer();
            // Create a table to store user and admin information if it doesn't exist
            logicLayer.createTable(connection);

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("------------------------------");
                System.out.println("Please select an option:");
                System.out.println("1. Admin");
                System.out.println("2. Manager");
                System.out.println("3. Existing Customer");
                System.out.println("4. New Customer");
                System.out.println("5. Exit");
                System.out.println("-------------------------------");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        //login
                        logicLayer.loginasAdmin(connection);
                        break;
                    case 2:
                        //login
                        logicLayer.loginasManager(connection);
                        // call roles.Manager page
                        break;
                    case 3:
                        //login`
                        logicLayer.loginasCustomer(connection);
                        //call Existing customer page
                        break;
                    case 4:
                        //No login required
                        //directly call New Customer page
                        NewCustomer nc=new NewCustomer();
                        nc.startNewCustomer();

                    case 5:
                        // Close resources and exit
                        scanner.close();
                        connection.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
