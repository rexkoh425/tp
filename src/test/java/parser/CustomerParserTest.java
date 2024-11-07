package parser;

import customer.Customer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerParserTest {

    @Test
    void parseParameterContents() {
        String userInput = "add-user /u test /a 18 /c 92750174";
        String[] parameters = { "/u" , "/a" , "/c"};
        String[] contents = CustomerParser.parseParameterContents(parameters, userInput);
        assert contents[0].equals("test");
        assert contents[1].equals("18");
        assert contents[2].equals("92750174");
    }

    @Test
    void parseIntoCustomer() {
        String userInput = "add-user /u test /a 18 /c 92750174";
        Customer customer = CustomerParser.parseIntoCustomer(userInput);
        assertEquals("test" , customer.getCustomerName());
        assertEquals(18 , customer.getAge());
        assertEquals( "92750174" , customer.getContactNumber());
    }
}
