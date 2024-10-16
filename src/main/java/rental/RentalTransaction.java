package rental;

import java.time.LocalDate;

public class RentalTransaction {
    private final String carId;
    private final String borrowerName;
    private final String nric;
    private final int duration; // Duration in days
    private final LocalDate startDate;

    public RentalTransaction(String carId, String borrowerName, String nric, int duration, LocalDate startDate) {
        this.carId = carId;
        this.borrowerName = borrowerName;
        this.nric = nric;
        this.duration = duration;
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "RentalTransaction {" +
                "Car ID='" + carId + '\'' +
                ", Borrower Name='" + borrowerName + '\'' +
                ", NRIC='" + nric + '\'' +
                ", Duration=" + duration + " days" +
                ", Start Date=" + startDate +
                '}';
    }
}
