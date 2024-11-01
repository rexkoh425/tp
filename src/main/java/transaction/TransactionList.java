package transaction;

import car.CarList;
import exceptions.CarException;
import parser.CarParser;
import java.util.ArrayList;

public class TransactionList {

    private static final ArrayList<Transaction> transactionList = new ArrayList<>();

    public static void addTx(Transaction transaction) {
        String licensePlateNumber = transaction.getCarLicensePlate();

        if (!CarParser.isValidLicensePlateNumber(licensePlateNumber)) {
            throw CarException.invalidLicensePlateNumber();
        }

        if (!CarList.isExistingLicensePlateNumber(licensePlateNumber)){
            throw CarException.licensePlateNumberNotFound();
        }
        transactionList.add(transaction);
        CarList.markCarAsRented(licensePlateNumber);
        System.out.println("Transaction added: ");
        System.out.println(transaction);
    }

    public static void addTxWithoutPrintingInfo(Transaction transaction) {
        transactionList.add(transaction);
    }

    public static void printAllTransactions() {
        int index = 1;

        if (transactionList.isEmpty()) {
            System.out.println("No transaction available.");
            return;
        }

        System.out.println("Here are all the transactions: ");

        for (Transaction transaction : transactionList) {
            System.out.println(index + ") " + transaction);
            index++;
        }
    }

    public static void printCompletedTransactions() {
        int index = 1;

        if (transactionList.isEmpty()) {
            System.out.println("No transaction available.");
            return;
        }

        System.out.println("Here are all the completed transactions: ");

        for (Transaction transaction : transactionList) {
            if(transaction.isCompleted()) {
                System.out.println(index + ") " + transaction);
                index++;
            }
        }
    }

    public static void printUncompletedTransactions() {
        int index = 1;

        if (transactionList.isEmpty()) {
            System.out.println("No transaction available.");
            return;
        }

        System.out.println("Here are all the uncompleted transactions: ");

        for (Transaction transaction : transactionList) {
            if(!transaction.isCompleted()) {
                System.out.println(index + ") " + transaction);
                index++;
            }
        }
    }

    public static void removeTxByTxId(String txId) {
        for (Transaction transaction : transactionList) {
            if (transaction.getTransactionId().toLowerCase().equals(txId)) {
                System.out.println("Transaction deleted: " + transaction);
                transactionList.remove(transaction);
                return;
            }
        }
        System.out.println("Transaction not found");
    }

    public static void findTxsByCustomer(String customer) {
        for (Transaction transaction : transactionList) {
            if (transaction.getCustomer().toLowerCase().equals(customer)) {
                System.out.println("Transaction(s) by " + customer + " found:");
                System.out.println(transaction);
            }
        }
        System.out.println("Transaction not found");
    }
    
    public static void markCompletedByTxId(String txId) {
        for (Transaction transaction : transactionList) {
            if (transaction.getTransactionId().toLowerCase().equals(txId)) {
                transaction.setCompleted(true);
                System.out.println("Transaction completed: " + transaction);
                return;
            }
        }
        System.out.println("Transaction not found");
    }

    public static void unmarkCompletedByTxId(String txId) {
        for (Transaction transaction : transactionList) {
            if (transaction.getTransactionId().toLowerCase().equals(txId)) {
                transaction.setCompleted(false);
                System.out.println("Transaction set uncompleted: " + transaction);
                return;
            }
        }
        System.out.println("Transaction not found");
    }

    public static String transactionListToFileString(){
        StringBuilder transactionData = new StringBuilder();
        for (Transaction transaction : transactionList) {
            transactionData.append(transaction.toFileString());
            transactionData.append("\n");
        }
        return transactionData.toString();
    }

    public static ArrayList<Transaction> getTransactionList(){
        return transactionList;
    }

}
