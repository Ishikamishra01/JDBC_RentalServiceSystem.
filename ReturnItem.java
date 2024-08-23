import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ReturnItem {

    public static void main(String[] args) {
        // Database connection parameters
        String jdbcURL = "jdbc:mysql://localhost:3306/rental_service";
        String dbUser = "root";
        String dbPassword = "password";
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter the rental ID to return:");
        int rentalId = scanner.nextInt();
        
        try {
            // Establishing connection to the database
            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            
            // SQL query to return an item
            String sql = "DELETE FROM rentals WHERE rental_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, rentalId);
            
            int rowsDeleted = statement.executeUpdate();
            
            if (rowsDeleted > 0) {
                System.out.println("Item returned successfully.");
            } else {
                System.out.println("No rental found with the given ID.");
            }
            
            // Closing the connection
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        scanner.close();
    }
}
