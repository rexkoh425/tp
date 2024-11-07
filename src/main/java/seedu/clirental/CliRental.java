package seedu.clirental;

import exceptions.CarException;
import exceptions.CliRentalException;
import exceptions.CustomerException;
import parser.HelpParser;
import file.FileHandler;
import parser.Parser;

public class CliRental {
    /**
     * Main entry-point for the java.clirental.CliRental application.
     */
    public static void main(String[] args) {
        printGreetings();
        FileHandler.createAndLoadFiles();

        boolean isExit = false;

        while (!isExit) {
            try {
                String action = Parser.getUserInput();

                if (Parser.parse(action)) {
                    isExit = true;
                }

                FileHandler.updateFiles();
                Parser.printDividerLine();

            } catch (CustomerException exception){
                exception.printErrorMessage();
            } catch (NumberFormatException exception){
                System.out.println("Unable to parse input.. 1 or more invalid parameters!!");
            } catch (CarException | CliRentalException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Goodbye!");
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
        System.out.println(logo);
        System.out.println("Hello, thank you for choosing our car rental management program CliRental");
        Parser.printDividerLine();
        HelpParser.parseHelpCommand();
        Parser.printDividerLine();
    }
}
