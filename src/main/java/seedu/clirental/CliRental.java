package seedu.clirental;

import customer.Customer;
import customer.CustomerList;
import exceptions.CarException;
import exceptions.CustomerException;
import parser.Parser;

import java.awt.desktop.SystemEventListener;
import java.util.Scanner;

public class CliRental {
    /**
     * Main entry-point for the java.clirental.CliRental application.
     */
    public static void main(String[] args) {
        printGreetings();
        getName();

        boolean isExit = false;

        while (!isExit) {
            try {
                String action = getUserInput();

                if (Parser.parse(action)) {
                    isExit = true;
                }

            } catch(CustomerException exception){
                exception.printErrorMessage();
            } catch(NumberFormatException exception){
                System.out.println("Unable to parse customer");
            } catch (CarException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Goodbye!");
    }

    public static void getName(){
        System.out.println("What is your name?");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello " + scanner.nextLine());
    }

    public static void printGreetings(){
        String logo =
                """
                        ____ _ _ ____            _        _
                        / ___| (_)  _ \\ ___ _ __ | |_ __ _| |
                        | |   | | | |_) / _ \\ '_ \\| __/ _` | |
                        | |___| | |  _ <  __/ | | | || (_| | |
                        \\____|_|_|_| \\_\\___|_| |_|\\__\\__,_|_|
                        """;
        System.out.println("Hello from\n" + logo);
    }

    public static String getUserInput(){
        System.out.println("What would you like to do?");
        System.out.println("_".repeat(60));

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        userInput = userInput.trim();

        return userInput;
    }
}
