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

    /**
     * Lists all cars in the current array list.
     */
    public static void printCarList(){
        System.out.println("Here are the current cars in the company");
        for(int i = 0 ; i < carsList.size(); i++){
            Car car = carsList.get(i);
            System.out.println( (i + 1) + ") " + car.getModel() + " | " + car.getLicensePlateNumber()
                    + " | $" +car.getPrice());
        }
    }
}
