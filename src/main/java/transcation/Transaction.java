package transcation;

public class Transaction {
    private String transcationId;
    private String carLicensePlate;
    private String borrowerName;
    private String duration;
    private String createdAt;
    public Transaction(String carLicensePlate, String borrowerName, String duration, String createdAt, String transcationId) {
        this.transcationId = transcationId  ;
        this.carLicensePlate = carLicensePlate;
        this.borrowerName = borrowerName;
        this.duration = duration;
        this.createdAt = createdAt;
    }

    public String getTranscationId() {
        return transcationId;
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
