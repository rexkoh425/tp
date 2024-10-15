package parser;

public class CarParser {
    private static String extractCarModel(String userInput) {
        int startIndexOfCarModel = userInput.indexOf(ADD_CAR_PARAMETERS[0]) + ADD_CAR_PARAMETERS_OFFSET;
        int endIndexOfCarModel = userInput.indexOf(ADD_CAR_PARAMETERS[1]);

        String carModel = userInput.substring(startIndexOfCarModel, endIndexOfCarModel);
        if (carModel.trim().isEmpty()) {
            throw new CarException("Car model missing!!");
        }

        return carModel;
    }

    private static String extractCarLicensePlateNumber(String userInput) {
        int startIndexOfLicensePlateNumber = userInput.indexOf(ADD_CAR_PARAMETERS[1])
                + ADD_CAR_PARAMETERS_OFFSET;
        int endIndexOfLicensePlateNumber = userInput.indexOf(ADD_CAR_PARAMETERS[2]);

        String carLicensePlateNumber = userInput.substring(startIndexOfLicensePlateNumber,
                endIndexOfLicensePlateNumber);
        if (carLicensePlateNumber.trim().isEmpty()) {
            throw new CarException("License plate number missing!!");
        }

        return carLicensePlateNumber;
    }

    public static boolean isValidFormat(String userInput) {
        for (String param : ADD_CAR_PARAMETERS) {
            if (!userInput.contains(param)) {
                return false;
            }
        }

        for (int i = 0; i < ADD_CAR_PARAMETERS.length - 1; i++) {
            if (userInput.indexOf(ADD_CAR_PARAMETERS[i]) > userInput.indexOf(ADD_CAR_PARAMETERS[i+1])) {
                return false;
            }
        }

        return true;
    }
}
