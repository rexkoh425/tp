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
        commands.put("add-tx", "Adds a new rental transaction.");
        commands.put("remove-tx", "Removes an existing rental transaction.");
        commands.put("list-tx", "Lists all transactions.");
        commands.put("exit", "Exits the program.");
    }

    // Method to parse and handle the "help" command
    public static void parseHelpCommand() {
        System.out.println("Available Commands:");
        commands.forEach((command, description) -> {
            System.out.println(command + " - " + description);
        });
    }
}
