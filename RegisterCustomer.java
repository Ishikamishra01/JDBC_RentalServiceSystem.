import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class RegisterCustomer {

    public static void main(String[] args) {
        // Database connection parameters
        String jdbcURL = "jdbc:mysql://localhost:3306/rental_service";
        String dbUser = "root";
        String dbPassword = "password";
        
        String customerName = args[0]; // Name passed from NewOrReturningCustomer class
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter customer contact number:");
        String contactNumber = scanner.nextLine();
        
        try {
            // Establishing connection to the database
            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            
            // SQL query to insert a new customer
            String sql = "INSERT INTO customers (name, contact_number) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, customerName);
            statement.setString(2, contactNumber);
            
            int rows = statement.executeUpdate();
            
            if (rows > 0) {
                System.out.println("Customer registered successfully.");
            } else {
                System.out.println("Failed to register customer.");
            }
            
            // Closing the connection
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        scanner.close();
    }
}
