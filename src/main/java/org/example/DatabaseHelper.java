package org.example;

import java.sql.*;
import java.text.MessageFormat;
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
    public void insertDataToDataBase(String filepath, String WordSearch, String finalResult, int totalNumberOfWords, String errorMessage) throws SQLException {
        Connection connectionToDataBase = null;
        Statement statement;
        String DateAndTime;
        DateTimeFormatter dateAndTimeFormat = DateTimeFormatter.ofPattern(Constants.DATE_AND_TIME_FORMAT);
        LocalDateTime now = LocalDateTime.now();
        DateAndTime = dateAndTimeFormat.format(now);
        try {
            connectionToDataBase = connectToDataBase();
            statement = connectionToDataBase.createStatement();
            DatabaseMetaData checkIfTableExists = connectionToDataBase.getMetaData();
            ResultSet tables = checkIfTableExists.getTables(null, null, Constants.AUDIT, null);
            if (tables.next()) {
                String query = MessageFormat.format("INSERT INTO audit VALUES ({0},{1},{2},{3},{4},{5})", "'" + filepath + "'", "'" + WordSearch + "'", "'" + DateAndTime + "'", "'" + finalResult + "'", "'" + totalNumberOfWords + "'", "'" + errorMessage + "'");
                statement.execute(query);
            } else {
                this.createTableAndInsertDataToDatabase(filepath, WordSearch, DateAndTime, finalResult, totalNumberOfWords, errorMessage);
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
    private void createTableAndInsertDataToDatabase(String filepath, String searchedWord, String currentDateAndTime, String resultToDatabase, int totalNoOfWords, String errorMessage) throws SQLException {
        Connection connectionToDataBase = connectToDataBase();
        try {
            Statement statement = connectionToDataBase.createStatement();
            statement.execute(Constants.CREATE_TABLE);
            String query = MessageFormat.format("INSERT INTO audit VALUES ({0},{1},{2},{3},{4},{5})", "'" + filepath + "'", "'" + searchedWord + "'", "'" + currentDateAndTime + "'", "'" + resultToDatabase + "'", "'" + totalNoOfWords + "'", "'" + errorMessage + "'");
            statement.execute(query);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Objects.requireNonNull(connectionToDataBase).close();
        }
    }

    private Connection connectToDataBase() throws SQLException {
        Connection connectionToDataBase = null;
        try {
            Class.forName(Constants.DRIVER_CLASS);
            connectionToDataBase = DriverManager.getConnection(Constants.MY_SQL_URL, Constants.USER_NAME, Constants.PASSWORD_OF_DATABASE);
            return connectionToDataBase;
        } catch (Exception e) {
            e.printStackTrace();
            return connectionToDataBase;
        }
    }
}
