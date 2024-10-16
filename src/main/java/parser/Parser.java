package parser;

import car.Car;
import car.CarList;
import customer.Customer;
import customer.CustomerList;

import java.util.Scanner;

public class Parser {

    public static Scanner scanner = new Scanner(System.in);

    private static final String ADD_CUSTOMER_COMMAND = "add-user";
    private static final String ADD_CAR_COMMAND = "add-car";
    private static final String LIST_CAR_COMMAND = "list-cars";

    public static String getUserInput(){
        System.out.println("What would you like to do?");
        System.out.println("_".repeat(60));

        String userInput = scanner.nextLine().trim();

        return userInput;
    }

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
        case LIST_CAR_COMMAND:
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
