package car;

import exceptions.CarException;

public class Car {

    public static final int NUMBER_OF_PARAMETERS = 4;
    private final String model;
    private final String licensePlateNumber;
    private final double price;
    private boolean isRented;

    public Car(String model, String licensePlateNumber, double price) {
        this.model = model;
        this.licensePlateNumber = licensePlateNumber;
        this.price = price;
        isRented = false;
    }

    public Car(String model, String licensePlateNumber, double price , boolean isRented) {
        this.model = model;
        this.licensePlateNumber = licensePlateNumber;
        this.price = price;
        this.isRented = isRented;
    }

    public String getModel() {
        return model;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public double getPrice() {
        return price;
    }

    public void markAsRented() {
        isRented = true;
    }

    public void markAsAvailable() {
        isRented = false;
    }

    public boolean isRented() {
        return isRented;
    }

    public String getRentedStatus(){
        if (isRented) {
            return "Rented";
        }
        return "Available";
    }

    public String toFileString(){
        return this.getModel() + " | " + this.getLicensePlateNumber()
                + " | " + this.getPrice() + " | " + this.isRented();
    }
}
