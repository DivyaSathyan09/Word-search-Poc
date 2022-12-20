package org.example;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/*
In this database mySQL database with table name audit.
The table contains following columns, path to the file, the word searched, the date time of the search
with error and success messages
 */
public class DatabaseHelper {
    /*
      Storing data to database
     */
    public void storingDataToDataBase(String filepath, String WordSearch, String finalResult, int totalNumberOfWords, String errorMessage) throws SQLException {
        Connection connectionToDataBase = null;
        Statement statement;
        String DateAndTime;
        DateTimeFormatter dateAndTimeFormat = DateTimeFormatter.ofPattern(Constants.dateAndTimeFormat);
        LocalDateTime now = LocalDateTime.now();
        DateAndTime = dateAndTimeFormat.format(now);
        try {
            connectionToDataBase = connectionToDataBase();
            statement = connectionToDataBase.createStatement();
            DatabaseMetaData checkIfTableExists = connectionToDataBase.getMetaData();
            ResultSet tables = checkIfTableExists.getTables(null, null, Constants.AUDIT, null);
            if (tables.next()) {
                statement.execute("INSERT INTO audit VALUES ('" + filepath + "','" + WordSearch + "','" + DateAndTime + "','" + finalResult + "'," + totalNumberOfWords + ",'" + errorMessage + "')");
            } else {
                this.saveDataToTable(filepath, WordSearch, DateAndTime, finalResult, totalNumberOfWords, errorMessage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Objects.requireNonNull(connectionToDataBase).close();
        }
    }

    /*
    Here data gets saved to table
     */
    private void saveDataToTable(String filepath, String searchedWord, String currentDateAndTime, String resultToDatabase, int totalNoOfWords, String errorMessage) throws SQLException {
        Connection connectionToDataBase = connectionToDataBase();
        try {
            Statement st = connectionToDataBase.createStatement();
            st.execute(Constants.createTable);
            st.execute("INSERT INTO audit VALUES ('" + filepath + "','" + searchedWord + "','" + currentDateAndTime + "','" + resultToDatabase + "'," + totalNoOfWords + ",'" + errorMessage + "')");

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Objects.requireNonNull(connectionToDataBase).close();
        }
    }
    private Connection connectionToDataBase() throws SQLException {
        Connection connectionToDataBase = null;
        try {
            Class.forName(Constants.driverClass);
            connectionToDataBase = DriverManager.getConnection(Constants.mySqlUrl, Constants.userName, Constants.passwordOfDatabase);
            return connectionToDataBase;
        } catch (Exception e) {
            e.printStackTrace();
            return connectionToDataBase;
        }
    }
}
