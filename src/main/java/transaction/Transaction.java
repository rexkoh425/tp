package transaction;

public class Transaction {
    private static int transactionCounter = 1;
    private final String transactionId;
    private final String carLicensePlate;
    private final String customer;
    private final String duration;
    private final String startDate;
    private boolean isCompleted;



    public Transaction(String carLicensePlate, String customer, String duration,
                       String startDate) {
        this.transactionId = "TX" + transactionCounter++;
        this.carLicensePlate = carLicensePlate;
        this.customer = customer;
        this.duration = duration;
        this.startDate = startDate;
        this.isCompleted = false;
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

}
