package com.jsp.first;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class fetchAlltheData {

    public static void main(String[] args) {
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;

        try {
            // Step 1: Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish the database connection
            String url = "jdbc:mysql://localhost:3306/jdbc-first-database"; // your database name
            String userName = "root";    // this is user name
            String password = "Your_password";    // your MySQL password
            con = DriverManager.getConnection(url, userName, password);

            // Step 3: Create a Statement object
            state = con.createStatement();

            // Step 4: Execute SELECT query
            String query = "SELECT * FROM student";
            rs = state.executeQuery(query);

            // Step 5: Process and display results
            System.out.println("ID\tName\t\tMarks");
            System.out.println("-------------------------------");

            while (rs.next()) {
                // Use column indexes instead of names to avoid column name mismatch
                int id = rs.getInt(1);           // 1st column
                String name = rs.getString(2);   // 2nd column
                double marks = rs.getDouble(3);  // 3rd column
                System.out.println(id + "\t" + name + "\t\t" + marks);
            }

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver class not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("SQL Error occurred!");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Unexpected error occurred!");
            e.printStackTrace();
        } finally {
            // Step 6: Close resources in reverse order of creation
            try {
                if (rs != null) rs.close();
                if (state != null) state.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error while closing resources.");
                e.printStackTrace();
            }
        }
    }
}
