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
    public static final String AUDIT = "audit";
    String driverClass = "com.mysql.cj.jdbc.Driver";
    String mySqlUrl = "jdbc:mysql://localhost:3306/Poc1";
    String userName = "root";
    String passwordOfDatabase = "DivyaSathyan022960";
    String dateAndTimeFormat = "dd/MM/yyyy HH:mm:ss";
    String createTable = "create table audit (PathOfTheFile varchar(100), WordSearched varchar(50), DateAndTime varchar(50), FinalResult varchar(50), NumberOfWordCount int, ErrorMessage varchar(100))";

    /*
      Storing data to database
     */
    public void storingDataToDataBase(String filepath, String WordSearch, String finalResult, int totalNumberOfWords, String errorMessage) throws SQLException, ClassNotFoundException {
        Connection connectionToDataBase = null;
        Statement statement;
        String DateAndTime;
        DateTimeFormatter dateAndTimeFormat = DateTimeFormatter.ofPattern(this.dateAndTimeFormat);
        LocalDateTime now = LocalDateTime.now();
        DateAndTime = dateAndTimeFormat.format(now);
        Class.forName(this.driverClass);
        try {
            connectionToDataBase = DriverManager.getConnection(this.mySqlUrl, this.userName, this.passwordOfDatabase);
            statement = connectionToDataBase.createStatement();
            DatabaseMetaData checkIfTableExists = connectionToDataBase.getMetaData();
            ResultSet tables = checkIfTableExists.getTables(null, null, AUDIT, null);
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
        Connection connectionToDataBase = null;
        try {

            connectionToDataBase = DriverManager.getConnection(this.mySqlUrl, this.userName, this.passwordOfDatabase);
            Statement st = connectionToDataBase.createStatement();
            st.execute(this.createTable);
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
            Class.forName(this.driverClass);
            connectionToDataBase = DriverManager.getConnection(this.mySqlUrl, this.userName, this.passwordOfDatabase);
            return connectionToDataBase;
        } catch (Exception e) {
            e.printStackTrace();
            return connectionToDataBase;
        }
    }
}
