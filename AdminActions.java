import java.util.Scanner;

public class AdminActions {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Admin Actions:");
            System.out.println("1. Add Item");
            System.out.println("2. Delete Item");
            System.out.println("3. View All Items");
            System.out.println("4. Exit");
            System.out.print("Choose an action: ");
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    AddItem.main(null);
                    break;
                case 2:
                    DeleteItem.main(null);
                    break;
                case 3:
                    DisplayAllItems.main(null);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}
