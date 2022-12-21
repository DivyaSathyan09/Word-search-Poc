package org.example;

public class Constants {

    public static final String AUDIT = "audit";
    public static final String DriverClass = "com.mysql.cj.jdbc.Driver";
    public static final String MySqlUrl = "jdbc:mysql://localhost:3306/Poc1";
    public static final String UserName = "root";
    public static final String PasswordOfDatabase = "DivyaSathyan022960";
    public static final String DateAndTimeFormat = "dd/MM/yyyy HH:mm:ss";
    public static final String CreateTable = "create table audit (PathOfTheFile varchar(100), WordSearched varchar(50), DateAndTime varchar(50), FinalResult varchar(50), NumberOfWordCount int, ErrorMessage varchar(100))";
    public static final int ArgumentNumber = 2;
    public static final String Regexpattern = "[^a-zA-Z0-9@-]";
    public static final String Replacement =" ";

    public static final String txtExtension = ".txt";
    public static final String jsonExtension= ".json";
}
