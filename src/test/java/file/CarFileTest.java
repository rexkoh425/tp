package file;

import car.CarList;
import car.Car;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.util.ArrayList;
import java.io.File;

public class CarFileTest {

    @Test
    public void testGetCarDataFilename() {
        CarFile carFile = new CarFile();
        assertEquals("carData.txt", carFile.getCarDataFilename());
    }



    @Test
    void testCreateCarFileIfNotExist() {
        CarFile carFile = new CarFile("carData2.txt");
        File testFile = new File(carFile.getAbsolutePath());
        carFile.createCarFileIfNotExist();

        if(testFile.exists()){
            assert true;
        }else{
            assert false;
        }
        System.out.println(testFile.delete());
    }

    @Test
    void addCarWithParameters() {
        ArrayList<Integer> errorLines = inputTestCases();
        Car car1 = CarList.getCarsList().get(0);
        assertEquals(car1.getModel() , "Corolla123");
        assertEquals(car1.getLicensePlateNumber() , "SGM32K");
        assertEquals(car1.getPrice() , 1.0);
        assertFalse(car1.isRented());
        Car car2 = CarList.getCarsList().get(1);
        assertEquals(car2.getModel() , "Toyota");
        assertEquals(car2.getLicensePlateNumber() , "SGK");
        assertEquals(car2.getPrice() , 0);
        assertFalse(car2.isRented());
        if(errorLines.size() == 1 && errorLines.get(0) == 3){
            assert true;
        }else{
            assert false;
        }
    }

    private static ArrayList<Integer> inputTestCases() {
        CarFile carFile = new CarFile("carData4.txt");

        int line = 1;
        ArrayList<Integer> errorLines = new ArrayList<>();
        String[] parameters = {"Corolla123","SGM32K" ,"1.0" , "false"};
        carFile.addCarWithParameters(parameters, errorLines, line);
        line ++;
        parameters = new String[]{"Toyota", "SGK", "0", "false"};
        carFile.addCarWithParameters(parameters, errorLines, line);
        line ++;
        parameters = new String[]{"Toyota Cor","S4932K" ,"jph", "false"};
        carFile.addCarWithParameters(parameters, errorLines, line);
        return errorLines;
    }
}
