package org.example;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;

/*
This method search the occurence of given word present in file
count the number of occurance of word and pass the information to main thread
by using implements Runnable and overriding the run()
 */
public class PerformWordSearch implements Runnable {
    private final String inputFilePath;
    private final String searchWord;

    public PerformWordSearch(String inputFilePath, String searchWord) {
        this.inputFilePath = inputFilePath;
        this.searchWord = searchWord;
    }

    @Override
    public void run() {
        int matchingResultsCount = 0;
        String inputDataFromFile;
        try {
            inputDataFromFile = new String(Files.readAllBytes(Paths.get(inputFilePath)));
            inputDataFromFile = inputDataFromFile.replaceAll("[^a-zA-Z0-9@-]", "  ");
            StringTokenizer st = new StringTokenizer(inputDataFromFile);
            while (st.hasMoreTokens()) {
                if (searchWord.equalsIgnoreCase(st.nextToken())) {
                    matchingResultsCount++;
                }
            }
            DatabaseHelper dbHelper = new DatabaseHelper();
            if (matchingResultsCount > 0) {
                dbHelper.storingDataToDataBase(inputFilePath, searchWord, "Success", matchingResultsCount, "");
                System.out.println("The Word : " + searchWord + " is Found! Count : " + matchingResultsCount);
            } else {
                dbHelper.storingDataToDataBase(inputFilePath, searchWord, "Error", matchingResultsCount, "Word not found");
                System.out.println("The Word : " + searchWord + " is incorrect! Count : " + matchingResultsCount);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
