package file;

import java.io.IOException;
import java.io.File;

public class FileHandler {

    private static final String DIR_NAME = "data";
    private static final String CAR_DATA_FILENAME = "carData.txt";
    private static final String CAR_DATA_FILEPATH = DIR_NAME + "/" + CAR_DATA_FILENAME;
    private static final String CUSTOMER_DATA_FILENAME = "customerData.txt";
    private static final String CUSTOMER_DATA_FILEPATH = DIR_NAME + "/" + CUSTOMER_DATA_FILENAME;
    private static final String TRANSACTION_DATA_FILENAME = "transactionData.txt";
    private static final String TRANSACTION_DATA_FILEPATH = DIR_NAME + "/" + TRANSACTION_DATA_FILENAME;

    private static final File DATA_DIR = new File(DIR_NAME);
    private static final File CAR_DATA_FILE = new File(CAR_DATA_FILEPATH);
    private static final File CUSTOMER_DATA_FILE = new File(CUSTOMER_DATA_FILEPATH);
    private static final File TRANSACTION_DATA_FILE = new File(TRANSACTION_DATA_FILEPATH);

    public FileHandler(){
        createFolderIfNotExist();
        createCarFileIfNotExist();
        createCustomerFileIfNotExist();
        createTransactionFileIfNotExist();
    }

    private static void createCarFileIfNotExist(){
        if(!CAR_DATA_FILE.exists()){
            createNewFile(CAR_DATA_FILE);
        }
    }

    private static void createCustomerFileIfNotExist(){
        if(!CUSTOMER_DATA_FILE.exists()){
            createNewFile(CUSTOMER_DATA_FILE);
        }
    }

    private static void createTransactionFileIfNotExist(){
        if(!TRANSACTION_DATA_FILE.exists()){
            createNewFile(TRANSACTION_DATA_FILE);
        }
    }

    private static void createNewFile(File filename) {

        try {
            if (filename.createNewFile()) {
                System.out.println("File created successfully: " + filename.getAbsolutePath());
            } else {
                System.out.println("File already exists.");
            }

        } catch (IOException exception) {
            System.err.println("Failed to create file: " + exception.getMessage());
        }
    }

    private static void createFolderIfNotExist(){

        if (!DATA_DIR.isDirectory()) {
            System.out.println(DIR_NAME + " does not exist. Creating it now.......");
            createFolder();
        }
    }

    private static void createFolder() {

        boolean isCreated = DATA_DIR.mkdirs();

        if (isCreated) {
            System.out.println(DIR_NAME + " directory created successfully.");
        } else {
            System.out.println(DIR_NAME + " directory already exists or failed to create.");
        }
    }

}
