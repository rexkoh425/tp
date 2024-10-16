package transaction;

public class Transaction {
    public static String transactionId;
    private String carLicensePlate;
    private String borrowerName;
    private String duration;
    private String createdAt;
    public Transaction(String carLicensePlate, String borrowerName, String duration,
                       String createdAt, String transactionId) {
        this.transactionId = transactionId;
        this.carLicensePlate = carLicensePlate;
        this.borrowerName = borrowerName;
        this.duration = duration;
        this.createdAt = createdAt;
    }

    public static String getTransactionId() {
        return transactionId;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "carLicensePlate='" + carLicensePlate + '\'' +
                ", borrowerName='" + borrowerName + '\'' +
                ", duration='" + duration + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getDuration() {
        return duration;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public String getCarLicensePlate() {
        return carLicensePlate;
    }
}
