package file;


import org.junit.jupiter.api.Test;
import transaction.Transaction;
import transaction.TransactionList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

class TransactionFileTest {

    private static final ArrayList<String> filenames = new ArrayList<>();

    @BeforeEach
    void setUp(){
        TransactionList.clearTransactionList();
        FileHandler.createFolderIfNotExist();
    }

    @AfterAll
    static void tearDown(){
        for (String filename : filenames) {
            File file = new File(filename);
            file.delete();
        }
    }

    @Test
    public void testGetTransactionDataFilename() {
        TransactionFile transactionFile = new TransactionFile();
        assertEquals("transactionData.txt", transactionFile.getTransactionDataFilename());
    }

    private static ArrayList<Integer> inputTestCases() {
        TransactionFile transactionFile = new TransactionFile("transactionData1.txt");
        int line = 1;
        ArrayList<Integer> errorLines = new ArrayList<>();
        String[] parameters = {"SJX1234D", "John Doe", "5", "08-11-2024", "false"};
        transactionFile.addTransactionWithParameters(parameters , errorLines , line);
        line++;
        parameters = new String[]{"SJX1234D", "Jane Doe", "3", "10-10-2024", "true"};
        transactionFile.addTransactionWithParameters(parameters , errorLines , line);
        line++;
        parameters = new String[]{"SJX1234D", "Alice", "seven", "15-09-2024", "false"};
        transactionFile.addTransactionWithParameters(parameters , errorLines , line);
        filenames.add(transactionFile.getAbsolutePath());
        return errorLines;
    }

    @Test
    void testAddTransactionWithParameters() {
        ArrayList<Integer> errorLines = inputTestCases();
        assertEquals(2, TransactionList.getTransactionList().size());

        Transaction transaction1 = TransactionList.getTransactionList().get(0);
        assertEquals("SJX1234D", transaction1.getCarLicensePlate());
        assertEquals("John Doe", transaction1.getCustomer());
        assertEquals(5, transaction1.getDuration());
        assertEquals(LocalDate.of(2024, 11, 8), transaction1.getStartDate());
        assertFalse(transaction1.isCompleted());

        Transaction transaction2 = TransactionList.getTransactionList().get(1);
        assertEquals("SJX1234D", transaction2.getCarLicensePlate());
        assertEquals("Jane Doe", transaction2.getCustomer());
        assertEquals(3, transaction2.getDuration());
        assertEquals(LocalDate.of(2024, 10, 10), transaction2.getStartDate());
        assertTrue(transaction2.isCompleted());

        if (errorLines.size() == 1 && errorLines.get(0) == 3) {
            assertTrue(true);
        } else {
            assert false;
        }
    }

    @Test
    void testUpdateTransactionDataFile() {
        TransactionFile transactionFile = new TransactionFile("transactionData2.txt");
        File testFile = new File(transactionFile.getAbsolutePath());
        transactionFile.createTransactionFileIfNotExist();
        assertTrue(testFile.exists());

        TransactionList.addTxWithoutPrintingInfo(new Transaction("SJX1234D", "John", 5,
                LocalDate.of(2024, 11, 8), false));
        TransactionList.addTxWithoutPrintingInfo(new Transaction("SJX1234D", "Jane", 3,
                LocalDate.of(2024, 10, 10), true));

        try {
            transactionFile.updateTransactionDataFile();
        } catch (IOException e) {
            assert false;
        }

        String[] lines = {
            "SJX1234D | John | 5 | 08-11-2024 | false",
            "SJX1234D | Jane | 3 | 10-10-2024 | true"
        };

        try {
            Scanner scanner = new Scanner(testFile);
            int i = 0;
            while (scanner.hasNext()) {
                assertEquals(scanner.nextLine(), lines[i]);
                i++;
            }
        } catch (FileNotFoundException e) {
            assert false;
        }

        filenames.add(transactionFile.getAbsolutePath());
    }

    @Test
    void testLoadTransactionDataIfExist() {
        TransactionFile transactionFile = new TransactionFile("transactionData3.txt");
        File testFile = new File(transactionFile.getAbsolutePath());
        transactionFile.createTransactionFileIfNotExist();

        try {
            FileWriter fw = new FileWriter(testFile);
            String textToAdd = "SJX1234D | John | 5 | 08-11-2024 | false\n";
            textToAdd += "SJE8720G | Jane | 3 | 10-10-2024 | true\n";
            textToAdd += "SJA9173C | Alice | 7 | 15-09-2024 | false";
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            assert false;
        }

        transactionFile.loadTransactionDataIfExist();
        assertEquals(3, TransactionList.getTransactionList().size());

        Transaction transaction1 = TransactionList.getTransactionList().get(0);
        assertEquals("SJX1234D", transaction1.getCarLicensePlate());
        assertEquals("John", transaction1.getCustomer());
        assertEquals(5, transaction1.getDuration());
        assertEquals(LocalDate.of(2024, 11, 8), transaction1.getStartDate());
        assertFalse(transaction1.isCompleted());

        Transaction transaction2 = TransactionList.getTransactionList().get(1);
        assertEquals("SJE8720G", transaction2.getCarLicensePlate());
        assertEquals("Jane", transaction2.getCustomer());
        assertEquals(3, transaction2.getDuration());
        assertEquals(LocalDate.of(2024, 10, 10), transaction2.getStartDate());
        assertTrue(transaction2.isCompleted());

        Transaction transaction3 = TransactionList.getTransactionList().get(2);
        assertEquals("SJA9173C", transaction3.getCarLicensePlate());
        assertEquals("Alice", transaction3.getCustomer());
        assertEquals(7, transaction3.getDuration());
        assertEquals(LocalDate.of(2024, 9, 15), transaction3.getStartDate());
        assertFalse(transaction3.isCompleted());

        filenames.add(transactionFile.getAbsolutePath());
    }

    @Test
    void testScanLineAndAddTransaction() {
        TransactionFile transactionFile = new TransactionFile("transactionData4.txt");
        File testFile = new File(transactionFile.getAbsolutePath());
        transactionFile.createTransactionFileIfNotExist();

        try {
            FileWriter fw = new FileWriter(testFile);
            String textToAdd = "SJX1234D | John | 5 | 08-11-2024 | false";
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            assert false;
        }

        int line = 1;
        ArrayList<Integer> errorLines = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(testFile);
            transactionFile.scanLineAndAddTransaction(scanner, errorLines, line);
        } catch (FileNotFoundException e) {
            assert false;
        }

        Transaction transaction1 = TransactionList.getTransactionList().get(0);
        assertEquals("SJX1234D", transaction1.getCarLicensePlate());
        assertEquals("John", transaction1.getCustomer());
        assertEquals(5, transaction1.getDuration());
        assertEquals(LocalDate.of(2024, 11, 8), transaction1.getStartDate());
        assertFalse(transaction1.isCompleted());

        filenames.add(transactionFile.getAbsolutePath());
    }

    @Test
    void testCreateTransactionFileIfNotExist() {
        TransactionFile transactionFile = new TransactionFile("transactionData5.txt");
        File testFile = new File(transactionFile.getAbsolutePath());
        transactionFile.createTransactionFileIfNotExist();

        assertTrue(testFile.exists());
        filenames.add(transactionFile.getAbsolutePath());
    }
}
