import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();
        
        // Add some dummy accounts
        bank.addAccount(new Account("123456", "pin123", 1000.0));
        bank.addAccount(new Account("654321", "pin321", 2000.0));
        
        while (true) {
            System.out.println("Welcome to the ATM");
            System.out.print("Enter account number: ");
            String accountNumber = scanner.nextLine();
            System.out.print("Enter PIN: ");
            String pin = scanner.nextLine();
            
            Account account = bank.authenticate(accountNumber, pin);
            if (account != null) {
                performTransactions(account, scanner);
            } else {
                System.out.println("Invalid account number or PIN");
            }
        }
    }

    private static void performTransactions(Account account, Scanner scanner) {
        while (true) {
            System.out.println("\n1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    System.out.println("Your balance is: " + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    System.out.println("Deposited: " + depositAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    if (account.withdraw(withdrawAmount)) {
                        System.out.println("Withdrew: " + withdrawAmount);
                    } else {
                        System.out.println("Insufficient balance");
                    }
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM");
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }
}