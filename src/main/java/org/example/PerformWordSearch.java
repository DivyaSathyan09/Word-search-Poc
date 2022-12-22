package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;
import java.util.concurrent.Callable;

/*
This method search the Occurrence of given word present in file
count the number of occurrence of word and pass the information to main thread
by using implements callable
 */
public class PerformWordSearch implements Callable<Integer> {
    private final String inputFilePath;
    private final String searchWord;
    int matchingResultCount = 0;

    public PerformWordSearch(String inputFilePath, String searchWord) {
        this.inputFilePath = inputFilePath;
        this.searchWord = searchWord;
    }

    public static void displayResult(String inputFilePath, String searchWord, int matchingResultCount) {
        try {
            String matchingResult;
            String wordMessage;
            DatabaseHelper databaseHelper = new DatabaseHelper();
            if (matchingResultCount > 0) {
                System.out.println("The Word : " + searchWord + " is Found! Count : " + matchingResultCount);
                matchingResult = "Success";
                wordMessage = "word found";
            } else {
                System.out.println("The Word : " + searchWord + " is incorrect! Count : " + matchingResultCount);
                matchingResult = "Error";
                wordMessage = "word not found";
            }
            databaseHelper.storingDataToDataBase(inputFilePath, searchWord, matchingResult, matchingResultCount, wordMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchWord() {
        String inputDataFromFile;
        try {
            inputDataFromFile = new String(Files.readAllBytes(Paths.get(inputFilePath)));
            inputDataFromFile = inputDataFromFile.replaceAll(Constants.REGEXPATTERN, Constants.REPLACEMENT);
            StringTokenizer stringTokenizer = new StringTokenizer(inputDataFromFile);
            while (stringTokenizer.hasMoreTokens()) {
                if (searchWord.equalsIgnoreCase(stringTokenizer.nextToken())) {
                    matchingResultCount++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer call() throws Exception {
        searchWord();
        return matchingResultCount;
    }
}
