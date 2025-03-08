package com.rida.javafxproject;
import java.sql.*;

public class Database {

    static Connection db;
    public static void connect() {
        try {
            db = DriverManager.getConnection("jdbc:mysql://localhost:3306/seekerslegacy", "root", "laiba406");
            System.out.println("Connected to Database!");
        }
        catch(Exception e) {
            System.out.println("Database Connection Failed!");
        }

    }
}
