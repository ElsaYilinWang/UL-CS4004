import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;

import java.util.HashMap;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountManagementSystemTest {

    private BankAccountManagementSystem bankAccountManagementSystem;
    @BeforeEach
    void init() {
        System.out.println("Test startup");
        bankAccountManagementSystem = new BankAccountManagementSystem();
    }

    // Create Account tests
    @Test
    @DisplayName("Create account success")
    void createAccountSuccess() {
        // generate random account number and initial balance
        int accountNumber = new Random().nextInt(1000);
        double initialBalance = new Random().nextDouble(0, 100);

        boolean expected = bankAccountManagementSystem.createAccount(accountNumber, initialBalance);
        // if the account number is unique and the initial balance is non-negative, return true
        assertTrue(expected, "Create account successfully!");
    }
    @Test
    @DisplayName("Create account with existing account")
    void createAccountWithExistingAccount() {

        //  create a valid account 100 with valid initial balance 100
        bankAccountManagementSystem.createAccount(100, 100.0);

        // create existing account number and a valid initial balance
        int accountNumber = 100;
        double initialBalance = 20.0;

        boolean expected = bankAccountManagementSystem.createAccount(accountNumber, initialBalance);

        // If the account number already exists, return false
        assertFalse(expected, "The account number already exists!");

    }
    @Test
    @DisplayName("Create account with negative initial balance")
    void createAccountWithNegativeInitialBalance(){
        // create a valid account number and a negative initial balance
        int accountNumber = 200;
        double initialBalance = new Random().nextDouble(0, 100) * (-1);
        boolean expected = bankAccountManagementSystem.createAccount(accountNumber, initialBalance);
        // if the initial balance is negative, return false
        assertFalse(expected, "Initial balance should not be negative!");
    }

    // Deposit tests
    @Test
    @DisplayName("Deposit Into Non Existing Account")
    void depositIntoNonExistingAccount() {
        // create a non-existing account
        double result = bankAccountManagementSystem.deposit(12, 12);

        // if account does not exist, return -1.0
        assertEquals(-1.0, result, "Account does not exist!");

    }

    @Test
    @DisplayName("Deposit Success")
    void depositSuccess() {
        // create an account number 10 with initial balance 100.0
        int accountNumber = 10;
        double initialBalance = 100.0;
        bankAccountManagementSystem.createAccount(accountNumber, initialBalance);

        // get the current balance
        double balance = bankAccountManagementSystem.getAccountBalance(accountNumber);

        // create a random amount to deposit
        double amount = new Random().nextDouble(100.0);

        // check expected balance by adding current balance and amount
        double expected = amount + balance;

        // get new balance by calling deposit method
        double newBalance = bankAccountManagementSystem.deposit(accountNumber, amount);

        // if deposit succeeds, return the balance
        assertEquals(expected, newBalance, String.format("Deposit Success! Your current balance is %.2f", newBalance));
    }


    // Withdraw tests

    @Test
    @DisplayName("Withdraw From Non-Existing Account")
    void withdrawFromNonExistingAccount() {
        // create a non-existing account
        double result = bankAccountManagementSystem.withdraw(30, 300.0);

        // if the account does not exist, return 0.0
        assertEquals(0.0, result, "The account does not exist!");

    }

    @Test
    @DisplayName("Withdraw Amount Exceeds Current Balance")
    void withdrawAmountExceedsCurrentBalance(){
        // create a valid account with initial balance
        int accountNumber = 15;
        double initialBalance = 15.0;
        bankAccountManagementSystem.createAccount(accountNumber, initialBalance);

        // check current balance
        double currentBalance = bankAccountManagementSystem.getAccountBalance(accountNumber);

        // create a withdrawal amount bigger than current balance
        double withdrawalAmount = new Random().nextDouble(0,99.0) + currentBalance;

        // get result by calling withdraw method
        double result = bankAccountManagementSystem.withdraw(accountNumber, withdrawalAmount);

        // if the amount exceeds current balance, return -2.0
        assertEquals(-2.0, result, "The withdrawal amount cannot euqal to or exceed current balance!");

    }

    @Test
    @DisplayName("Withdraw Amount Is Non Positive")
    void withdrawAmountIsNonPositive(){
        // create a valid account with initial balance
        int accountNumber = 15;
        double initialBalance = 15.0;
        bankAccountManagementSystem.createAccount(accountNumber, initialBalance);

        // create a negative withdrawal amount
        double withdrawalAmount = new Random().nextDouble(0,99.0) * (-1.0);

        // get result by calling withdraw method
        double result = bankAccountManagementSystem.withdraw(accountNumber, withdrawalAmount);

        // if the amount is negative, return -1.0
        assertEquals(-1.0, result, "The withdrawal amount should not be zero or negative!");
    }

    @Test
    @DisplayName("Withdraw If Amount Is Zero And Balance Not Zero")
    void withdrawIfAmountIsZeroAndBalanceNotZero(){

        // create a valid account with 0 initial balance
        int accountNumber = 19;
        double initialBalance = 194.7;
        bankAccountManagementSystem.createAccount(accountNumber, initialBalance);

        // create a 0 withdrawal amount
        double withdrawalAmount = 0.0;

        // get result by calling withdraw method
        double result = bankAccountManagementSystem.withdraw(accountNumber, withdrawalAmount);

        // if the amount is 0 and balance is 0, return -3.0
        assertEquals(-3.0, result, "The withdrawal amount is zero!");
    }
    @Test
    @DisplayName("Withdraw If Balance Is Zero And Amount Is Not Zero")
    void withdrawIfBalanceIsZeroAndAmountIsNotZero(){

        // create a valid account with 0 initial balance
        int accountNumber = 19;
        double initialBalance = 0.0;
        bankAccountManagementSystem.createAccount(accountNumber, initialBalance);

        // create a 0 withdrawal amount
        double withdrawalAmount = 30.0;

        // get result by calling withdraw method
        double result = bankAccountManagementSystem.withdraw(accountNumber, withdrawalAmount);

        // if the amount is 0 and balance is 0, return -3.0
        assertEquals(-3.0, result, "The balance is zero and the amount is not zero!");
    }


    @Test
    @DisplayName("Withdraw Success")
    void withdrawSuccess(){
        // create a valid account with positive initial balance
        int accountNumber = 25;
        double initialBalance = 180.0;
        bankAccountManagementSystem.createAccount(accountNumber, initialBalance);

        // check current balance
        double currentBalance = bankAccountManagementSystem.getAccountBalance(accountNumber);

        // create a valid withdrawal amount (> 0 & < currentBalance)
        double withdrawalAmount = new Random().nextDouble(0.1, currentBalance);

        // calculate expected balance
        double expected = currentBalance - withdrawalAmount;

        // check new balance by calling withdraw method
        double newBalance = bankAccountManagementSystem.withdraw(accountNumber, withdrawalAmount);

        // if the withdrawal succeeds, return the new Balance
        assertEquals(expected, newBalance, String.format("Withdraw succeed! Your current balance is %.2f", newBalance));
    }

    // Get Account Balance tests
    @Test
    @DisplayName("Get Account Balance For Non-Existing Account")
    void getAccountBalanceForNonExistingAccount() {
        // create a random account number
        int accountNumber = new Random().nextInt(900);

        // Throws an error if the account does not exist
        try{
            bankAccountManagementSystem.getAccountBalance(accountNumber);

        } catch(Exception e){

            System.out.println(e.getMessage());

            System.out.println("This account " + accountNumber + " does not exist!");

        }

    }

    @Test
    @DisplayName("Get Account Balance Success")
    void getAccountBalanceSuccess() {
        // create a valid account with positive initial balance
        int accountNumber = 47;
        double initialBalance = 356.0;
        bankAccountManagementSystem.createAccount(accountNumber, initialBalance);

        // deposit to change balance
        double depositAmount = 570.8;
        double balanceAfterDeposit = bankAccountManagementSystem.deposit(accountNumber, depositAmount);

        // withdraw to change balance
        double withdrawAmount = 238.7;
        double balanceAfterWithdraw = bankAccountManagementSystem.withdraw(accountNumber, withdrawAmount);

        // calculate expected balance
        double expectedBalance = initialBalance + depositAmount - withdrawAmount;

        // check current balance
        double currentBalance = bankAccountManagementSystem.getAccountBalance(accountNumber);

        assertEquals(expectedBalance, currentBalance, "Your current balance is " + currentBalance);

    }



    @AfterEach
    void tearDown(){
        // delete the variable and call garbage collector
        bankAccountManagementSystem = null;
        System.gc();
        System.out.println("Test finishes");

    }
}