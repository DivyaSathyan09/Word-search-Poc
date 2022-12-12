package org.example;

import java.io.File;

public class OccurrenceWordPoc {
    /*
    Entering the path
    once if the input is received it shows processing
    and executes if the file is present or not present
     */
    public static void main(String[] args) {
        if (args.length == 2) {
            String inputFilePath = args[0];
            String searchWord = args[1];
            System.out.println(inputFilePath);
            if (inputFilePath.endsWith(".txt") || inputFilePath.endsWith(".json")) {
                File inputFile = new File(inputFilePath);
                System.out.println("Processing......");
                if (inputFile.exists()) {
                    System.out.println("File already exists");
                } else {
                    System.out.println("File dont exist");
                    return;
                }
                performReadWordSearch(inputFilePath, searchWord);
            } else {
                System.out.println("File is not in Supported format");
            }
        } else {
            System.out.println("Number of arguments are exceeded");
        }
    }

    /*
    In this performRead it is used to call the other class by creating object
     */
    public static void performReadWordSearch(String inputFilePath, String searchWord) {
        // calling performSearch()
        PerformWordSearch performWord = new PerformWordSearch(inputFilePath, searchWord);
        performWord.run();
    }
}
