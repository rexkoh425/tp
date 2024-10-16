package transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import transcation.Transaction;
import transcation.TransactionList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TransactionListTest {
    @Test
    public void testRemoveTransaction() {
        Transaction transaction1 = new Transaction("1234", "Joe Pual",
                "13 days", "13/10/2024", "1111");
    }
}
