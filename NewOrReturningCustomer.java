import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class NewOrReturningCustomer {

    public static void main(String[] args) {
        // Database connection parameters
        String jdbcURL = "jdbc:mysql://localhost:3306/rental_service";
        String dbUser = "root";
        String dbPassword = "password";
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter customer name:");
        String customerName = scanner.nextLine();
        
        try {
            // Establishing connection to the database
            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            
            // SQL query to check if customer exists
            String sql = "SELECT * FROM customers WHERE name = '" + customerName + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            
            if (resultSet.next()) {
                System.out.println("Welcome back, " + customerName + "!");
            } else {
                System.out.println("New customer detected, registering...");
                RegisterCustomer.main(new String[]{customerName});
            }
            
            // Closing the connection
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        scanner.close();
    }
}
