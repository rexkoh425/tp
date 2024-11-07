package file;

import org.junit.jupiter.api.Test;
import customer.Customer;
import customer.CustomerList;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerFileTest {

    @Test
    void getCustomerDataFilename() {
        CustomerFile customerFile = new CustomerFile();
        assertEquals("customerData.txt", customerFile.getCustomerDataFilename());
    }

    @Test
    void addCustomerWithParameters() {

        ArrayList<Integer> errorLines = inputTestCases();
        Customer customer1 = CustomerList.getCustomerList().get(0);
        assertEquals(customer1.getCustomerName(), "John");
        assertEquals(customer1.getContactNumber() , "90907638");
        assertEquals(customer1.getAge(), 22);
        Customer customer2 = CustomerList.getCustomerList().get(1);
        assertEquals(customer2.getCustomerName(), "John");
        assertEquals(customer2.getContactNumber(), "914109036638");
        assertEquals(customer2.getAge(), 22);
        if(errorLines.size() == 1 && errorLines.get(0) == 3){
            assert true;
        }else{
            assert false;
        }
    }

    private static ArrayList<Integer> inputTestCases() {
        CustomerFile customerFile = new CustomerFile("customerData1.txt");

        int line = 1;
        ArrayList<Integer> errorLines = new ArrayList<>();
        String[] parameters = {"John","22","90907638"};
        customerFile.addCustomerWithParameters(parameters , errorLines , line);
        line ++;
        parameters = new String[]{"John","22","914109036638"};
        customerFile.addCustomerWithParameters(parameters  , errorLines , line);
        line ++;
        parameters = new String[]{"John","mere","90907638"};
        customerFile.addCustomerWithParameters(parameters  , errorLines , line);
        return errorLines;
    }
}
