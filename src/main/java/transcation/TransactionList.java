package transcation;

import java.time.LocalDate;
import java.util.ArrayList;

public class TransactionList {
    public static final String REMOVE_TRANSACTION_FORMAT = "remove-tx /t [TRANSACTION_ID]";
    public static final String ADD_TRANSACTION_FORMAT = "add-tx /l [CAR_LICENSE_PLATE] /b [BORROWER_NAME] /d [DURATION] /c [START_DATE]";
    private static int transactionCounter = 1;
    private static final ArrayList<Transaction> transactionList = new ArrayList<>();

    public static void removeTransaction(String userInput) {

        String[] words = userInput.split(" ");
        if ( (words.length < 2) || (!words[1].equals("/t")) ) {
            throw new IllegalStateException("Unable to add customer. Please follow : " + REMOVE_TRANSACTION_FORMAT);
        }
        if(!removeTransactionById(words[3])) {
            throw new RuntimeException("Transaction ID doesn't exist");
        }
    }

    public static boolean removeTransactionById(String transactionId) {
        for (Transaction transaction : transactionList) {
            if (transaction.getTransactionId().equals(transactionId)) {
                transactionList.remove(transaction);
                return true;
            }
        }
        return false;
    }

    public static void addTransaction(String[] args) {
        String carLicensePlate = null;
        String borrowerName = null;
        int duration = 0;
        LocalDate startDate = null;

        try {
            for (int i = 1; i < args.length; i++) {
                switch (args[i]) {
                case "/l":
                    carLicensePlate = args[++i];
                    break;
                case "/b":
                    StringBuilder borrowerNameBuilder = new StringBuilder();
                    while (i + 1 < args.length && !args[i + 1].startsWith("/")) {
                        borrowerNameBuilder.append(args[++i]).append(" ");
                    }
                    borrowerName = borrowerNameBuilder.toString().trim();
                    break;
                case "/d":
                    duration = Integer.parseInt(args[++i]);
                    break;
                case "/c":
                    String startDateStr = args[++i];
                    startDate = LocalDate.parse(startDateStr.replace("\"", "")); // Remove quotes if present
                    break;
                default:
                    throw new IllegalArgumentException("Invalid parameter: " + args[i]);
                }
            }

            if (carLicensePlate != null && borrowerName != null && duration > 0 && startDate != null) {
                String transactionId = "TX" + (++transactionCounter);
                String createdAt = startDate.toString();
                Transaction transaction = new Transaction(carLicensePlate, borrowerName, String.valueOf(duration), createdAt, transactionId);
                transactionList.add(transaction);
                System.out.println("Transaction added successfully: " + transaction);
            } else {
                throw new IllegalArgumentException("Missing or incorrect transaction data.");
            }

        } catch (Exception e) {
            System.out.println("Error adding transaction: " + e.getMessage());
          
    public static void printAllTransactions() {
        int index = 1;

        System.out.println("Here are all the transactions: ");

        for (Transaction transaction : transactionList) {
            System.out.println(index + ") " + transaction.getCarLicensePlate()
                    + " | " + transaction.getBorrowerName()
                    + " | " + transaction.getDuration()
                    + " | " + transaction.getCreatedAt());
            index++;
        }
    }
}
