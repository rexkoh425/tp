package exceptions;

public class CarException extends RuntimeException{

    private static final String ADD_CAR_FORMAT = "add-car /n [CAR_MODEL] /c [CAR_ID] /p [PRICE]";

    public CarException(String message) {
        super(message);
    }

    public static CarException addCarException() {
        String message = "Unable to add car.. Refer to correct format below:\n" +
                ADD_CAR_FORMAT;
        return new CarException(message);
    }

    public static CarException invalidPrice() {
        String message = "Unable to add car.. Price cannot be negative!!";
        return new CarException(message);
    }

    public static CarException duplicateLicensePlateNumber() {
        String message = "Unable to add car.. License Plate number already exists!!";
        return new CarException(message);
    }
}
