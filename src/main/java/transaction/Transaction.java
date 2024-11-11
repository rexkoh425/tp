package transaction;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static parser.TransactionParser.dateTimeFormatter;

public class Transaction {
    public static final int NUMBER_OF_PARAMETERS = 6;
    private String transactionId;
    private final String carLicensePlate;
    private final String customer;
    private final int duration;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private boolean isCompleted;

    public Transaction(String carLicensePlate, String customer, int duration,
                       LocalDate startDate) {
        this.carLicensePlate = carLicensePlate;
        this.customer = customer;
        this.duration = duration;
        this.startDate = startDate;
        this.endDate = startDate.plusDays(duration);
        this.isCompleted = false;
    }

    public Transaction(String transactionId ,String carLicensePlate, String customer, int duration, LocalDate startDate
            , boolean isCompleted) {
        this.transactionId = transactionId;
        this.carLicensePlate = carLicensePlate;
        this.customer = customer;
        this.duration = duration;
        this.startDate = startDate;
        this.endDate = startDate.plusDays(duration);
        this.isCompleted = isCompleted;
    }

   // public static void clearTransactionCounter() {
        //transactionCounter = 1;
   // }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public int getDuration() {
        return duration;
    }

    public LocalDate getStartDate() {
        return startDate;
    }


    public String getTransactionId() {
        return transactionId;
    }


    private String durationtoString(){
        if(duration == 1) {
            return " day";
        } else {
            return duration + " days";
        }
    }

    public static boolean isValidTxId(String transactionId) {
        String regex = "TX([1-9]\\d*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(transactionId);
        return matcher.matches();
    }

    @Override
    public String toString() {
        String formattedDate = startDate.format(dateTimeFormatter);
        String formattedEndDate = endDate.format(dateTimeFormatter);
        if (this.isCompleted) {
            return "[X] " + transactionId + " | " + carLicensePlate + " | " + customer + " | " +
                    durationtoString() + System.lineSeparator() + "Start Date: " + formattedDate
                    + " | End Date: " + formattedEndDate;
        } else {
            return "[ ] " + transactionId + " | " + carLicensePlate + " | " + customer + " | " +
                    durationtoString() + System.lineSeparator() + "Start Date: " + formattedDate
                    + " | End Date: " + formattedEndDate;
        }
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public String getCustomer() {
        return customer;
    }

    public String getCarLicensePlate() {
        return carLicensePlate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public String toFileString(){
        return this.getTransactionId() + " | " + this.getCarLicensePlate() + " | " + this.getCustomer() + " | " +
                this.getDuration() + " | " + this.getStartDate().format(dateTimeFormatter) + " | "
                + this.isCompleted();
    }
}
