package parser;

import java.util.LinkedHashMap;
import java.util.Map;

public class HelpParser {

    // Command descriptions stored in a map
    private static final Map<String, String> commands = new LinkedHashMap<>();

    static {
        commands.put("help", "Provides a list of all commands and their descriptions.");
        commands.put("add-user", "Adds a new customer to the system.");
        commands.put("remove-user", "Removes a customer from the system.");
        commands.put("list-users", "Lists all customers.");
        commands.put("add-car", "Adds a new car to the fleet.");
        commands.put("remove-car", "Removes a car from the fleet.");
        commands.put("list-cars", "Lists all rented-out cars.");
        commands.put("add-tx /p [CAR_LICENSE_PLATE] /u [BORROWER_NAME] /d [DURATION] /s [START_DATE dd-MM-yyyy]",
                "Adds a new rental transaction.");
        commands.put("mark-tx /t [Transaction ID]", "Marks a rental transaction completed.");
        commands.put("unmark-tx /t [Transaction ID]", "Unmarks a rental transaction.");
        commands.put("remove-tx /t [Transaction ID]", "Removes an existing rental transaction.");
        commands.put("list-tx", "Lists all transactions.");
        commands.put("list-tx-completed", "Lists all completed transactions.");
        commands.put("list-tx-uncompleted", "Lists all uncompleted transactions.");
        commands.put("find-tx-by-customer /u [CUSTOMER_NAME]", "Finds transactions by a customer's name.");
        commands.put("exit", "Exits the program.");
    }

    // Method to parse and handle the "help" command
    public static void parseHelpCommand() {
        System.out.println("Available Commands:");
        commands.forEach((command, description) -> System.out.println(command + " - " + description));
    }
}
