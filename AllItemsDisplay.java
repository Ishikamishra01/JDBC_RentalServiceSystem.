import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AllItemsDisplay {

    public static void main(String[] args) {
        // Database connection parameters
        String jdbcURL = "jdbc:mysql://localhost:3306/rental_service";
        String dbUser = "root";
        String dbPassword = "password";
        
        try {
            // Establishing connection to the database
            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            
            // SQL query to select all items
            String sql = "SELECT * FROM items";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            
            System.out.println("Available Items for Rent:");
            System.out.println("---------------------------------------------------");
            System.out.printf("%-10s %-20s %-20s %-10s\n", "ID", "Name", "Category", "Price/Day");
            System.out.println("---------------------------------------------------");
            
            // Iterating through the result set and displaying each item
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String category = resultSet.getString("category");
                double pricePerDay = resultSet.getDouble("price_per_day");
                
                System.out.printf("%-10d %-20s %-20s %-10.2f\n", id, name, category, pricePerDay);
            }
            
            // Closing the connection
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
