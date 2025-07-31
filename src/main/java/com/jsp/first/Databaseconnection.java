package com.jsp.first;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Databaseconnection {

    public static void main(String[] args) {
        try {
            // Step 1: Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish the database connection
            String url = "jdbc:mysql://localhost:3306/jdbc-first-database";
            String userName = "root";
            String password = "Manish12345@";
            Connection con = DriverManager.getConnection(url, userName, password);

            // Step 3: Create a Statement object
            Statement state = con.createStatement();

            // Step 4: Insert records (skip if primary key exists)
            String query = "INSERT IGNORE INTO student VALUES " +
                           "(1, 'Alice', 85.50), " +
                           "(2, 'Bob', 90.25), " +
                           "(3, 'Charlie', 78.00)";
            int rowsAffected = state.executeUpdate(query);

            // Step 5: Close the connection
            con.close();

            if (rowsAffected > 0) {
                System.out.println(rowsAffected + " new records inserted successfully!");
            } else {
                System.out.println("No new records inserted (might be duplicate entries).");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Driver class not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("SQL Error occurred!");
            e.printStackTrace();
        }
    }
}
