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
    private ResultSet rs1 = null;
    private ResultSet rs2 = null;

    private MyConnection() {

        try {

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/abc_restaurant?createDatabaseIfNotExist=true&autoReconnect=true&useSSL=false", "root", "MySQL@123");
            st = con.createStatement();
            System.out.println("Connection Success!");

        } catch (SQLException e) {

            System.out.println("Database Connection Error (MyConnection Class): " + e);

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

    public Statement getStatement() {

        return st;

    }

    public PreparedStatement getPreparedStatement(String SQLQuery) throws SQLException {

        if (pst == null) {
            pst = con.prepareStatement(SQLQuery);
        }

        return pst;

    }

    public void clear() {

        try {

            if (rs1 != null) {
                rs1.close();
            }

            if (rs2 != null) {
                rs2.close();
            }

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

            rs1 = null;
            rs2 = null;
            pst = null;
            st = null;
            con = null;

        }

    }

}
