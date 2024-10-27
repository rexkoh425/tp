package file;

import car.Car;
import car.CarList;
import exceptions.CarException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles file operation for the car.
 */
public class CarFile {

    private static final String CAR_DATA_FILENAME = "carData.txt";
    private static final String CAR_DATA_FILEPATH = FileHandler.getDirName() + "/" + CAR_DATA_FILENAME;
    private static final File CAR_DATA_FILE = new File(CAR_DATA_FILEPATH);

    public static String getCarDataFilename() {
        return CAR_DATA_FILENAME;
    }

    public static String getCarDataFilepath() {
        return CAR_DATA_FILEPATH;
    }

    private static void addCarWithParameters(String[] parameters, ArrayList<Integer> errorLines, int line) {
        String model = parameters[0];
        String licensePlateNumber = parameters[1];
        try {
            double price = Double.parseDouble(parameters[2]);
            boolean isRented = Boolean.parseBoolean(parameters[3]);
            Car car = new Car(model, licensePlateNumber, price, isRented);
            CarList.addCarWithoutPrintingInfo(car);
        }catch(NumberFormatException e) {
            errorLines.add(line);
        }
    }

    public static void updateCarDataFile() throws IOException {
        FileWriter fw = new FileWriter(CAR_DATA_FILE);
        String textToAdd = CarList.carListToFileString();
        fw.write(textToAdd);
        fw.close();
    }

    public static void createCarFileIfNotExist(){
        if(!CAR_DATA_FILE.exists()){
            FileHandler.createNewFile(CAR_DATA_FILE);
        }
    }

    private static void loadCarData() throws FileNotFoundException, CarException {
        if(CAR_DATA_FILE.exists()){
            Scanner scanner = new Scanner(CAR_DATA_FILE);
            ArrayList<Integer> errorLines = new ArrayList<>();
            int line = 1;
            while (scanner.hasNext()) {
                scanLineAndAddCar(scanner, errorLines, line);
                line ++;
            }
            if(!errorLines.isEmpty()) {
                throw CarException.invalidParameters(errorLines);
            }
        }
    }

    private static void scanLineAndAddCar(Scanner scanner, ArrayList<Integer> errorLines, int line) {
        String input = scanner.nextLine();
        String[] parameters = input.split(" \\| ");
        if(parameters.length != Car.NUMBER_OF_PARAMETERS){
            errorLines.add(line);
        }else{
            addCarWithParameters(parameters, errorLines, line);
        }
    }

    public static void loadCarDataIfExist(){
        try {
            CarFile.loadCarData();
        } catch (FileNotFoundException e) {
            System.out.println("carData.txt not found in data directory. Please try again");
        } catch (CarException e) {
            System.out.println(e.getMessage());
        }
    }
}
