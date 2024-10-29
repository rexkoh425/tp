package transaction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    private Transaction transaction1;
    private Transaction transaction2;

    @BeforeEach
    void setUp() {
        // Initialize two Transaction instances before each test
        transaction1 = new Transaction("ABC-123", "John Doe", "5", "2024-10-01");
        transaction2 = new Transaction("XYZ-789", "Jane Smith", "3", "2024-10-02");
    }

    @Test
    void testTransactionIdGeneration() {
        // Assuming the transactionCounter starts at 1 and increments by 1 for each new Transaction
        assertEquals("TX5", transaction1.getTransactionId(), "First transaction ID should be TX1");
        assertEquals("TX6", transaction2.getTransactionId(), "Second transaction ID should be TX2");
    }

    @Test
    void testGetCarLicensePlate() {
        assertEquals("ABC-123", transaction1.getCarLicensePlate(), "Car license plate should match");
        assertEquals("XYZ-789", transaction2.getCarLicensePlate(), "Car license plate should match");
    }

    @Test
    void testGetCustomer() {
        assertEquals("John Doe", transaction1.getCustomer(), "Customer name should match");
        assertEquals("Jane Smith", transaction2.getCustomer(), "Customer name should match");
    }

    @Test
    void testIsCompletedInitiallyFalse() {
        assertFalse(transaction1.isCompleted(), "New transaction should not be completed");
        assertFalse(transaction2.isCompleted(), "New transaction should not be completed");
    }

    @Test
    void testSetCompleted() {
        transaction1.setCompleted(true);
        assertTrue(transaction1.isCompleted(), "Transaction should be marked as completed");

        transaction2.setCompleted(true);
        assertTrue(transaction2.isCompleted(), "Transaction should be marked as completed");

        transaction2.setCompleted(false);
        assertFalse(transaction2.isCompleted(), "Transaction should be marked as not completed");
    }

    @Test
    void testToStringWhenNotCompleted() {
        String expected1 = "[ ] TX1 | ABC-123 | John Doe | 5day(s) \nStart Date: 2024-10-01";
        String expected2 = "[ ] TX2 | XYZ-789 | Jane Smith | 3day(s) \nStart Date: 2024-10-02";

        assertEquals(expected1, transaction1.toString(), "toString should match expected format when not completed");
        assertEquals(expected2, transaction2.toString(), "toString should match expected format when not completed");
    }

    @Test
    void testToStringWhenCompleted() {
        transaction1.setCompleted(true);
        transaction2.setCompleted(true);

        String expected1 = "[X] TX3 | ABC-123 | John Doe | 5day(s) \nStart Date: 2024-10-01";
        String expected2 = "[X] TX4 | XYZ-789 | Jane Smith | 3day(s) \nStart Date: 2024-10-02";

        assertEquals(expected1, transaction1.toString(), "toString should match expected format when completed");
        assertEquals(expected2, transaction2.toString(), "toString should match expected format when completed");
    }

    @Test
    void testMultipleTransactionIds() {
        // Create additional transactions to test transactionCounter increments correctly
        Transaction transaction3 = new Transaction("LMN-456", "Alice Johnson", "2", "2024-10-03");
        Transaction transaction4 = new Transaction("DEF-321", "Bob Brown", "4", "2024-10-04");

        assertEquals("TX15", transaction3.getTransactionId(), "Third transaction ID should be TX3");
        assertEquals("TX16", transaction4.getTransactionId(), "Fourth transaction ID should be TX4");
    }
}