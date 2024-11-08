package file;


import org.junit.jupiter.api.Test;
import transaction.Transaction;
import transaction.TransactionList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static parser.TransactionParser.dateTimeFormatter;

class TransactionFileTest {

    @Test
    void testGetTransactionDataFilename() {
        TransactionFile transactionFile = new TransactionFile();
        assertEquals("transactionData.txt", transactionFile.getTransactionDataFilename());
    }

    @Test
    void testAddTransactionWithParameters() {
        TransactionFile transactionFile = new TransactionFile("transactionData1.txt");

        String[] parameters = {"SBS123B" ,"John" ,"3" ,"17-10-2024" , "false"};
        transactionFile.addTransactionWithParameters(parameters);
        parameters = new String[]{"SB" ,"Jon" ,"1" ,"17-11-2024" , "false"};
        transactionFile.addTransactionWithParameters(parameters);
        parameters = new String[]{"12313" ,"ohn" ,"365" ,"29-10-2024" , "false"};
        transactionFile.addTransactionWithParameters(parameters);
        Transaction transaction1 = TransactionList.getTransactionList().get(0);
        assertEquals(transaction1.getCustomer(), "John");
        assertEquals(transaction1.getCarLicensePlate() , "SBS123B");
        assertEquals(transaction1.getDuration(), 3);
        assertEquals(transaction1.getStartDate().format(dateTimeFormatter), "17-10-2024");
        Transaction transaction2 = TransactionList.getTransactionList().get(1);
        assertEquals(transaction2.getCustomer(), "Jon");
        assertEquals(transaction2.getCarLicensePlate() , "SB");
        assertEquals(transaction2.getDuration(), 1);
        assertEquals(transaction2.getStartDate().format(dateTimeFormatter), "17-11-2024");
        Transaction transaction3 = TransactionList.getTransactionList().get(2);
        assertEquals(transaction3.getCustomer(), "ohn");
        assertEquals(transaction3.getCarLicensePlate() , "12313");
        assertEquals(transaction3.getDuration(), 365);
        assertEquals(transaction3.getStartDate().format(dateTimeFormatter), "29-10-2024");
    }
}
