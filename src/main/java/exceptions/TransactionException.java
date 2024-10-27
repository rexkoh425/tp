package exceptions;

import java.util.ArrayList;

public class TransactionException extends RuntimeException {
    public TransactionException(String message) {
        super(message);
    }

    public void printErrorMessage(){
        System.out.println(getMessage());
    }

    public static TransactionException invalidParameters(ArrayList<Integer> errorLines){
        String message = "Transaction data do not match number of parameters in "
                + errorLines.size() + " rows of data\n";
        message += "Rows are : ";
        message += errorLines.toString();
        message = message + "\n";
        return new TransactionException(message);
    }
}
