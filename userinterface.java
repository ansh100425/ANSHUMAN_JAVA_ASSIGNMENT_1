// UserInterface.java
import java.util.Scanner;

public class UserInterface {
    private static Account[] accounts = new Account[100];
    private static int accountCount = 0;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        mainMenu();
    }

    private static void mainMenu() {
        int choice;
        do {
            System.out.println("\nWelcome to the Banking Application!");
            System.out.println("1. Create a new account");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. View account details");
            System.out.println("5. Update contact details");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> createAccount();
                case 2 -> performDeposit();
                case 3 -> performWithdrawal();
                case 4 -> showAccountDetails();
                case 5 -> updateContact();
                case 6 -> System.out.println("Exiting... Goodbye!");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 6);

        sc.close(); // Close scanner when done
    }

    private static void createAccount() {
        if (accountCount >= 100) {
            System.out.println("Cannot create more accounts. Maximum limit reached.");
            return;
        }

        System.out.print("Enter account holder name: ");
        String name = sc.nextLine();
        System.out.print("Enter initial deposit: ");
        double initialDeposit = sc.nextDouble();
        sc.nextLine();
        System.out.print("Enter email: ");
        String email = sc.nextLine();
        System.out.print("Enter phone number: ");
        String phone = sc.nextLine();

        int accountNumber = 1001 + accountCount;
        accounts[accountCount] = new Account(accountNumber, name, initialDeposit, email, phone);
        accountCount++;

        System.out.println("Account created successfully with Account Number: " + accountNumber);
    }

    private static void performDeposit() {
        System.out.print("Enter account number: ");
        int accNum = sc.nextInt();
        System.out.print("Enter deposit amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        Account acc = findAccount(accNum);
        if (acc != null) acc.deposit(amount);
        else System.out.println("Account not found.");
    }

    private static void performWithdrawal() {
        System.out.print("Enter account number: ");
        int accNum = sc.nextInt();
        System.out.print("Enter withdrawal amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        Account acc = findAccount(accNum);
        if (acc != null) acc.withdraw(amount);
        else System.out.println("Account not found.");
    }

    private static void showAccountDetails() {
        System.out.print("Enter account number: ");
        int accNum = sc.nextInt();
        sc.nextLine();

        Account acc = findAccount(accNum);
        if (acc != null) acc.displayAccountDetails();
        else System.out.println("Account not found.");
    }

    private static void updateContact() {
        System.out.print("Enter account number: ");
        int accNum = sc.nextInt();
        sc.nextLine();

        Account acc = findAccount(accNum);
        if (acc != null) {
            System.out.print("Enter new email: ");
            String email = sc.nextLine();
            System.out.print("Enter new phone number: ");
            String phone = sc.nextLine();
            acc.updateContactDetails(email, phone);
        } else {
            System.out.println("Account not found.");
        }
    }

    private static Account findAccount(int accNum) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getAccountNumber() == accNum) {
                return accounts[i];
            }
        }
        return null;
    }
}
