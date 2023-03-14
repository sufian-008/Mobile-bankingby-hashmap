import java.util.HashMap;
import java.util.Scanner;

public class MobileBankingSystem {

    private static HashMap<String, String> userCredentials = new HashMap<>();
    private static HashMap<String, Double> userBalances = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Initialize user credentials and balances
        userCredentials.put("rafin", "1122");
        userBalances.put("user1", 1000.0);
        userCredentials.put("user2", "password2");
        userBalances.put("user2", 500.0);
        
        // Login or create account
        boolean isLoggedIn = false;
        String username = null;
        while (!isLoggedIn) {
            System.out.println("Enter your username:");
            username = scanner.nextLine();
            System.out.println("Enter your password:");
            String password = scanner.nextLine();
            if (userCredentials.containsKey(username) && userCredentials.get(username).equals(password)) {
                isLoggedIn = true;
            } else {
                System.out.println("Invalid username or password. Try again.");
            }
        }
        
        // Main menu
        while (true) {
            System.out.println("Welcome, " + username + "!");
            System.out.println("1. Send money");
            System.out.println("2. Cash out");
            System.out.println("3. Make payment");
            System.out.println("4. View e-cash balance");
            System.out.println("5. Exit");
            System.out.println("Enter your choice:");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    sendMoney(username);
                    break;
                case 2:
                    cashOut(username);
                    break;
                case 3:
                    //makePayment(username);
                    //break;
                case 4:
                  //  viewECashBalance(username);
                    break;
                case 5:
                    System.out.println("Thank you for using our mobile banking system. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void sendMoney(String sender) {
        System.out.println("Enter the recipient's username:");
        String recipient = scanner.nextLine();
        if (!userCredentials.containsKey(recipient)) {
            System.out.println("User " + recipient + " does not exist.");
            return;
        }
        System.out.println("Enter the amount to send:");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }
        if (amount > userBalances.get(sender)) {
            System.out.println("Insufficient balance.");
            return;
        }
        userBalances.put(sender, userBalances.get(sender) - amount);
        userBalances.put(recipient, userBalances.get(recipient) + amount);
        System.out.println("Transfer successful.");
    }

    private static void cashOut(String username) {
        System.out.println("Enter the amount to withdraw:");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }
        if (amount > userBalances.get(username)) {
            System.out.println("Insufficient balance.");
            return;
        }
        userBalances.put(username, userBalances.get(username) - amount);
        System.out.println("Cash withdrawal successful.");
    }
}
