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
