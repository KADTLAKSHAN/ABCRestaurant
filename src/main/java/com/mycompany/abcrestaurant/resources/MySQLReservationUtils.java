/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.abcrestaurant.resources;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author tharindulakshan
 */
public class MySQLReservationUtils implements DBReservationUtils{

    @Override
    public boolean makeReservation(Reservation reservation) {
        String sql = "INSERT INTO tblReservation (reservationEmail, reservationDate, reservationTime, reservationPeople, userName) VALUES (?, ?, ?, ?, ?)";

        try {

            if (checkUserExist(reservation.getUserName())) {
                MyConnection myConnection = MyConnection.getInstance();
                myConnection.getConnection();

                PreparedStatement pst = myConnection.getPreparedStatement(sql);
                pst.setString(1, reservation.getReservationEmail());
                pst.setString(2, reservation.getReservationDate());
                pst.setString(3, reservation.getReservationTime());
                pst.setInt(4, reservation.getReservationPeople());
                pst.setString(5, reservation.getUserName());

                int rowAffected = pst.executeUpdate();

                return rowAffected == 1;
            }else{
                System.out.println("User not exist..");
                return false;
            }

        } catch (SQLException e) {

            System.out.println("make Reservation MySQL Util Error (Database) ::::" + e);
            return false;

        }
    }

    @Override
    public boolean checkUserExist(String userName) {
        String sql = "SELECT * FROM tblUser WHERE BINARY userName=?";

        try {

            MyConnection myConnection = MyConnection.getInstance();
            myConnection.getConnection();
            PreparedStatement pst = myConnection.getPreparedStatement(sql);
            pst.setString(1, userName);

            ResultSet resultSet = pst.executeQuery();

            if (resultSet.isBeforeFirst()) {

                return true;

            }

        } catch (SQLException e) {

            System.out.println("checkUserExist Function error Database::: " + e);

        }

        return false;
    }

    
}
