/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.abcrestaurant.resources;

import java.util.List;

/**
 *
 * @author tharindulakshan
 */
public interface DBReservationUtils {
    
    public boolean makeReservation(Reservation reservation);
    
    public boolean checkUserExist(String userName);
    
    public List<Reservation> getAllReservations();
    
    public boolean deleteReservation(int reservationID);
    
    public Reservation searchReservation(int reservationID);
    
    public boolean updateReservation(Reservation reservation);
    
    public List<Reservation> getAllReservationsByCustomer(String userName);
    
}
