package exceptions;

import java.util.ArrayList;

public class CarException extends RuntimeException{

    private static final String ADD_CAR_FORMAT = "add-car /n [CAR_MODEL] /c [CAR_ID] /p [PRICE]";
    private static final String LICENSE_PLATE_NUMBER_FORMAT = """
            License Plate number format: SXX####X
            X -> Letters [A - Z], # -> Numbers [0 - 9]""";

    public CarException(String message) {
        super(message);
    }

    public static CarException addCarException() {
        String message = "Unable to add car. Refer to correct format below:\n" +
                ADD_CAR_FORMAT;
        return new CarException(message);
    }

    public static CarException invalidPrice() {
        String message = "Price cannot be negative!! Try again...";
        return new CarException(message);
    }

    public static CarException invalidLicensePlateNumber() {
        String message = "Oops!! License Plate number is invalid...\n"
                + LICENSE_PLATE_NUMBER_FORMAT;
        return new CarException(message);
    }

    public static CarException duplicateLicensePlateNumber() {
        String message = "Unable to add car.. License Plate number already exists!!";
        return new CarException(message);
    }

    public static CarException licensePlateNumberNotFound() {
        String message = "Car license plate number not found!!"
                + "\nUse command <list-cars> to view list of available cars.";
        return new CarException(message);
    }

    /**
     * Exception thrown if data in carData.txt does not fit pre-determined format.
     *
     * @param errorLines List of row numbers in the carData.txt file which the data format is wrong.
     * @return Exception with message of which the row of data which are wrong.
     */
    public static CarException invalidParameters(ArrayList<Integer> errorLines){
        String message = "Car data do not match number of parameters in " + errorLines.size() + " rows of data\n";
        message += "Rows are : ";
        message += errorLines.toString();
        message = message + "\n";
        return new CarException(message);
    }

}
