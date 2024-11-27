import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TDD_23221356 {

    private BankAccountManagementSystem_23221356 bankAccountManagementSystem;

    @BeforeEach
    void setup() {
        // create the variable for test
        System.out.println("Test startup");
        bankAccountManagementSystem = new BankAccountManagementSystem_23221356();
    }

    @AfterEach
    void tearDown() {
        // delete the variable for test
        bankAccountManagementSystem = null;
        System.gc();
        System.out.println("Test finishes");
    }

    /*
    1. Requirement 1: Account Creation
    Ensure the system allows the creation of a new bank account with a specified initial balance.
    * Implement a successful account creation operation.
    * Validate that the initial balance is a positive numeric value.
    * Implement a check to prevent the creation of duplicate accounts.
    */
    @Test
    void createAccountWithNonPositiveValue() {

        boolean expected = bankAccountManagementSystem.createAccount(100, -1.0);

        assertFalse(expected, "Should not create account.");

    }

    @Test
    void createAccountWithExistingAccount() {
        bankAccountManagementSystem.createAccount(100, 1.0);
        boolean expected = bankAccountManagementSystem.createAccount(100, 1.0);

        assertFalse(expected, "Should not create account.");
    }

    @Test
    void createAccountSuccess() {
        assertTrue(
                bankAccountManagementSystem.createAccount(100, 100.0),
                "Should create account.");
    }

    /*
    2. Requirement 2: Deposit
    Enable users to deposit and withdraw funds from their bank account.
    * Implement a successful deposit operation.
    * Validate that deposited amounts are positive numeric values.
    */
    @Test
    void depositSuccess() {
        bankAccountManagementSystem.createAccount(100, 10.0);
        assertTrue(bankAccountManagementSystem.deposit(100, 100.0));
    }

    @Test
    void depositNonPositiveValue() {
        bankAccountManagementSystem.createAccount(100, 20.2);
        assertFalse(bankAccountManagementSystem.deposit(100, -52.0));
    }

    @Test
    void depositNonExistingAccount() {
        assertFalse(bankAccountManagementSystem.deposit(200, 20.1));
    }

    /*
    3. Requirement 3: Withdrawal
    Enable users to withdraw funds from their bank account.
    * Implement a successful withdrawal operation.
    * Validate that withdrawal amounts are positive numeric values.
    */

    @Test
    void withdrawSuccess() {
        bankAccountManagementSystem.createAccount(100, 170.0);
        assertTrue(bankAccountManagementSystem.withdraw(100, 20.0));
    }

    @Test
    void withdrawNonPositiveValue() {
        bankAccountManagementSystem.createAccount(100, 170.0);
        assertFalse(bankAccountManagementSystem.withdraw(100, -10.0));
    }

    /*
        4. Requirement 4: Overdraft Protection
        Prevent users from overdrawing their accounts and reject withdrawal attempts exceeding
        the available balance.
        * Implement overdraft prevention to disallow negative balances.
        * Reject withdrawal attempts that exceed the available balance.
        */
    @Test
    void overDraft() {
        bankAccountManagementSystem.createAccount(100, 10.0);
        assertTrue(bankAccountManagementSystem.overDraft(100, 20.0));
    }

    /*
        5. Requirement 5: Balance Inquiry
        Allow users to check their account balance at any time.
        * Implement a successful balance inquiry operation.
        */
    @Test
    void getAccountBalance() {
        bankAccountManagementSystem.createAccount(100, 30.0);
        bankAccountManagementSystem.deposit(100, 270.0);
        bankAccountManagementSystem.withdraw(100, 300);
        assertEquals(0, bankAccountManagementSystem.getBankAccounts().get(100));
    }


}