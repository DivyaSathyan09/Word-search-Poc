package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;

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
                performRead(inputFilePath, searchWord);
            } else {
                System.out.println("File is not in Supported format");
            }
        } else {
            System.out.println("Number of arguments are exceeded");
        }
    }

    /*
    In this performRead it is used to read the content present in the file
    by creation of fileReader object and bufferReader object
    then by using try and catch Exception, reading the content from the file
    calling performsearch()
     */
    public static void performRead(String inputFilePath, String searchWord) {
        StringBuilder fileContents = new StringBuilder();
        try {
            FileReader find = new FileReader(inputFilePath);//Creation of fileReader object
            BufferedReader bufferedReader = new BufferedReader(find);// Creation of BufferedReader object
            //Reading content from the file
            String lineText;
            while ((lineText = bufferedReader.readLine()) != null) {
                fileContents.append(lineText);
            }
            bufferedReader.close();//Close the file
        } catch (IOException ex) {
            System.out.println("Something went wrong! The file name is Incorrect,the file '" + inputFilePath + "' does not exist.");
        }
        // calling performSearch();
        PerformWordSearch performWord = new PerformWordSearch(inputFilePath, searchWord);
        performWord.run();
    }
}
