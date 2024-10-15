package seedu.duke;

import exceptions.CarException;
import parser.Parser;

import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {

        boolean isExit = false;

        while (!isExit) {
            try {
                String userInput = getUserInput();

                if (Parser.parse(userInput)) {
                    isExit = true;
                };

            } catch (CarException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public static String getUserInput() {
        System.out.print("Enter command: ");

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine().trim();

        return userInput;
    }
}
