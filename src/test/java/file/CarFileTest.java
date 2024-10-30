package file;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;

public class CarFileTest {

    @Test
    public void testGetCarDataFilename() {
        CarFile carFile = new CarFile();
        assertEquals("carData.txt", carFile.getCarDataFilename());
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
