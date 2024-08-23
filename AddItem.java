import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AddItem {
    
    public static void main(String[] args) {
        // Database connection parameters
        String jdbcURL = "jdbc:mysql://localhost:3306/rental_service";
        String dbUser = "root";
        String dbPassword = "password";
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter item name:");
        String itemName = scanner.nextLine();
        
        System.out.println("Enter item category (e.g., Car, Tool, Equipment):");
        String itemCategory = scanner.nextLine();
        
        System.out.println("Enter item description:");
        String itemDescription = scanner.nextLine();
        
        System.out.println("Enter item price per day:");
        double itemPrice = scanner.nextDouble();
        
        try {
            // Establishing connection to the database
            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            
            // SQL query to insert a new item
            String sql = "INSERT INTO items (name, category, description, price_per_day) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, itemName);
            statement.setString(2, itemCategory);
            statement.setString(3, itemDescription);
            statement.setDouble(4, itemPrice);
            
            int rows = statement.executeUpdate();
            
            if (rows > 0) {
                System.out.println("A new item has been added successfully.");
            }
            
            // Closing the connection
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        scanner.close();
    }
}
