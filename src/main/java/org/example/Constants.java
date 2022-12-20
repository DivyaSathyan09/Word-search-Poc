package org.example;

public class Constants {

    public static final String AUDIT = "audit";
    public static final String driverClass = "com.mysql.cj.jdbc.Driver";
    public static final String mySqlUrl = "jdbc:mysql://localhost:3306/Poc1";
    public static final String userName = "root";
    public static final String passwordOfDatabase = "DivyaSathyan022960";
    public static final String dateAndTimeFormat = "dd/MM/yyyy HH:mm:ss";
    public static final String createTable = "create table audit (PathOfTheFile varchar(100), WordSearched varchar(50), DateAndTime varchar(50), FinalResult varchar(50), NumberOfWordCount int, ErrorMessage varchar(100))";
    public static final int ArgumentNumber = 2;
}


