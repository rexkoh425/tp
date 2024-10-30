package file;


import org.junit.jupiter.api.Test;
import transcation.Transaction;
import transcation.TransactionList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionFileTest {

    @Test
    void getTransactionDataFilename() {
        TransactionFile transactionFile = new TransactionFile();
        assertEquals("transactionData.txt", transactionFile.getTransactionDataFilename());
    }

    @Test
    void addTransactionWithParameters() {
        TransactionFile transactionFile = new TransactionFile("transactionData1.txt");

        String[] parameters = {"SBS123B" ,"John" ,"3" ,"2024-10-17"};
        transactionFile.addTransactionWithParameters(parameters);
        parameters = new String[]{"SB" ,"Jon" ,"0" ,"2024-11-17"};
        transactionFile.addTransactionWithParameters(parameters);
        parameters = new String[]{"12313" ,"ohn" ,"me" ,"2024-10-29"};
        transactionFile.addTransactionWithParameters(parameters);
        Transaction transaction1 = TransactionList.getTransactionList().get(0);
        assertEquals(transaction1.getBorrowerName(), "John");
        assertEquals(transaction1.getCarLicensePlate() , "SBS123B");
        assertEquals(transaction1.getDuration(), "3");
        assertEquals(transaction1.getStartDate() , "2024-10-17");
        Transaction transaction2 = TransactionList.getTransactionList().get(1);
        assertEquals(transaction2.getBorrowerName(), "Jon");
        assertEquals(transaction2.getCarLicensePlate() , "SB");
        assertEquals(transaction2.getDuration(), "0");
        assertEquals(transaction2.getStartDate() , "2024-11-17");
        Transaction transaction3 = TransactionList.getTransactionList().get(2);
        assertEquals(transaction3.getBorrowerName(), "ohn");
        assertEquals(transaction3.getCarLicensePlate() , "12313");
        assertEquals(transaction3.getDuration(), "me");
        assertEquals(transaction3.getStartDate() , "2024-10-29");

    }
}
