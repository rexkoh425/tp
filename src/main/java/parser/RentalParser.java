package parser;

import rental.RentalTransaction;
import java.time.LocalDate;

public class RentalParser {

    public static RentalTransaction parseIntoRentalTransaction(String userInput) throws IllegalArgumentException {
        String[] words = userInput.split(" ");
        String carId = null;
        String borrowerName = null;
        String nric = null;
        int duration = 0;
        LocalDate startDate = null;

        try {
            for (int i = 1; i < words.length; i++) {
                switch (words[i]) {
                case "/l":
                    if (i + 1 < words.length) {
                        carId = words[++i];
                    } else {
                        throw new IllegalArgumentException("Missing car ID.");
                    }
                    break;
                case "/b":
                    if (i + 1 < words.length) {
                        // Handle cases where the borrower name may contain spaces
                        StringBuilder borrowerNameBuilder = new StringBuilder();
                        while (i + 1 < words.length && !words[i + 1].startsWith("/")) {
                            borrowerNameBuilder.append(words[++i]).append(" ");
                        }
                        borrowerName = borrowerNameBuilder.toString().trim();
                    } else {
                        throw new IllegalArgumentException("Missing borrower name.");
                    }
                    break;
                case "/n":
                    if (i + 1 < words.length) {
                        nric = words[++i];
                    } else {
                        throw new IllegalArgumentException("Missing NRIC.");
                    }
                    break;
                case "/d":
                    if (i + 1 < words.length) {
                        duration = Integer.parseInt(words[++i]);
                    } else {
                        throw new IllegalArgumentException("Missing duration.");
                    }
                    break;
                case "/c":
                    if (i + 1 < words.length) {
                        // Handle the case where the created at date is the last parameter
                        StringBuilder dateBuilder = new StringBuilder();
                        while (i + 1 < words.length && !words[i + 1].startsWith("/")) {
                            dateBuilder.append(words[++i]).append(" ");
                        }
                        String startDateStr = dateBuilder.toString().trim();

                        if (startDateStr.startsWith("\"") && startDateStr.endsWith("\"")) {
                            startDateStr = startDateStr.substring(1, startDateStr.length() - 1);
                        }

                        startDate = LocalDate.parse(startDateStr);
                    } else {
                        throw new IllegalArgumentException("Missing start date.");
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid parameter: " + words[i]);
                }
            }

            // Ensure all necessary fields are filled
            if (carId == null || borrowerName == null || nric == null || duration <= 0 || startDate == null) {
                throw new IllegalArgumentException("Missing or incorrect transaction data.");
            }

            return new RentalTransaction(carId, borrowerName, nric, duration, startDate);

        } catch (Exception e) {
            throw new IllegalArgumentException("Error parsing rental transaction: " + e.getMessage());
        }
    }
}
