package exceptions;

import java.util.ArrayList;

/**
 * Customer related exceptions
 */
public class CustomerException extends RuntimeException {

    public static final String ADD_FORMAT = "add-user /u [USERNAME] /a [AGE] /c [CONTACT_NUMBER]";

    public CustomerException(String message) {
        super(message);
    }

    public void printErrorMessage(){
        System.out.println(getMessage());
    }

    /**
     * Exception for the add-user command
     */
    public static CustomerException addCustomerException(){
        return new CustomerException("Unable to add customer. Please follow : " + ADD_FORMAT);
    }

    public static CustomerException invalidParameters(ArrayList<Integer> errorLines){
        String message = "Customer data do not match number of parameters in " + errorLines.size() + " rows of data\n";
        message += "Rows are : ";
        message += errorLines.toString();
        message = message + "\n";
        return new CustomerException(message);
    }
}
