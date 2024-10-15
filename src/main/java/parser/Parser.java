package parser;

import car.Car;
import car.CarList;

public class Parser {

    public static boolean parse(String userInput) {
        String[] words = userInput.split(" ");
        String command = words[0].toLowerCase();

        switch (command) {
        case "add-car":
            Car car = CarParser.parseIntoCar(userInput);
            CarList.addCar(car);
            return false;
        case "print":
            CarList.printCarList();
            return false;
        case "exit":
            return true;
        default:
            System.out.println("Invalid command");
            return false;
        }
    }
}
