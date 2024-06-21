import java.util.Scanner;

class AtmInterface {
    private double accountBalance;

    public FinancialAccount(double initialDeposit) {
        setAccountBalance(initialDeposit);
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double balance) {
        this.accountBalance = balance;
    }

    public void creditAccount(double amount) {
        if (isValidTransactionAmount(amount)) {
            accountBalance += amount;
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public boolean debitAccount(double amount) {
        if (isValidTransactionAmount(amount) && hasSufficientFunds(amount)) {
            accountBalance -= amount;
            System.out.println("Withdrawn: $" + amount);
            return true;
        } else if (!hasSufficientFunds(amount)) {
            System.out.println("Insufficient funds.");
            return false;
        } else {
            System.out.println("Invalid withdrawal amount.");
            return false;
        }
    }

    private boolean isValidTransactionAmount(double amount) {
        return amount > 0;
    }

    private boolean hasSufficientFunds(double amount) {
        return amount <= accountBalance;
    }
}

class AutomatedTellerMachine {
    private FinancialAccount account;

    public AutomatedTellerMachine(FinancialAccount account) {
        this.account = account;
    }

    public void displayMainMenu() {
        System.out.println("Automated Teller Machine Menu:");
        System.out.println("1. Check Account Balance");
        System.out.println("2. Deposit Funds");
        System.out.println("3. Withdraw Cash");
        System.out.println("4. Exit");
    }

    public void checkAccountBalance() {
        System.out.println("Current Balance: $" + account.getAccountBalance());
    }

    public void depositFunds(double amount) {
        account.creditAccount(amount);
    }

    public void withdrawCash(double amount) {
        account.debitAccount(amount);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            displayMainMenu();
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkAccountBalance();
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    depositFunds(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawalAmount = scanner.nextDouble();
                    withdrawCash(withdrawalAmount);
                    break;
                case 4:
                    exit = true;
                    System.out.println("Thank you for using the Automated Teller Machine. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}

public class BankingSystem {
    public static void main(String[] args) {
        FinancialAccount userAccount = new FinancialAccount(500.00); // Initial deposit
        AutomatedTellerMachine atm = new AutomatedTellerMachine(userAccount);
        atm.run();
    }
}
