package transcation;

import java.util.ArrayList;

public class TransactionList {
    public static final String REMOVE_TRANSACTION_FORMAT = "remove-tx /t [TRANSACTION_ID]";

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
}
