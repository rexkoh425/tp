package parser;

import car.CarList;
import customer.CustomerList;
import exceptions.CliRentalException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void parse_validUserInput_commandActionExecuted() throws CliRentalException {
        String userInput = "add-user /u user1234 /a 18 /c 92750174";
        Parser.parse(userInput);

        assertEquals(1, CustomerList.getCustomers().size());
        assertEquals("user1234", CustomerList.getCustomers().get(0).getUsername());
        assertEquals(false, Parser.parse(userInput));

        String userInput1 = "add-car /n Honda Civic /c SGE1234T /p 123";
        Parser.parse(userInput1);

        assertEquals(1, CarList.getCarsList().size());
        assertEquals(123.0, CarList.getCarsList().get(0).getPrice());
        assertEquals(false, Parser.parse(userInput1));


        String userInput2 = "exit";
        Parser.parse(userInput2);
        assertEquals(true, Parser.parse(userInput2));
    }
}
