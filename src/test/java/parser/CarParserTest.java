package parser;

import car.Car;
import exceptions.CarException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CarParserTest {

    @Test
    public void isValidFormat_validUserInput_expectTrue() {
        String userInput = "add-car /n civic /c 12345 /p 150";
        String userInput1 = "add-car /n Abc /c DEF123X /p 57.30";

        assertEquals(true, CarParser.isValidFormat(userInput));
        assertEquals(true, CarParser.isValidFormat(userInput1));
    }

    @Test
    public void isValidFormat_invalidUserInput_expectFalse()
            throws CarException, NumberFormatException {
        String userInput = "add-car civic ABC123 150";
        String userInput1 = "add-car /c BCE123 /n civic /p 150";
        String userInput2 = "add-car /p 130 /c XYZ888 /n civic";

        assertEquals(false, CarParser.isValidFormat(userInput));
        assertEquals(false, CarParser.isValidFormat(userInput1));
        assertEquals(false, CarParser.isValidFormat(userInput2));
    }

    @Test
    public void parseIntoCar_validUserInput_carObjectCreated() {
        String userInput = "add-car /n civic /c 12345 /p 150";

        Car car = CarParser.parseIntoCar(userInput);
        assertEquals("civic", car.getModel());
        assertEquals("12345", car.getLicensePlateNumber());
        assertEquals(150, car.getPrice());
    }

    @Test
    public void parseIntoCar_invalidUserInput_carExceptionThrown() throws CarException {
        String userInput = "add-car /n civic /c JKL12345 /p -138";
        String userInput1 = "add-car /n civic ABC123 /p 150";

        assertThrows(CarException.class, () -> CarParser.parseIntoCar(userInput));
        assertThrows(CarException.class, () -> CarParser.parseIntoCar(userInput1));
    }
}
