package transaction;

import car.CarList;

import exceptions.CarException;
import parser.CarParser;
import java.time.LocalDate;
import java.util.ArrayList;

public class TransactionList {
    // Ensure the transaction list is initialized properly
    private static final ArrayList<Transaction> transactionList = new ArrayList<>();
    private static int txCounter = 1;

    public static void setTxCounter(int counter) {
        if (counter > txCounter) {
            txCounter = counter;
        }
    }

    public static void addTx(Transaction transaction) {
        // Assert that the transaction is not null
        assert transaction != null : "Transaction to add should not be null.";

        String licensePlateNumber = transaction.getCarLicensePlate();
        String customerName = transaction.getCustomer();

        // Assert that the license plate number is not null
        assert licensePlateNumber != null : "License plate number should not be null.";

        if (!CarParser.isValidLicensePlateNumber(licensePlateNumber)) {
            throw CarException.invalidLicensePlateNumber();
        }

        if (!CarList.isExistingLicensePlateNumber(licensePlateNumber)){
            throw CarException.licensePlateNumberNotFound();
        }

        for (Transaction tx : transactionList) {
            boolean isCarInTxList = tx.getCarLicensePlate().equals(licensePlateNumber);
            boolean isCustomerInTxList = tx.getCustomer().equalsIgnoreCase(customerName);
            boolean doDatesOverlap =  datesOverlap(tx.getStartDate(), tx.getEndDate(),
                    transaction.getStartDate(), transaction.getEndDate());

            if (isCarInTxList && doDatesOverlap) {
                System.out.println("Car " + licensePlateNumber +
                        " is already rented during this period. Transaction not added.");
                return;
            }

            if (isCustomerInTxList && doDatesOverlap) {
                System.out.println("Customer " + customerName +
                        " already has a rental during this period. Transaction not added.");
                return;
            }
        }

        // Assert that the license plate number is valid and exists before adding
        assert CarParser.isValidLicensePlateNumber(licensePlateNumber)
                && CarList.isExistingLicensePlateNumber(licensePlateNumber)
                : "License plate number must be valid and exist in CarList.";

        String newTransactionId = "TX" + txCounter++;
        transaction.setTransactionId(newTransactionId);
        transactionList.add(transaction);

        // Assert that the transaction was added successfully
        assert transactionList.contains(transaction) : "Transaction was not added to the list.";

        CarList.markCarAsRented(licensePlateNumber);
        System.out.println("Transaction added: ");
        System.out.println(transaction);
    }

    public static void addTxWithoutPrintingInfo(Transaction transaction) throws CarException{

        String licensePlateNumber = transaction.getCarLicensePlate();

        if (!CarParser.isValidLicensePlateNumber(licensePlateNumber)) {
            throw CarException.invalidLicensePlateNumber();
        }

        String newTransactionId = "TX" + txCounter++;
        transaction.setTransactionId(newTransactionId);
        transactionList.add(transaction);
        CarList.markCarAsRented(licensePlateNumber);

    }

    public static void printAllTransactions() {
        int index = 1;

        if (transactionList.isEmpty()) {
            System.out.println("No transaction available.");
            return;
        }

        System.out.println("Here are all the transactions: ");

        for (Transaction transaction : transactionList) {
            // Assert that each transaction is not null
            assert transaction != null : "Transaction in the list should not be null.";
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

        boolean containsCompletedTx = false;

        for (Transaction transaction : transactionList) {
            // Assert that each transaction is not null
            assert transaction != null : "Transaction in the list should not be null.";
            if(transaction.isCompleted()) {
                System.out.println(index + ") " + transaction);
                index++;
                containsCompletedTx = true;
            }
        }

        if (!containsCompletedTx) {
            System.out.println("No completed transaction available.");
        }
    }

    public static void printUncompletedTransactions() {
        int index = 1;

        if (transactionList.isEmpty()) {
            System.out.println("No transaction available.");
            return;
        }

        boolean containsUncompletedTx = false;

        System.out.println("Here are all the uncompleted transactions: ");

        for (Transaction transaction : transactionList) {
            // Assert that each transaction is not null
            assert transaction != null : "Transaction in the list should not be null.";
            if(!transaction.isCompleted()) {
                System.out.println(index + ") " + transaction);
                index++;
                containsUncompletedTx = true;
            }
        }

        if (!containsUncompletedTx) {
            System.out.println("No uncompleted transaction available.");
        }
    }

    public static void removeTxByTxId(String txId) {
        // Assert that txId is not null
        assert txId != null : "Transaction ID to remove should not be null.";

        for (Transaction transaction : transactionList) {
            // Assert that each transaction is not null
            assert transaction != null : "Transaction in the list should not be null.";
            if (transaction.getTransactionId().equalsIgnoreCase(txId)) {
                System.out.println("Transaction deleted: " + transaction);
                transactionList.remove(transaction);
                CarList.markCarAsAvailable(transaction.getCarLicensePlate());

                // Assert that the transaction was removed successfully
                assert !transactionList.contains(transaction) : "Transaction was not removed from the list.";
                return;
            }
        }
        System.out.println("Transaction not found");
    }

    public static void removeAllTxs() {
        transactionList.clear();
        System.out.println("All transactions removed!!!");
    }

    public static void findTxsByCustomer(String customer) {
        // Assert that customer is not null
        assert customer != null : "Customer name to find transactions should not be null.";

        boolean found = false;
        System.out.println("Transaction(s) by " + customer + " found:");
        for (Transaction transaction : transactionList) {
            // Assert that each transaction is not null
            assert transaction != null : "Transaction in the list should not be null.";
            if (transaction.getCustomer().toLowerCase().contains(customer)) {
                found = true;
                System.out.println(transaction);
            }
        }
        if(!found) {
            System.out.println("none");
        }
    }

    public static void markCompletedByTxId(String txId) {
        // Assert that txId is not null
        assert txId != null : "Transaction ID to mark as completed should not be null.";

        for (Transaction transaction : transactionList) {
            // Assert that each transaction is not null
            assert transaction != null : "Transaction in the list should not be null.";
            if (transaction.getTransactionId().equalsIgnoreCase(txId)) {
                transaction.setCompleted(true);
                CarList.markCarAsAvailable(transaction.getCarLicensePlate());
                System.out.println("Transaction completed: " + transaction);

                // Assert that the transaction is marked as completed
                assert transaction.isCompleted() : "Transaction was not marked as completed.";
                return;
            }
        }
        System.out.println("Transaction not found");
    }

    public static void unmarkCompletedByTxId(String txId) {
        // Assert that txId is not null
        assert txId != null : "Transaction ID to unmark as completed should not be null.";

        for (Transaction transaction : transactionList) {
            // Assert that each transaction is not null
            assert transaction != null : "Transaction in the list should not be null.";
            if (transaction.getTransactionId().equalsIgnoreCase(txId)) {
                transaction.setCompleted(false);
                CarList.markCarAsRented(transaction.getCarLicensePlate());
                System.out.println("Transaction set uncompleted: " + transaction);

                // Assert that the transaction is marked as uncompleted
                assert !transaction.isCompleted() : "Transaction was not unmarked as completed.";
                return;
            }
        }
        System.out.println("Transaction not found");
    }

    public static String transactionListToFileString(){

        StringBuilder transactionData = new StringBuilder();
        for (Transaction transaction : transactionList) {

            assert transaction != null : "Transaction in the list should not be null.";
            transactionData.append(transaction.toFileString());
            transactionData.append(System.lineSeparator());
        }
        return transactionData.toString();
    }

    public static ArrayList<Transaction> getTransactionList(){
        return transactionList;
    }


    public static void clearTransactionList(){
        transactionList.clear();
    }
  
    private static boolean datesOverlap(LocalDate start1, LocalDate end1,
                                        LocalDate start2, LocalDate end2) {
        return (start1.isBefore(end2) && end1.isAfter(start2)) || start1.equals(start2)
                || end1.equals(end2);
    }

}
