package parser;

import car.Car;
import car.CarList;
import customer.Customer;
import customer.CustomerList;
import exceptions.CliRentalException;
import transcation.TransactionList;

import java.util.Scanner;

public class Parser {

    public static Scanner scanner = new Scanner(System.in);
    private static final String HELP_COMMAND = "help";
    private static final String ADD_CUSTOMER_COMMAND = "add-user";
    private static final String ADD_CAR_COMMAND = "add-car";
    private static final String REMOVE_CAR_COMMAND = "remove-car";
    private static final String LIST_USERS_COMMAND = "list-users";
    private static final String REMOVE_TRANSACTION_COMMAND = "remove-tx";

    public static String getUserInput(){
        System.out.println("What would you like to do?");
        System.out.println("_".repeat(60));

        String userInput = scanner.nextLine().trim();

        return userInput;
    }

    public static boolean parse(String userInput) throws CliRentalException {
        String[] words = userInput.split(" ");
        String command = words[0].toLowerCase();

        switch (command) {
        case HELP_COMMAND:
            HelpParser.parseHelpCommand();
            return false;
        case ADD_CUSTOMER_COMMAND:
            Customer customer = CustomerParser.parseIntoCustomer(userInput);
            CustomerList.addCustomer(customer);
            return false;
        case ADD_CAR_COMMAND:
            Car car = CarParser.parseIntoCar(userInput);
            CarList.addCar(car);
            return false;
        case REMOVE_CAR_COMMAND:
            try {
                String carLicensePlateNumber = CarParser.parseCarLicenseForRemoval(userInput);
                CarList.removeCar(carLicensePlateNumber);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return false;
        case LIST_USERS_COMMAND:
            CustomerList.printCustomers();
            return false;
        case REMOVE_TRANSACTION_COMMAND:
            TransactionList.removeTransaction(userInput);
            return false;
        case "exit":
            return true;
        default:
            throw CliRentalException.unknownCommand();
        }
    }
}
