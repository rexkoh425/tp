package parser;

import car.CarList;
import customer.CustomerList;
import exceptions.CliRentalException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {

    @Test
    public void parse_validUserInput_commandActionExecuted() throws CliRentalException {
        String userInput = "add-user /u user1234 /a 18 /c 82750174";
        CustomerList.getCustomers().clear();

        assertFalse(Parser.parse(userInput));
        assertEquals(1, CustomerList.getCustomers().size());
        assertEquals("user1234", CustomerList.getCustomers().get(0).getCustomerName());

        String userInput1 = "add-car /n Honda Civic /c SGE4966P /p 123";
        CarList.getCarsList().clear();

        assertFalse(Parser.parse(userInput1));
        assertEquals(1, CarList.getCarsList().size());
        assertEquals(123.0, CarList.getCarsList().get(0).getPrice());


        String userInput2 = "exit";
        assertTrue(Parser.parse(userInput2));
    }
}
