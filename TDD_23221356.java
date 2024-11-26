import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

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

    @Test
    void createAccount() {
    }

    @Test
    void deposit() {
    }

    @Test
    void withdraw() {
    }

    @Test
    void overDraft() {
    }

    @Test
    void getAccountBalance() {
    }


}