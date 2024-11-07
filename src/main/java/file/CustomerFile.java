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
    private final String customerDataFileName;
    private final String customerDataFilePath ;
    private final File customerDataFile;

    public CustomerFile(){
        this.customerDataFileName = "customerData.txt";
        this.customerDataFilePath = FileHandler.getDirName() + "/" + customerDataFileName;
        this.customerDataFile = new File(customerDataFilePath);
    }

    public CustomerFile(String filename){
        this.customerDataFileName = filename;
        this.customerDataFilePath = FileHandler.getDirName() + "/" + customerDataFileName;
        this.customerDataFile = new File(customerDataFilePath);
    }

    public String getCustomerDataFilename() {
        return this.customerDataFileName;
    }

    public void createCustomerFileIfNotExist(){
        if(!this.customerDataFile.exists()){
            FileHandler.createNewFile(this.customerDataFile);
        }
    }

    /**
     * Reads every line in the customerData.txt file and add it to the current customer list.
     *
     * @throws FileNotFoundException if customerData.txt does not exist.
     * @throws CustomerException if there is corruption in file data.
     */
    public void loadCustomerData() throws FileNotFoundException, CustomerException {
        if(this.customerDataFile.exists()){
            Scanner scanner = new Scanner(this.customerDataFile);
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
    public void scanLineAndAddCustomer(Scanner scanner, ArrayList<Integer> errorLines, int line) {
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
    public void updateCustomerDataFile() throws IOException {
        FileWriter fw = new FileWriter(this.customerDataFile);
        String textToAdd = CustomerList.customerListToFileString();
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Add customer object to the list according to the parameters.
     *
     * @param parameters parameters of the Customer object.
     */
    public void addCustomerWithParameters(String[] parameters, ArrayList<Integer> errorLines , int line) {
        assert parameters.length == 3 : "Parameter for customers is wrong";
        String customerName = parameters[0];
        try {
            int age = Integer.parseInt(parameters[1]);
            String contactNumber = parameters[2];
            Customer customer = new Customer(customerName , age , contactNumber);
            CustomerList.addCustomerWithoutPrintingInfo(customer);
        }catch(NumberFormatException e) {
            errorLines.add(line);
        }
    }

    /**
     * Loads data from customerData.txt if the file exist.
     */
    public void loadCustomerDataIfExist(){
        try {
            loadCustomerData();
        } catch (FileNotFoundException e) {
            System.out.println("customerData.txt not found in data directory. Please try again");
        } catch (CustomerException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getAbsolutePath(){
        return this.customerDataFile.getAbsolutePath();
    }
}
