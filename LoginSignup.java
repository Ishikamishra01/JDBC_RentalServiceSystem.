import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LoginSignup {

    public static void main(String[] args) {
        // Database connection parameters
        String jdbcURL = "jdbc:mysql://localhost:3306/rental_service";
        String dbUser = "root";
        String dbPassword = "password";
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to the Rental Service System");
        System.out.println("1. Login");
        System.out.println("2. Signup");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        
        if (choice == 1) {
            System.out.println("Enter your name to login:");
            String name = scanner.nextLine();
            try {
                Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
                String sql = "SELECT * FROM customers WHERE name = '" + name + "'";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                
                if (resultSet.next()) {
                    System.out.println("Login successful! Welcome " + name);
                } else {
                    System.out.println("No user found with that name.");
                }
                
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (choice == 2) {
            System.out.println("Enter your name to sign up:");
            String name = scanner.nextLine();
            RegisterCustomer.main(new String[]{name});
        } else {
            System.out.println("Invalid option. Exiting.");
        }
        
        scanner.close();
    }
}
