/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.abcrestaurant.resources;

import java.util.List;
import java.util.Map;

/**
 *
 * @author tharindulakshan
 */
public interface DBReservationUtils {
    
    public boolean makeReservation(Reservation reservation);
    
    public boolean checkUserExist(String userName);
    
    public List<Reservation> getAllReservations();
    
    public boolean deleteReservation(String reservationID);
    
    public Reservation searchReservation(String reservationID);
    
    public boolean updateReservation(Reservation reservation);
    
    public List<Reservation> getAllReservationsByCustomer(String userName);
    
    public Map<String,Boolean> availability();
    
    public String generatedID(String userName);
    
    public boolean reservationPayment(Payment payment);
    
    public List<Date> getAllReservationDates();
    
    public boolean addReservationDate(Date date);
    
    public boolean deleteReservationDate(String date);
    
    public boolean updateReservationDate(Date date);
    
    public Date searchDate(String date);
    
}
