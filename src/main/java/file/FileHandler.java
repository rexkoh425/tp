package file;

import java.io.IOException;
import java.io.File;

public class FileHandler {
    private static final String DIR_NAME = "data";
    private static final String CAR_DATA_FILENAME = "carData.txt";
    private static final String CAR_DATA_FILEPATH = DIR_NAME + "/" + CAR_DATA_FILENAME;

    public FileHandler(){
        createFolderIfNotExist();
        createCarFileIfNotExist();
    }

    public static void createCarFileIfNotExist(){
        File file = new File(CAR_DATA_FILEPATH);
        if(!file.exists()){
            createNewFile(file);
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

        File dataDirectory = new File(DIR_NAME);

        if (!dataDirectory.isDirectory()) {
            System.out.println(DIR_NAME + " does not exist. Creating it now.......");
            createFolder(dataDirectory);
        }
    }

    private static void createFolder(File directoryName) {

        boolean isCreated = directoryName.mkdirs();

        if (isCreated) {
            System.out.println(directoryName + " directory created successfully.");
        } else {
            System.out.println(directoryName + " directory already exists or failed to create.");
        }
    }
}
