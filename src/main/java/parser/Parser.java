package parser;

import car.Car;
import car.CarList;
import customer.Customer;
import customer.CustomerList;

public class Parser {

    private static final String ADD_CUSTOMER_COMMAND = "add-user";
    private static final String ADD_CAR_COMMAND = "add-car";

    public static boolean parse(String userInput) {
        String[] words = userInput.split(" ");
        String command = words[0].toLowerCase();

        switch (command) {
        case ADD_CUSTOMER_COMMAND:
            Customer customer = CustomerParser.parseIntoCustomer(userInput);
            CustomerList.addCustomer(customer);
            return false;
        case ADD_CAR_COMMAND:
            Car car = CarParser.parseIntoCar(userInput);
            CarList.addCar(car);
            return false;
        case "exit":
            return true;
        default:
            System.out.println("Invalid command");
            return false;
        }
    }
}
