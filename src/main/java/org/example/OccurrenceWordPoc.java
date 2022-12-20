package org.example;

import java.io.File;
import java.sql.SQLException;

/**
 * In this Poc here the inputs are received from the user, then shows a  message ‘Processing’
 * then the thread will read the file contents, and search for the word given.
 * counts the number of occurrences of the word and pass the info back to the main thread.
 * and then Handles the error,then all application is logged to mysql database and table is created
 * with specified contents.
 */
public class OccurrenceWordPoc {
    /*
    Entering the path
    once if the input is received it shows processing
    and executes if the file is present or not present
     */
    public static void main(String[] args) throws SQLException {
        if (args.length == Constants.ArgumentNumber) {
            String inputFilePath = args[0];
            String searchWord = args[1];
            System.out.println(inputFilePath);
            if (inputFilePath.endsWith(".txt") || inputFilePath.endsWith(".json")) {
                File inputFile = new File(inputFilePath);
                System.out.println("Processing......");
                if (inputFile.exists()) {
                    System.out.println("File exists");
                    performReadWordSearch(inputFilePath, searchWord);
                } else {
                    DatabaseHelper databasehelper = new DatabaseHelper();
                    databasehelper.storingDataToDataBase(inputFilePath, searchWord, "Error", 0, "File not found");
                    System.out.println("File dont exist");
                }
            } else {
                DatabaseHelper databasehelper = new DatabaseHelper();
                databasehelper.storingDataToDataBase(inputFilePath, searchWord, "Error", 0, "File not in Supported format");
                System.out.println("File is not in Supported format");
            }
        } else {
            System.out.println("Number of arguments are exceeded");
        }
    }

    /*
    In this performReadWordSearch it is used to call the other class by creating object
     */
    public static void performReadWordSearch(String inputFilePath, String searchWord) {
        // calling performSearch()
        PerformWordSearch performWord = new PerformWordSearch(inputFilePath, searchWord);
        performWord.run();
    }
}
