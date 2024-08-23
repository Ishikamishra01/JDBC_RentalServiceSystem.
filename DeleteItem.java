import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DeleteItem {

    public static void main(String[] args) {
        // Database connection parameters
        String jdbcURL = "jdbc:mysql://localhost:3306/rental_service";
        String dbUser = "root";
        String dbPassword = "password";
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter the ID of the item you want to delete:");
        int itemId = scanner.nextInt();
        
        try {
            // Establishing connection to the database
            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            
            // SQL query to delete an item
            String sql = "DELETE FROM items WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, itemId);
            
            int rowsDeleted = statement.executeUpdate();
            
            if (rowsDeleted > 0) {
                System.out.println("The item has been deleted successfully.");
            } else {
                System.out.println("No item found with the given ID.");
            }
            
            // Closing the connection
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        scanner.close();
    }
}
