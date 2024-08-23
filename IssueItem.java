import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class IssueItem {

    public static void main(String[] args) {
        // Database connection parameters
        String jdbcURL = "jdbc:mysql://localhost:3306/rental_service";
        String dbUser = "root";
        String dbPassword = "password";
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter customer ID:");
        int customerId = scanner.nextInt();
        
        System.out.println("Enter item ID to issue:");
        int itemId = scanner.nextInt();
        
        System.out.println("Enter rental duration in days:");
        int rentalDuration = scanner.nextInt();
        
        try {
            // Establishing connection to the database
            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            
            // SQL query to issue an item
            String sql = "INSERT INTO rentals (customer_id, item_id, rental_duration) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, customerId);
            statement.setInt(2, itemId);
            statement.setInt(3, rentalDuration);
            
            int rows = statement.executeUpdate();
            
            if (rows > 0) {
                System.out.println("Item issued successfully.");
            } else {
                System.out.println("Failed to issue item.");
            }
            
            // Closing the connection
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        scanner.close();
    }
}
