/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.abcrestaurant.resources;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();

        String sql = "SELECT * FROM tblReservation";

        try {

            MyConnection myCon = MyConnection.getInstance();
            myCon.getConnection();
            ResultSet rs = myCon.executeQuery(myCon.getPreparedStatement(sql));

            while (rs.next()) {

                reservations.add(new Reservation(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6)));

            }


        } catch (SQLException e) {

            return null;

        }

        return reservations;
    }

    @Override
    public boolean deleteReservation(int reservationID) {
        
        String sql = "DELETE FROM tblReservation WHERE reservationID=?";
        
        try {
            
            MyConnection myConnection = MyConnection.getInstance();
            myConnection.getConnection();
            PreparedStatement pst = myConnection.getPreparedStatement(sql);
            pst.setInt(1, reservationID);
            
            if(pst.executeUpdate() == 1){
                
                return true;
                
            }
            
        } catch (Exception e) {
            
            System.out.println("deleteReservation Function error Database::: " + e);
            
        }
        
        return false;
        
        
    }

    @Override
    public Reservation searchReservation(int reservationID) {
        
        String sql = "SELECT * FROM tblReservation WHERE reservationID=?";
        Reservation reservation = null;
        
        try {
            
            MyConnection myConnection = MyConnection.getInstance();
            myConnection.getConnection();
            PreparedStatement pst = myConnection.getPreparedStatement(sql);
            pst.setInt(1, reservationID);
            
            ResultSet resultSet = pst.executeQuery();
            
            if(resultSet.next()){
                
                reservation = new Reservation(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5), resultSet.getString(6));
   
            }
            
            
        } catch (Exception e) {
            
            System.out.println("Search Reservation MySQL Util Error (Database) ::::" + e);
            
            return null;
            
        }
        
        return reservation;
        
        
    }

    @Override
    public boolean updateReservation(Reservation reservation) {
        String sql = "UPDATE tblReservation SET reservationEmail=?, reservationDate=?, reservationTime=?, reservationPeople=?, userName=? WHERE reservationID=?";
        
        try {
            
            MyConnection myConnection = MyConnection.getInstance();
            myConnection.getConnection();
            PreparedStatement pst = myConnection.getPreparedStatement(sql);
            pst.setString(1, reservation.getReservationEmail());
            pst.setString(2, reservation.getReservationDate());
            pst.setString(3, reservation.getReservationTime());
            pst.setInt(4, reservation.getReservationPeople());
            pst.setString(5, reservation.getUserName());
            pst.setInt(6, reservation.getReservationID());
                 
            if(pst.executeUpdate() == 1){
                return true;
            }
            
            
            
        } catch (Exception e) {
            
            System.out.println("updateUser Function error Database::: " + e);
            
        }
        
        return false;
    }

    @Override
    public List<Reservation> getAllReservationsByCustomer(String userName) {
        
        List<Reservation> reservation = new ArrayList<>();

        String sql = "SELECT * FROM tblReservation WHERE userName=?";

        try {

            MyConnection myCon = MyConnection.getInstance();
            myCon.getConnection();
            PreparedStatement pst = myCon.getPreparedStatement(sql);
            pst.setString(1, userName);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                reservation.add(new Reservation(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6)));

            }


        } catch (SQLException e) {

            return null;

        }

        return reservation;
        
    }

    @Override
    public Map<String, Boolean> availability() {
        Map<String, Boolean> availability = new HashMap<>();
        
        String sql = "SELECT * FROM tblDate";
        
        try {
            
            MyConnection myCon = MyConnection.getInstance();
            myCon.getConnection();
            PreparedStatement pst = myCon.getPreparedStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                String date = rs.getString(1);
                String time = rs.getString(2);
                String key = date + ":" + time; //composite key
                
                if("Both".equals(time)){
                    
                    availability.put(date + ":1 PM", false);
                    availability.put(date + ":10 AM",false );        
                    
                }else{
                    
                    availability.put(key, false);
                    
                }
                
                
            }
            
            
        } catch (Exception e) {
            System.out.println("Disable date function error: " + e);
        }
        
        return availability;
        
    }
    
    
    

    
}
