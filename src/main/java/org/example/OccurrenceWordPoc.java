package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class OccurrenceWordPoc {
    static String inputFilePath;
    static String searchWord;

    /*
    Entering the path
    once if the input is received it shows processing
    and executes if the file is present or not present
     */
    public static void main(String[] args) {
        inputFilePath = args[0];
        searchWord = args[1];
        System.out.println(inputFilePath);
        File inputFile = new File(inputFilePath);
        System.out.println("Processing......");
        if (inputFile.exists()) {
            System.out.println("File already exists");
        } else {
            System.out.println("File dont exist");
            return;
        }
        performRead();
    }

    /*
    In this performRead it is used to read the content present in the file
    by creation of fileReader object and bufferReader object
    then by using try and catch Exception, reading the content from the file
    calling performsearch()
     */
    public static void performRead() {
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
            System.out.println(" Something went wrong!The file name is Incorrect,The file '" + inputFilePath + "' does not exist.");
        }
        // calling performSearch();
        PerformWord performWord = new PerformWord(inputFilePath, searchWord);
        performWord.run();
    }
}