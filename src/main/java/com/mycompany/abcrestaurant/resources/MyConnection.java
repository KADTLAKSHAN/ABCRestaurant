package com.mycompany.abcrestaurant.resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author tharindulakshan
 */
public class MyConnection {

    private static MyConnection instance = null;

    private Connection con = null;
    private Statement st = null;
    private PreparedStatement pst = null;

    private MyConnection() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/abc_restaurant?createDatabaseIfNotExist=true&autoReconnect=true&useSSL=false", "root", "MySQL@123");
            st = con.createStatement();
//            System.out.println("Connection Success!");

        } catch (ClassNotFoundException e) {
            
            System.err.println("MySQL JDBC Driver not found: " + e.getMessage());
            
        } catch (SQLException e) {
            
            System.err.println("Database Connection Error (MyConnection Class): " + e.getMessage());
            
        }


    }

    public static synchronized MyConnection getInstance() {

        if (instance == null) {
            instance = new MyConnection();
        }

        return instance;
    }

    public Connection getConnection() {

        return con;

    }

    public Statement getStatement() throws SQLException{

        return con.createStatement();

    }

    public PreparedStatement getPreparedStatement(String SQLQuery) throws SQLException {

        return con.prepareStatement(SQLQuery);

    }
    
    public ResultSet executeQuery(PreparedStatement pst) throws SQLException {
        return pst.executeQuery();
    }
    
    public void clear() {

        try {

            if (pst != null) {
                pst.close();
            }

            if (st != null) {
                st.close();
            }

            if (con != null) {
                con.close();
            }

        } catch (SQLException e) {

            System.out.println("Error Clearing database resources : " + e);

        } finally {
            
            pst = null;
            st = null;
            con = null;

        }

    }

}
