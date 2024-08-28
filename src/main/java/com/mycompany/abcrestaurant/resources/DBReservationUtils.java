/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.abcrestaurant.resources;

/**
 *
 * @author tharindulakshan
 */
public interface DBReservationUtils {
    
    public boolean makeReservation(Reservation reservation);
    
    public boolean checkUserExist(String userName);
    
}
