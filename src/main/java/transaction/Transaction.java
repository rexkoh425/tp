package transaction;

import java.time.LocalDate;

public class Transaction {
    public static final int NUMBER_OF_PARAMETERS = 4;
    private static int transactionCounter = 1;
    private final String transactionId;
    private final String carLicensePlate;
    private final String customer;
    private final int duration;
    private final LocalDate startDate;
    private boolean isCompleted;

    public Transaction(String carLicensePlate, String customer, int duration,
                       LocalDate startDate) {
        this.transactionId = "TX" + transactionCounter++;
        this.carLicensePlate = carLicensePlate;
        this.customer = customer;
        this.duration = duration;
        this.startDate = startDate;
        this.isCompleted = false;
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

    @Override
    public String toString() {
        if (this.isCompleted) {
            return "[X] " + transactionId + " | " + carLicensePlate + " | " + customer + " | " +
                    duration + "day(s) " + '\n' + "Start Date: " + startDate;
        } else {
            return "[ ] " + transactionId + " | " + carLicensePlate + " | " + customer + " | " +
                    duration + "day(s) " + '\n' + "Start Date: " + startDate;
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
        return this.getCarLicensePlate() + " | " + this.getCustomer()
                + " | " + this.getDuration() + " | " + this.getStartDate();
    }
}
