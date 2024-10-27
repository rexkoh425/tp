package file;

import customer.Customer;
import customer.CustomerList;
import exceptions.CustomerException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles file operation for the customer.
 */
public class CustomerFile {
    private static final String CUSTOMER_DATA_FILENAME = "customerData.txt";
    private static final String CUSTOMER_DATA_FILEPATH = FileHandler.getDirName() + "/" + CUSTOMER_DATA_FILENAME;
    private static final File CUSTOMER_DATA_FILE = new File(CUSTOMER_DATA_FILEPATH);

    public static String getCustomerDataFilename() {
        return CUSTOMER_DATA_FILENAME;
    }

    public static void createCustomerFileIfNotExist(){
        if(!CUSTOMER_DATA_FILE.exists()){
            FileHandler.createNewFile(CUSTOMER_DATA_FILE);
        }
    }

    /**
     * Reads every line in the customerData.txt file and add it to the current customer list.
     *
     * @throws FileNotFoundException if customerData.txt does not exist.
     * @throws CustomerException if there is corruption in file data.
     */
    private static void loadCustomerData() throws FileNotFoundException, CustomerException {
        if(CUSTOMER_DATA_FILE.exists()){
            Scanner scanner = new Scanner(CUSTOMER_DATA_FILE);
            ArrayList<Integer> errorLines = new ArrayList<>();
            int line = 1;
            while (scanner.hasNext()) {
                scanLineAndAddCustomer(scanner, errorLines, line);
                line ++;
            }
            if(!errorLines.isEmpty()) {
                throw CustomerException.invalidParameters(errorLines);
            }
        }
    }

    /**
     * Scans the current line and add data to current customer list.
     *
     * @param errorLines List of line number which the data were wrongly formatted.
     * @param line the current line number.
     */
    private static void scanLineAndAddCustomer(Scanner scanner, ArrayList<Integer> errorLines, int line) {
        String input = scanner.nextLine();
        String[] parameters = input.split(" \\| ");
        if(parameters.length != Customer.NUMBER_OF_PARAMETERS){
            errorLines.add(line);
        }else{
            addCustomerWithParameters(parameters, errorLines ,line);
        }
    }

    /**
     * Reads the current customer list and updates customerData.txt file.
     *
     * @throws IOException File does not exist.
     */
    public static void updateCustomerDataFile() throws IOException {
        FileWriter fw = new FileWriter(CUSTOMER_DATA_FILE);
        String textToAdd = CustomerList.customerListToFileString();
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Add customer object to the list according to the parameters.
     *
     * @param parameters parameters of the Customer object.
     */
    private static void addCustomerWithParameters(String[] parameters, ArrayList<Integer> errorLines , int line) {
        String username = parameters[0];
        try {
            int age = Integer.parseInt(parameters[1]);
            String contactNumber = parameters[2];
            Customer customer = new Customer(username , age , contactNumber);
            CustomerList.addCustomerWithoutPrintingInfo(customer);
        }catch(NumberFormatException e) {
            errorLines.add(line);
        }
    }

    /**
     * Loads data from customerData.txt if the file exist.
     */
    public static void loadCustomerDataIfExist(){
        try {
            loadCustomerData();
        } catch (FileNotFoundException e) {
            System.out.println("customerData.txt not found in data directory. Please try again");
        } catch (CustomerException e) {
            System.out.println(e.getMessage());
        }
    }
}
