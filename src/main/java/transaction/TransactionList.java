package transaction;

import car.CarList;
import exceptions.CarException;

import java.util.ArrayList;

public class TransactionList {
    public static final String REMOVE_TRANSACTION_FORMAT = "remove-tx /t [TRANSACTION_ID]";
    private static final ArrayList<Transaction> transactionList = new ArrayList<>();

    public static void addTx(Transaction transaction) {
        String licensePlateNumber = transaction.getCarLicensePlate();
        if (!CarList.isExistingLicensePlateNumber(licensePlateNumber)){
            throw CarException.licensePlateNumberNotFound();
        }
        transactionList.add(transaction);
        CarList.markCarAsRented(licensePlateNumber);
        System.out.println("Transaction added: ");
        System.out.println(transaction);
    }
          
    public static void printAllTransactions() {
        int index = 1;

        if (transactionList.isEmpty()) {
            System.out.println("No transaction available.");
            return;
        }

        System.out.println("Here are all the transactions: ");

        for (Transaction transaction : transactionList) {
            System.out.println(index + ") " + transaction.getTransactionId()
                    + " | " + transaction.getCarLicensePlate()
                    + " | " + transaction.getBorrowerName()
                    + " | " + transaction.getDuration()
                    + " | " + transaction.getStartDate());
            index++;
        }
    }

    public static void removeTx(String userInput){
        String[] words = userInput.split(" ",3);
        if (words.length < 3 || words[1] != "/t" || !words[2].toLowerCase().startsWith("tx")) {
            System.out.println("Unable to remove transaction. Refer to correct format below:");
            System.out.println(REMOVE_TRANSACTION_FORMAT);
            return;
        }
        removeTxByTxId(words[2].toLowerCase());
    }

    public static void removeTxByTxId(String txId) {
        for (Transaction transaction : transactionList) {
            if (transaction.getTransactionId().toLowerCase().equals(txId)) {
                System.out.println(new StringBuilder().append("Transaction deleted: ").append(transaction).toString());
                transactionList.remove(transaction);
                return;
            }
        }
        System.out.println("Transaction not found");
    }
}
