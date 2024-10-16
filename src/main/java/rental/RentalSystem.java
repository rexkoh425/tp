package rental;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RentalSystem {
    private static final List<RentalTransaction> transactions = new ArrayList<>();

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide a command.");
            return;
        }

        String command = args[0];

        if ("add-tx".equals(command)) {
            addTransaction(args);
        } else {
            System.out.println("Unknown command.");
        }
    }

    private static void addTransaction(String[] args) {
        String carId = null;
        String borrowerName = null;
        String nric = null;
        int duration = 0;
        LocalDate startDate = null;

        try {
            for (int i = 1; i < args.length; i++) {
                switch (args[i]) {
                case "/l":
                    carId = args[++i];
                    break;
                case "/b":
                    borrowerName = args[++i];
                    break;
                case "/n":
                    nric = args[++i];
                    break;
                case "/d":
                    duration = Integer.parseInt(args[++i]);
                    break;
                case "/c":
                    String startDateStr = args[++i];
                    startDate = LocalDate.parse(startDateStr);
                    break;
                default:
                    System.out.println("Invalid parameter: " + args[i]);
                    return;
                }
            }

            if (carId != null && borrowerName != null && nric != null && duration > 0 && startDate != null) {
                RentalTransaction transaction = new RentalTransaction(carId, borrowerName, nric, duration, startDate);
                transactions.add(transaction);
                System.out.println("Transaction added successfully.");
                System.out.println(transaction);
            } else {
                System.out.println("Missing or incorrect transaction data.");
            }

        } catch (Exception e) {
            System.out.println("Error processing transaction: " + e.getMessage());
        }
    }
}
