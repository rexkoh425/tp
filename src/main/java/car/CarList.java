package car;

import java.util.ArrayList;

public class CarList {

    public static ArrayList<Car> carsList = new ArrayList<>();

    public static ArrayList<Car> getCarsList() {
        return carsList;
    }

    public static void addCar(Car car) {
        carsList.add(car);
        System.out.println("Car added to list");
        System.out.println("Car details:");
        System.out.println(car.getModel() + " | " + car.getLicensePlateNumber()
                + " | $" + car.getPrice());
    }

    public static void removeCar(String carLicensePlateNumber) {
        Car carToRemove = null;

        // Iterate through the list of cars to find the one with the given license plate
        for (Car car : carsList) {
            if (car.getLicensePlateNumber().equals(carLicensePlateNumber)) {
                carToRemove = car;
                break;
            }
        }

        // Remove the car if it exists
        if (carToRemove != null) {
            carsList.remove(carToRemove);
            System.out.println("Car with license plate " + carLicensePlateNumber + " removed from list.");
        } else {
            System.out.println("No car found with license plate " + carLicensePlateNumber);
        }
    }
}
