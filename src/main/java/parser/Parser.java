package parser;

import car.Car;
import car.CarList;
import customer.Customer;
import customer.CustomerList;
import transaction.Transaction;
import exceptions.CliRentalException;
import transaction.TransactionList;

import java.util.Scanner;

public class Parser {

    public static Scanner scanner = new Scanner(System.in);
    public static final String HELP_COMMAND = "help";
    public static final String ADD_CUSTOMER_COMMAND = "add-user";
    public static final String REMOVE_CUSTOMER_COMMAND = "remove-user";
    public static final String LIST_USERS_COMMAND = "list-users";
    public static final String ADD_CAR_COMMAND = "add-car";
    public static final String REMOVE_CAR_COMMAND = "remove-car";
    public static final String ADD_TRANSACTION_COMMAND = "add-tx";
    public static final String LIST_CARS_COMMAND = "list-cars";
    public static final String REMOVE_TRANSACTION_COMMAND = "remove-tx";
    public static final String LIST_ALL_TRANSACTIONS = "list-tx";
    public static final String LIST_COMPLETED_TRANSACTIONS = "list-tx-completed";
    public static final String LIST_UNCOMPLETED_TRANSACTIONS = "list-tx-uncompleted";
    public static final String EXIT_COMMAND = "exit";
    public static final String MARK_TRANSACTION_COMMAND = "mark-tx";
    public static final String UNMARK_TRANSACTION_COMMAND = "unmark-tx";
    public static final String FIND_TRANSACTION_BY_CUSTOMER_COMMAND = "find-tx-by-customer";

    public static void printDividerLine() {
        System.out.println("_".repeat(60));
    }

    public static String getUserInput(){
        System.out.println("What would you like to do?");
        printDividerLine();

        return scanner.nextLine().trim();
    }

    public static boolean parse(String userInput) throws CliRentalException {
        String[] words = userInput.split(" ",2);
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
        case LIST_CARS_COMMAND:
            CarList.printCarList();
            return false;
        case ADD_TRANSACTION_COMMAND:
            try {
                Transaction transaction = TransactionParser.parseIntoTransaction(userInput);
                TransactionList.addTx(transaction);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            return false;
        case REMOVE_CAR_COMMAND:
            try {
                String carLicensePlateNumber = CarParser.parseCarLicenseForRemoval(userInput);
                CarList.removeCar(carLicensePlateNumber);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return false;
        case REMOVE_CUSTOMER_COMMAND:
            try {
                String username = CustomerParser.parseUsernameForRemoval(userInput);
                if (CustomerList.removeCustomer(username)) {
                    System.out.println("Customer " + username + " has been removed.");
                } else {
                    System.out.println("Customer " + username + " not found.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            return false;
        case LIST_USERS_COMMAND:
            CustomerList.printCustomers();
            return false;
        case REMOVE_TRANSACTION_COMMAND:
            TransactionParser.parseRemoveTx(userInput);
            return false;
        case MARK_TRANSACTION_COMMAND:
            TransactionParser.parseMarkCompleted(userInput);
            return false;
        case UNMARK_TRANSACTION_COMMAND:
            TransactionParser.parseUnmarkCompleted(userInput);
            return false;
        case LIST_ALL_TRANSACTIONS:
            TransactionList.printAllTransactions();
            return false;
        case LIST_COMPLETED_TRANSACTIONS:
            TransactionList.printCompletedTransactions();
            return false;
        case LIST_UNCOMPLETED_TRANSACTIONS:
            TransactionList.printUncompletedTransactions();
            return false;
        case FIND_TRANSACTION_BY_CUSTOMER_COMMAND:
            TransactionParser.parseFindTxsByCustomer(userInput);
            return false;
        case EXIT_COMMAND:
            return true;
        default:
            throw CliRentalException.unknownCommand();
        }
    }
}
