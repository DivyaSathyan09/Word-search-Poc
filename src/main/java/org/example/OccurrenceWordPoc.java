package org.example;

import java.io.File;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
    the information of the number of occurance of the word is passed into the main thread
     */
    public static void main(String[] args) throws SQLException, ExecutionException, InterruptedException {
        if (args.length == Constants.ARGUMENT_NUMBER) {
            String inputFilePath = args[0];
            String searchWord = args[1];
            System.out.println(inputFilePath);
            if (inputFilePath.endsWith(Constants.TXT_EXTENSION) || inputFilePath.endsWith(Constants.JSON_EXTENSION)) {
                File inputFile = new File(inputFilePath);
                System.out.println("Processing......");
                if (inputFile.exists()) {
                    System.out.println("File exists");
                    ExecutorService threadPool = Executors.newFixedThreadPool(1);
                    Future<Integer> welcomeChildThread = threadPool.submit(new PerformWordSearch(inputFilePath, searchWord));
                    int matchingResultCount = welcomeChildThread.get();
                    PerformWordSearch.displayResult(inputFilePath, searchWord, matchingResultCount);
                    threadPool.shutdown();
                } else {
                    DatabaseHelper databasehelper = new DatabaseHelper();
                    databasehelper.insertDataToDataBase(inputFilePath, searchWord, "Error", 0, "File not found");
                    System.out.println("File dont exist");
                }
            } else {
                DatabaseHelper databasehelper = new DatabaseHelper();
                databasehelper.insertDataToDataBase(inputFilePath, searchWord, "Error", 0, "File not in Supported format");
                System.out.println("File is not in Supported format");
            }
        } else {
            System.out.println("Number of arguments are exceeded");
        }
    }
}
