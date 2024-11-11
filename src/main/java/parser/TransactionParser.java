package parser;

import car.CarList;
import customer.CustomerList;
import exceptions.CarException;
import transaction.Transaction;
import transaction.TransactionList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static parser.Parser.ADD_TRANSACTION_COMMAND;

public class TransactionParser {
    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static final String ADD_TRANSACTION_FORMAT = "add-tx /c [LICENSE_PLATE_NUMBER]" +
            "/u [CUSTOMER_NAME] /d [DURATION] /s [START_DATE dd-MM-yyyy]";
    public static final String FIND_TRANSACTION_BY_CUSTOMER_FORMAT = "find-txs-by-customer /u [CUSTOMER_NAME]";
    public static final String REMOVE_TRANSACTION_FORMAT = "remove-tx /t [TRANSACTION_ID]";
    public static final String MARK_TRANSACTION_FORMAT = "mark-tx /t [TRANSACTION_ID]";
    public static final String UNMARK_TRANSACTION_FORMAT = "unmark-tx /t [TRANSACTION_ID]";



    public static Transaction parseIntoTransaction(String userInput) throws IllegalArgumentException {
        userInput = userInput.substring(ADD_TRANSACTION_COMMAND.length()).trim();
        String[] parameters = { "/c", "/u", "/d", "/s" };
        String[] parameterContents;

        if (isValidSequence(parameters, userInput)) {
            parameterContents = parseParameterContents(parameters, userInput);
        } else {
            throw new IllegalArgumentException("Invalid command format for adding a transaction. " +
                    "Refer to the format below: " + System.lineSeparator() + ADD_TRANSACTION_FORMAT);
        }

        String carLicensePlate = parameterContents[0];
        if (!CarParser.isValidLicensePlateNumber(carLicensePlate)) {
            throw CarException.invalidLicensePlateNumber();
        }
        if (!CarList.isExistingLicensePlateNumber(carLicensePlate)) {
            throw CarException.licensePlateNumberNotFound();
        }

        String customerName = parameterContents[1];
        if (!CustomerList.isExistingCustomer(customerName)) {
            throw new IllegalArgumentException("Customer " + customerName + " does not exist.");
        }

        int duration;
        try {
            duration = Integer.parseInt(parameterContents[2]);
            if (duration < 1 || duration > 365) {
                throw new IllegalArgumentException("Invalid duration. Duration must be between 1 and 365 days.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid duration format. Duration must be an integer.");
        }

        LocalDate startDate;
        String dateStr = parameterContents[3];
        try {
            dateTimeFormatter.parse(dateStr);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format for startDate. Date format: dd-MM-yyyy.");
        }
        try {
            startDate = LocalDate.parse(dateStr, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("The specified startDate does not exist in the calendar.");
        }

        return new Transaction(carLicensePlate.toUpperCase(), customerName, duration,
                startDate);
    }

    private static boolean isValidSequence(String[] parameters, String userInput) {
        StringBuilder patternBuilder = new StringBuilder();

        for (int i = 0; i < parameters.length; i++) {
            patternBuilder.append(parameters[i]).append("\\s+"); // Parameter followed by space(s)

            if (i < parameters.length - 1) {
                // Match content with spaces until the next parameter
                patternBuilder.append("([^/]+?)\\s+"); // Non-empty content until the next parameter
            } else {
                // Last parameter, capture until the end without requiring trailing space
                patternBuilder.append("(.+)$");
            }
        }

        // Build the final regex pattern
        String pattern = patternBuilder.toString();

        // Match userInput against the generated regex pattern
        return userInput.matches(pattern);
    }

    private static String[] parseParameterContents(String[] parameters, String userInput) {
        String[] contents = new String[parameters.length];

        for (int i = 0; i < parameters.length; i++) {
            int startIndex = userInput.indexOf(parameters[i]) + parameters[i].length();

            // Find the end of this parameter's value by locating the next parameter or end of the string
            int endIndex = userInput.length();
            for (int j = i + 1; j < parameters.length; j++) {
                int nextParamIndex = userInput.indexOf(parameters[j], startIndex);
                if (nextParamIndex != -1) {
                    endIndex = nextParamIndex;
                    break;
                }
            }

            // Extract the parameter content and trim whitespace
            contents[i] = userInput.substring(startIndex, endIndex).trim();

            // Validate that content is not missing
            if (contents[i].isEmpty()) {
                throw new IllegalArgumentException("Missing value for parameter: " + parameters[i]);
            }
        }
        return contents;
    }

    public static void parseFindTxsByCustomer(String userInput) {
        String[] words = userInput.split(" ",3);
        if (words.length < 3 || !words[1].equals("/u")) {
            System.out.println("Unable to search for transaction. Refer to correct format below:");
            System.out.println(FIND_TRANSACTION_BY_CUSTOMER_FORMAT);
            return;
        }
        TransactionList.findTxsByCustomer(words[2].toLowerCase());
    }

    public static void parseRemoveTx(String userInput){
        String[] words = userInput.split(" ",3);
        if (words.length < 3 || !words[1].equals("/t") || !words[2].toLowerCase().startsWith("tx")) {
            System.out.println("Unable to remove transaction. Refer to correct format below:");
            System.out.println(REMOVE_TRANSACTION_FORMAT);
            return;
        }
        TransactionList.removeTxByTxId(words[2].toLowerCase());
    }

    public static void parseMarkCompleted(String userInput){
        String[] words = userInput.split(" ",3);
        if (words.length < 3 || !words[1].equals("/t") || !words[2].toLowerCase().startsWith("tx")) {
            System.out.println("Unable to mark transaction. Refer to correct format below:");
            System.out.println(MARK_TRANSACTION_FORMAT);
            return;
        }
        TransactionList.markCompletedByTxId(words[2].toLowerCase());
    }

    public static void parseUnmarkCompleted(String userInput){
        String[] words = userInput.split(" ",3);
        if (words.length < 3 || !words[1].equals("/t") || !words[2].toLowerCase().startsWith("tx")) {
            System.out.println("Unable to unmark transaction. Refer to correct format below:");
            System.out.println(UNMARK_TRANSACTION_FORMAT);
            return;
        }
        TransactionList.unmarkCompletedByTxId(words[2].toLowerCase());
    }

}
