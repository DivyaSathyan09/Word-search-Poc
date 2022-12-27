package org.example;

public class Constants {

    public static final String AUDIT = "audit";
    public static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    public static final String MY_SQL_URL = "jdbc:mysql://localhost:3306/Poc1";
    public static final String USER_NAME = "root";
    public static final String PASSWORD_OF_DATABASE = "DivyaSathyan022960";
    public static final String DATE_AND_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";
    public static final String CREATE_TABLE = "create table audit (PathOfTheFile varchar(100), WordSearched varchar(50), DateAndTime varchar(50), FinalResult varchar(50), NumberOfWordCount int, ErrorMessage varchar(100))";
    public static final int ARGUMENT_NUMBER = 2;
    public static final String REGEXPATTERN = "[^a-zA-Z0-9@-]";
    public static final String REPLACEMENT = " ";

    public static final String TXT_EXTENSION = ".txt";
    public static final String JSON_EXTENSION = ".json";
}
