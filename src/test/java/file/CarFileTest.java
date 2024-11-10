package file;

import car.Car;
import car.CarList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CarFileTest {

    private static final ArrayList<String> filenames = new ArrayList<>();

    @BeforeEach
    void setUp(){
        CarList.clearCarList();
        FileHandler.createFolderIfNotExist();
    }

    @AfterAll
    static void tearDown(){
        for (String filename : filenames) {
            File file = new File(filename);
            file.delete();
        }
    }

    @Test
    public void testGetCarDataFilename() {
        CarFile carFile = new CarFile();
        assertEquals("carData.txt", carFile.getCarDataFilename());
    }

    private static ArrayList<Integer> inputTestCases() {
        CarFile carFile = new CarFile("carData1.txt");
        int line = 1;
        ArrayList<Integer> errorLines = new ArrayList<>();
        String[] parameters = {"Corolla123","SGM1432K" ,"1.0" , "false" , "false"};
        carFile.addCarWithParameters(parameters, errorLines, line);
        line ++;
        parameters = new String[]{"Toyota", "SGK6200F", "0", "false" , "true"};
        carFile.addCarWithParameters(parameters, errorLines, line);
        line ++;
        parameters = new String[]{"Toyota Cor","SGK4932K" ,"jph", "false" , "true"};
        carFile.addCarWithParameters(parameters, errorLines, line);
        filenames.add(carFile.getAbsolutePath());
        return errorLines;
    }

    @Test
    void testAddCarWithParameters() {

        ArrayList<Integer> errorLines = inputTestCases();
        assertEquals(2  , CarList.getCarsList().size());

        Car car1 = CarList.getCarsList().get(0);
        assertEquals("Toyota",car1.getModel());
        assertEquals("SGK6200F", car1.getLicensePlateNumber());
        assertEquals(0.0 , car1.getPrice());
        assertFalse(car1.isRented());
        assertFalse(car1.isExpensive());
        Car car2 = CarList.getCarsList().get(1);
        assertEquals("Corolla123",car2.getModel());
        assertEquals("SGM1432K" ,car2.getLicensePlateNumber());
        assertEquals(1.0 , car2.getPrice());
        assertFalse(car2.isRented());
        assertTrue(car2.isExpensive());

        if(errorLines.size() == 1 && errorLines.get(0) == 3){
            assert true;
        }else{
            assert false;
        }
    }

    @Test
    void testUpdateCarDataFile() {
        CarFile carFile = new CarFile("carData2.txt");
        File testFile = new File(carFile.getAbsolutePath());
        System.out.println(testFile.getAbsolutePath());
        carFile.createCarFileIfNotExist();
        assertTrue(testFile.exists());

        CarList.addCarWithoutPrintingInfo(new Car("Toyota Corolla","SGM4932K" ,10));
        CarList.addCarWithoutPrintingInfo(new Car("Toyota Cor","S4932K" ,1));
        CarList.addCarWithoutPrintingInfo(new Car("Toyota","SGK" ,0));
        try{
            carFile.updateCarDataFile();
        } catch (IOException e) {
            assert false;
        }

        String[] lines = {"Toyota | SGK | 0.0 | false | false",
            "Toyota Cor | S4932K | 1.0 | false | false",
            "Toyota Corolla | SGM4932K | 10.0 | false | true"};
        try {
            Scanner scanner = new Scanner(testFile);
            int i = 0;
            while (scanner.hasNext()) {
                assertEquals(scanner.nextLine(), lines[i]);
                i++;
            }
        } catch (FileNotFoundException e) {
            assert false;
        }

        filenames.add(carFile.getAbsolutePath());
    }

    @Test
    void testLoadCarDataIfExist() {
        CarFile carFile = new CarFile("carData3.txt");
        File testFile = new File(carFile.getAbsolutePath());
        carFile.createCarFileIfNotExist();


        try {
            FileWriter fw = new FileWriter(testFile);
            String textToAdd = "Toyota Corolla | SGM4932K | 10 | false | false\n";
            textToAdd += "Toyota Cor | SDF4932K | 1 | false | false\n";
            textToAdd += "Toyota | SGK1234F | 0 | false | false";
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            assert false;
        }

        carFile.loadCarDataIfExist();
        Car car1 = CarList.getCarsList().get(0);
        assertEquals("Toyota", car1.getModel());
        assertEquals("SGK1234F", car1.getLicensePlateNumber());
        assertEquals(0.0, car1.getPrice(), 0.01);
        assertFalse(car1.isRented());
        assertFalse(car1.isExpensive());

        Car car2 = CarList.getCarsList().get(1);
        assertEquals("Toyota Cor", car2.getModel());
        assertEquals("SDF4932K", car2.getLicensePlateNumber());
        assertEquals(1.0, car2.getPrice(), 0.01);
        assertFalse(car2.isRented());
        assertFalse(car2.isExpensive());

        Car car3 = CarList.getCarsList().get(2);
        assertEquals("Toyota Corolla", car3.getModel());
        assertEquals("SGM4932K", car3.getLicensePlateNumber());
        assertEquals(10.0, car3.getPrice(), 0.01);
        assertFalse(car3.isRented());
        assertTrue(car3.isExpensive());

        filenames.add(carFile.getAbsolutePath());
    }

    @Test
    void scanLineAndAddCar() {
        CarFile carFile = new CarFile("carData4.txt");
        File testFile = new File(carFile.getAbsolutePath());
        carFile.createCarFileIfNotExist();
        try {
            FileWriter fw = new FileWriter(testFile);
            String textToAdd = "Toyota Corolla | SGM4932K | 120.0 | false | false";
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            assert false;
        }

        int line = 1;
        ArrayList<Integer> errorLines = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(testFile);
            carFile.scanLineAndAddCar(scanner , errorLines , line);
        }catch (FileNotFoundException e){
            assert false;
        }

        Car car1 = CarList.getCarsList().get(0);
        assertEquals(car1.getModel() , "Toyota Corolla");
        assertEquals(car1.getLicensePlateNumber() , "SGM4932K");
        assertEquals(car1.getPrice() , 120);
        assertFalse(car1.isRented());
        assertFalse(car1.isExpensive());

        filenames.add(carFile.getAbsolutePath());
    }

    @Test
    void testCreateCarFileIfNotExist() {
        CarFile carFile = new CarFile("carData5.txt");
        File testFile = new File(carFile.getAbsolutePath());
        carFile.createCarFileIfNotExist();

        assert testFile.exists();
        filenames.add(carFile.getAbsolutePath());
    }

}
