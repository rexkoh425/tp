package file;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import customer.Customer;
import customer.CustomerList;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomerFileTest {

    private static final ArrayList<String> filenames = new ArrayList<>();

    @BeforeEach
    void setUp() {
        CustomerList.removeAllCustomers();
        FileHandler.createFolderIfNotExist();
    }

    @AfterAll
    static void tearDown() {
        for (String filename : filenames) {
            File file = new File(filename);
            file.delete();
        }
    }

    @Test
    public void testGetCustomerDataFilename() {
        CustomerFile customerFile = new CustomerFile();
        assertEquals("customerData.txt", customerFile.getCustomerDataFilename());
    }

    @Test
    void testAddCustomerWithParameters() {
        CustomerFile customerFile = new CustomerFile("customerData1.txt");
        int line = 1;
        ArrayList<Integer> errorLines = new ArrayList<>();

        String[] parameters = {"John Doe", "30", "83456789"};
        customerFile.addCustomerWithParameters(parameters, errorLines, line);
        line++;

        parameters = new String[]{"Jane Doe", "abc", "87654321"};
        customerFile.addCustomerWithParameters(parameters, errorLines, line);

        assertEquals(1, CustomerList.getCustomerList().size());
        assertEquals(1, errorLines.size());

        Customer customer = CustomerList.getCustomerList().get(0);
        assertEquals("John Doe", customer.getCustomerName());
        assertEquals(30, customer.getAge());
        assertEquals("+123456789", customer.getContactNumber());

        assertTrue(errorLines.contains(2));
    }

    @Test
    void testUpdateCustomerDataFile() {
        CustomerFile customerFile = new CustomerFile("customerData2.txt");
        File testFile = new File(customerFile.getAbsolutePath());
        customerFile.createCustomerFileIfNotExist();

        assertTrue(testFile.exists());

        CustomerList.addCustomerWithoutPrintingInfo(new Customer("John Doe", 30, "83456789"));
        CustomerList.addCustomerWithoutPrintingInfo(new Customer("Jane Smith", 25, "87654321"));

        try {
            customerFile.updateCustomerDataFile();
        } catch (IOException e) {
            assert false;
        }

        String[] expectedLines = {
            "John Doe | 30 | 83456789",
            "Jane Smith | 25 | 87654321"
        };

        try (Scanner scanner = new Scanner(testFile)) {
            int i = 0;
            while (scanner.hasNext()) {
                assertEquals(expectedLines[i], scanner.nextLine());
                i++;
            }
        } catch (IOException e) {
            assert false;
        }

        filenames.add(customerFile.getAbsolutePath());
    }

    @Test
    void testLoadCustomerDataIfExist() {
        CustomerFile customerFile = new CustomerFile("customerData3.txt");
        File testFile = new File(customerFile.getAbsolutePath());
        customerFile.createCustomerFileIfNotExist();

        try (FileWriter fw = new FileWriter(testFile)) {
            String data = "John Doe | 30 | 83456789\n";
            data += "Jane Smith | 25 | 87654321";
            fw.write(data);
        } catch (IOException e) {
            assert false;
        }

        customerFile.loadCustomerDataIfExist();

        Customer customer1 = CustomerList.getCustomerList().get(0);
        assertEquals("John Doe", customer1.getCustomerName());
        assertEquals(30, customer1.getAge());
        assertEquals("83456789", customer1.getContactNumber());

        Customer customer2 = CustomerList.getCustomerList().get(1);
        assertEquals("Jane Smith", customer2.getCustomerName());
        assertEquals(25, customer2.getAge());
        assertEquals("87654321", customer2.getContactNumber());

        filenames.add(customerFile.getAbsolutePath());
    }

    @Test
    void testCreateCustomerFileIfNotExist() {
        CustomerFile customerFile = new CustomerFile("customerData5.txt");
        File testFile = new File(customerFile.getAbsolutePath());
        customerFile.createCustomerFileIfNotExist();
        assertTrue(testFile.exists());
        filenames.add(customerFile.getAbsolutePath());
    }

    @Test
    void testScanLineAndAddCustomer() {
        CustomerFile customerFile = new CustomerFile("customerData4.txt");
        File testFile = new File(customerFile.getAbsolutePath());
        customerFile.createCustomerFileIfNotExist();

        try (FileWriter fw = new FileWriter(testFile)) {
            String customerData = "John Doe | 30 | 83456789";
            fw.write(customerData);
        } catch (IOException e) {
            assert false;
        }

        int line = 1;
        ArrayList<Integer> errorLines = new ArrayList<>();
        try (Scanner scanner = new Scanner(testFile)) {
            customerFile.scanLineAndAddCustomer(scanner, errorLines, line);
        } catch (IOException e) {
            assert false;
        }

        Customer customer = CustomerList.getCustomerList().get(0);
        assertEquals("John Doe", customer.getCustomerName());
        assertEquals(30, customer.getAge());
        assertEquals("83456789", customer.getContactNumber());

        filenames.add(customerFile.getAbsolutePath());
    }
}
