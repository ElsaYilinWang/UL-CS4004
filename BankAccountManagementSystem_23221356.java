import lombok.Data;

import java.util.HashMap;

// The Java file containing the implementation of the system.

@Data  // add lombok dependency to create all the normally used boilerplate code
public class BankAccountManagementSystem_23221356 {

    private final HashMap<Integer, Double> bankAccounts;

    public BankAccountManagementSystem_23221356(HashMap<Integer, Double> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    /*
    1. Requirement 1: Account Creation
    Ensure the system allows the creation of a new bank account with a specified initial balance.
    * Implement a successful account creation operation.
    * Validate that the initial balance is a positive numeric value.
    * Implement a check to prevent the creation of duplicate accounts.
    */
    public boolean createAccount(int accountId, double initialBalance) {


        if (!bankAccounts.containsKey(accountId) && initialBalance > 0.0) {

            bankAccounts.put(accountId, initialBalance);

            System.out.println("Your account " + accountId + " has been successfully created.");
            System.out.println("Your balance is " + initialBalance + ".");

            return true;
        }

        return false;
    }

    /*
    2. Requirement 2: Deposit
    Enable users to deposit and withdraw funds from their bank account.
    * Implement a successful deposit operation.
    * Validate that deposited amounts are positive numeric values.
    */
    public double deposit(int accountId, double amount) {

        if (bankAccounts.containsKey(accountId) && amount > 0.0) {
            double currentBalance = bankAccounts.get(accountId);

            currentBalance += amount;

            bankAccounts.put(accountId, currentBalance);

            System.out.println("Deposit successful! Current balance is " + currentBalance + ".");

            return currentBalance;

        } else if (!bankAccounts.containsKey(accountId)) {

            System.out.println("Deposit unsuccessful. The account id is invalid.");
            return -1.0;

        } else if (amount <= 0.0) {

            System.out.println("Deposit unsuccessful. The amount is invalid.");
            return -2.0;
        }


        return -3.0; // This means something exceptional happens.
    }

    /*
    3. Requirement 3: Withdrawal
    Enable users to withdraw funds from their bank account.
    * Implement a successful withdrawal operation.
    * Validate that withdrawal amounts are positive numeric values.
    */

    public double withdraw(int accountId, double amount) {
        try {
            bankAccounts.containsKey(accountId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        double currentBalance = bankAccounts.get(accountId);

        if (amount > 0.0 && !overDraft(accountId, amount)) {

            currentBalance -= amount;
            System.out.println("Withdraw successful! Your current balance is " + currentBalance + ".");
            return currentBalance;


        } else if (amount <= 0.0) {

            System.out.println("Withdraw unsuccessful. The amount is invalid.");
            return -4.0;

        } else if (overDraft(accountId, amount)) {

            System.out.println("Withdraw unsuccessful. Overdraft");
            return -5.0;

        }


        return -6.0; // This means something exceptional happens.
    }

    /*
    4. Requirement 4: Overdraft Protection
    Prevent users from overdrawing their accounts and reject withdrawal attempts exceeding
    the available balance.
    * Implement overdraft prevention to disallow negative balances.
    * Reject withdrawal attempts that exceed the available balance.
    */
    public boolean overDraft(int accountId, double amount) {
        try {
            bankAccounts.get(accountId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        double currentBalance = bankAccounts.get(accountId);

        return bankAccounts.containsKey(accountId) && amount > currentBalance;
    }

    /*
    5. Requirement 5: Balance Inquiry
    Allow users to check their account balance at any time.
    * Implement a successful balance inquiry operation.
    */
    public double getAccountBalance(int accountId) {

        try {
            bankAccounts.containsKey(accountId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return bankAccounts.get(accountId);

    }

}
