package file;

import car.Car;
import car.CarList;
import customer.Customer;
import customer.CustomerList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CarFileTest {

    public static CarFile carFile = new CarFile("carData4.txt");

    @BeforeEach
    void setUp(){
        carFile.deleteCarFileIfExist();
        carFile.createCarFileIfNotExist();

    }

    @Test
    public void testGetCarDataFilename() {
        CarFile carFile = new CarFile();
        assertEquals("carData.txt", carFile.getCarDataFilename());
    }

    private static ArrayList<Integer> inputTestCases() {
        int line = 1;
        ArrayList<Integer> errorLines = new ArrayList<>();
        String[] parameters = {"Corolla123","SGM1432K" ,"1.0" , "false" , "false"};
        carFile.addCarWithParameters(parameters, errorLines, line);
        line ++;
        parameters = new String[]{"Toyota", "SGK62080F", "0", "false" , "true"};
        carFile.addCarWithParameters(parameters, errorLines, line);
        line ++;
        parameters = new String[]{"Toyota Cor","S4932K" ,"jph", "false" , "true"};
        carFile.addCarWithParameters(parameters, errorLines, line);
        return errorLines;
    }

    @Test
    void testAddCarWithParameters() {
        ArrayList<Integer> errorLines = inputTestCases();
        assertEquals(CarList.getCarsList().size() , 2);
        Car car1 = CarList.getCarsList().get(0);
        assertEquals(car1.getModel(), "Corolla123");
        assertEquals(car1.getLicensePlateNumber() , "SGM1432K");
        assertEquals(car1.getPrice(), 1.0);
        assertFalse(car1.isRented());
        assertFalse(car1.isExpensive());
        Car car2 = CarList.getCarsList().get(1);
        assertEquals(car2.getModel(), "Toyota");
        assertEquals(car2.getLicensePlateNumber() , "SGK62080F");
        assertEquals(car2.getPrice(), 0.0);
        assertFalse(car2.isRented());
        assertTrue(car2.isExpensive());

        if(errorLines.size() == 1 && errorLines.get(0) == 3){
            assert true;
        }else{
            assert false;
        }
    }
}
