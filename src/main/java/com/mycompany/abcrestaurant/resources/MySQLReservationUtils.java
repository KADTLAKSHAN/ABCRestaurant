/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.abcrestaurant.resources;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author tharindulakshan
 */
public class MySQLReservationUtils implements DBReservationUtils {

    @Override
    public boolean makeReservation(Reservation reservation) {
        String sql = "INSERT INTO tblReservation (reservationID, reservationEmail, reservationDate, reservationTime, reservationPeople, userName) VALUES (?, ?, ?, ?, ?, ?)";

        try {

            if (checkUserExist(reservation.getUserName())) {
                MyConnection myConnection = MyConnection.getInstance();
                myConnection.getConnection();

                PreparedStatement pst = myConnection.getPreparedStatement(sql);
                pst.setString(1, reservation.getReservationID());
                pst.setString(2, reservation.getReservationEmail());
                pst.setString(3, reservation.getReservationDate());
                pst.setString(4, reservation.getReservationTime());
                pst.setInt(5, reservation.getReservationPeople());
                pst.setString(6, reservation.getUserName());

                int rowAffected = pst.executeUpdate();
//                TwilioSMS sendMessage = new TwilioSMS();
//                sendMessage.sendSms(reservation);

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

                reservations.add(new Reservation(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6)));

            }

        } catch (SQLException e) {

            return null;

        }

        return reservations;
    }

    @Override
    public boolean deleteReservation(String reservationID) {

        String sql = "DELETE FROM tblReservation WHERE reservationID=?";

        try {

            MyConnection myConnection = MyConnection.getInstance();
            myConnection.getConnection();
            PreparedStatement pst = myConnection.getPreparedStatement(sql);
            pst.setString(1, reservationID);

            if(pst.executeUpdate() == 1){

                return true;

            }

        } catch (Exception e) {

            System.out.println("deleteReservation Function error Database::: " + e);

        }

        return false;

        
    }

    @Override
    public Reservation searchReservation(String reservationID) {

        String sql = "SELECT * FROM tblReservation WHERE reservationID=?";
        Reservation reservation = null;

        try {

            MyConnection myConnection = MyConnection.getInstance();
            myConnection.getConnection();
            PreparedStatement pst = myConnection.getPreparedStatement(sql);
            pst.setString(1, reservationID);

            ResultSet resultSet = pst.executeQuery();

            if (resultSet.next()) {

                reservation = new Reservation(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5), resultSet.getString(6));

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
            pst.setString(6, reservation.getReservationID());

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

                reservation.add(new Reservation(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6)));

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
                    availability.put(date + ":10 AM", false);

                }else{

                    availability.put(key, false);

                }

                
            }

            
        } catch (Exception e) {
            System.out.println("Disable date function error: " + e);
        }

        return availability;

    }

    @Override
    public String generatedID(String userName) {
        return userName + System.nanoTime();
    }
    
    
    public String currentDate(){
        
        LocalDate date = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(dateFormatter);
        
    }
    
    public String getTime(){
        
        LocalTime time = LocalTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return time.format(timeFormatter);
        
    }

    @Override
    public boolean reservationPayment(Payment payment) {
        String sql = "INSERT INTO tblPayment (userName, id, date, time, cardNumber, cardName, expiryDate, cvv) VALUES (?,?,?,?,?,?,?,?)";

        try {
            MyConnection myConnection = MyConnection.getInstance();
            myConnection.getConnection();
            PreparedStatement pst = myConnection.getPreparedStatement(sql);
            pst.setString(1, payment.getUserName());
            pst.setString(2, payment.getId());
            pst.setString(3, currentDate());
            pst.setString(4, getTime());
            pst.setString(5, payment.getCardNumber());
            pst.setString(6, payment.getCardName());
            pst.setString(7, payment.getExpiryDate());
            pst.setString(8, payment.getCvv());
            
            int rowAffected = pst.executeUpdate();

            return rowAffected == 1;

        } catch (Exception e) {
            System.out.println("reservationPayment function error: " + e);
            return false;
        }
    }

    @Override
    public List<Date> getAllReservationDates() {
        
        String sql = "SELECT * FROM tblDate";
        
        List<Date> date = new ArrayList<>();
        
        try {
            
            MyConnection myCon = MyConnection.getInstance();
            myCon.getConnection();
            PreparedStatement pst = myCon.getPreparedStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                
                date.add(new Date(rs.getString(1), rs.getString(2)));
                
            }
            
            
        } catch (Exception e) {
            
            System.out.println("getAllReservationDates function error: " + e);
            return null;
            
        }
        
        return date;
        
    }

    @Override
    public boolean addReservationDate(Date date) {
        String sql = "INSERT INTO tblDate (date, time) VALUES (?,?)";
        
        try {
            
            MyConnection myConnection = MyConnection.getInstance();
            myConnection.getConnection();
            PreparedStatement pst = myConnection.getPreparedStatement(sql);
            pst.setString(1, date.getDate());
            pst.setString(2, date.getTime());
            
            int rowAffected = pst.executeUpdate();
            
            return rowAffected == 1;
            
        } catch (Exception e) {
            
            System.out.println("Add date function error: " + e);
            return false;
        }
        
    }

    @Override
    public boolean deleteReservationDate(String date) {
        
        String sql = "DELETE FROM tblDate WHERE date=?";
        
        try {
            
            MyConnection myConnection = MyConnection.getInstance();
            myConnection.getConnection();
            PreparedStatement pst = myConnection.getPreparedStatement(sql);
            pst.setString(1, date);
            
            if(pst.executeUpdate() == 1){

                return true;

            }
            
        } catch (Exception e) {
            
            System.out.println("Delete date function error: " + e);
            
            
        }
        
        return false;
        
    }

    @Override
    public boolean updateReservationDate(Date date) {
        String sql = "UPDATE tblDate SET time=? WHERE date=?";
        
        try {
            
            MyConnection myConnection = MyConnection.getInstance();
            myConnection.getConnection();
            PreparedStatement pst = myConnection.getPreparedStatement(sql);
            pst.setString(1, date.getTime());
            pst.setString(2, date.getDate());
            
            if(pst.executeUpdate() == 1){
                
                return true;
                
            }
            
        } catch (Exception e) {
           System.out.println("Update date function error: " + e); 
        }
        
        return false;
    }

    @Override
    public Date searchDate(String date) {
        String sql = "SELECT * FROM tblDate WHERE date=?";
        
        Date dt = null;
        
        try {
            
            MyConnection myConnection = MyConnection.getInstance();
            myConnection.getConnection();
            PreparedStatement pst = myConnection.getPreparedStatement(sql);
            pst.setString(1, date);
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                
                dt = new Date(rs.getString(1), rs.getString(2));
                
            }
            
            
            
        } catch (Exception e) {
            
            System.out.println("Update date function error: " + e);
            return dt;
            
        }
        
        return dt;
        
    }

 

   
   
}
