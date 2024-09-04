/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.abcrestaurant.resources;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tharindulakshan
 */
public class MySQLRateUtils implements DBRateUtils {

    @Override
    public boolean addRate(Rate rate) {

        String sql = "INSERT INTO tblRating (rateTitle, rateDescription, userName) VALUES (?, ?, ?)";

        try {

            MyConnection myConnection = MyConnection.getInstance();
            myConnection.getConnection();

            PreparedStatement pst = myConnection.getPreparedStatement(sql);
            pst.setString(1, rate.getRateTitle());
            pst.setString(2, rate.getRateDescription());
            pst.setString(3, rate.getUserName());

            int rowAffected = pst.executeUpdate();

            return rowAffected == 1;

        } catch (SQLException e) {

            System.out.println("make Rate MySQL Util Error (Database) ::::" + e);
            return false;

        }

    }

    @Override
    public List<Rate> getAllRateByUser(String userName) {
        
        List<Rate> rates = new ArrayList<>();

        String sql = "SELECT * FROM tblRating WHERE userName=?";

        try {

            MyConnection myCon = MyConnection.getInstance();
            myCon.getConnection();
            PreparedStatement pst = myCon.getPreparedStatement(sql);
            pst.setString(1, userName);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                
                rates.add(new Rate(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));

            }


        } catch (SQLException e) {

            return null;

        }

        return rates;
        
    }

    @Override
    public List<Rate> getAllRates() {
        List<Rate> rate = new ArrayList<>();

        String sql = "SELECT * FROM tblRating";

        try {

            MyConnection myCon = MyConnection.getInstance();
            myCon.getConnection();
            ResultSet rs = myCon.executeQuery(myCon.getPreparedStatement(sql));

            while (rs.next()) {

                rate.add(new Rate(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));

            }


        } catch (SQLException e) {

            return null;

        }

        return rate;
    }

}
