// Activity 3 | Java 2
// Term 2, 2020
// Create, Insert & Search Database Program where user searches an ID number to see the matching name
package database;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Database { 
    
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/"; // 3306 is default port
        String user = "root";
        String password = ""; // you set password when MySQL is installed
        Connection con = null; // JDBC connection
        Statement stmt = null; // SQL statement object
        String query; // SQL query string
        ResultSet result = null; // results after SQL execution
        boolean found = false;

        do {
            try {
                con = DriverManager.getConnection(url, user, password); // connect to MySQL
                stmt = con.createStatement();           
                query = "USE DetailsDB;";
                stmt.execute(query);
                String searchID = JOptionPane.showInputDialog("Enter ID number to search names - Max 10");
                PreparedStatement st = (PreparedStatement) con.prepareStatement("SELECT ID, Name FROM Details WHERE ID =(?);");
                st.setString(1, searchID);                
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "ID: " + rs.getString("ID") + "\nName: " + rs.getString("Name"));
                    found = true;
                } else {
                    JOptionPane.showMessageDialog(null, "No name matching ID: " + searchID); 
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Bad input, try again");
                System.exit(0);
            } finally {
                // Close all database objects
                try {
                    if (result != null) {
                        result.close();
                    }
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "SQLException caught: " + ex.getMessage());
                }
            }
        } while (found == false);
        JOptionPane.showMessageDialog(null, "Program Ended");
        System.exit(0);
    }
}
/*  query = "DROP DATABASE IF EXISTS DetailsDB;";
                stmt.execute(query);
                query = "CREATE DATABASE DetailsDB;";
                stmt.execute(query);
                query = "USE DetailsDB;";
                stmt.execute(query);
                query = "CREATE TABLE Details(DetailsID INTEGER NOT NULL AUTO_INCREMENT, ID VARCHAR(32), Name VARCHAR(32), PRIMARY KEY(DetailsID));";  //INTEGER NOT NULL AUTO_INCREMENT
                stmt.execute(query);
                query = "INSERT INTO Details(ID, Name)"
                        + "VALUES(1, 'John'),(2, 'David'),(3, 'Mark'),(4, 'Tim'),(5, 'Blake'),(6, 'Mariah'),(7, 'Sofia'),(8, 'Carla'),(9, 'Justine'),(10, 'Liz');";      // CHANGE THIS LINE     
                stmt.execute(query); */