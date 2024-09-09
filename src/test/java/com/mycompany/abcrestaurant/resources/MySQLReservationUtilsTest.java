/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.abcrestaurant.resources;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author tharindulakshan
 */
public class MySQLReservationUtilsTest {
    
    public MySQLReservationUtilsTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    @Test
    public void testManageReservations(){
        
        System.out.println("Test Manage Reservations functions");
        
        MySQLReservationUtils instance = new MySQLReservationUtils();
        Reservation reservation = new Reservation("tharindu@gmail.com", "2024-09-07", "10 AM", 6, "Tharindu");
        
        //Test Make reservation / add reservations
        boolean addResult = instance.makeReservation(reservation);
        assertEquals(true, addResult);
        
        //Test get reservation
        Reservation result = instance.searchReservation(reservation.getReservationID());
        
        
        
        
        
    }
    
    
    
    
    
    
    
    
    
    
    

    /**
     * Test of makeReservation method, of class MySQLReservationUtils.
     */
//    @Test
//    public void testMakeReservation() {
//        System.out.println("makeReservation");
//        Reservation reservation = new Reservation( "heshan@gmail.com","2024-09-07" , "10 AM", 4, "Heshan");
//        MySQLReservationUtils instance = new MySQLReservationUtils();
//        boolean expResult = true;
//        boolean result = instance.makeReservation(reservation);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//    }

    /**
     * Test of checkUserExist method, of class MySQLReservationUtils.
     */
//    @Test
//    public void testCheckUserExist() {
//        System.out.println("checkUserExist");
//        String userName = "";
//        MySQLReservationUtils instance = new MySQLReservationUtils();
//        boolean expResult = false;
//        boolean result = instance.checkUserExist(userName);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getAllReservations method, of class MySQLReservationUtils.
//     */
//    @Test
//    public void testGetAllReservations() {
//        System.out.println("getAllReservations");
//        MySQLReservationUtils instance = new MySQLReservationUtils();
//        List<Reservation> expResult = null;
//        List<Reservation> result = instance.getAllReservations();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of deleteReservation method, of class MySQLReservationUtils.
//     */
//    @Test
//    public void testDeleteReservation() {
//        System.out.println("deleteReservation");
//        int reservationID = 0;
//        MySQLReservationUtils instance = new MySQLReservationUtils();
//        boolean expResult = false;
//        boolean result = instance.deleteReservation(reservationID);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of searchReservation method, of class MySQLReservationUtils.
//     */
//    @Test
//    public void testSearchReservation() {
//        System.out.println("searchReservation");
//        int reservationID = 0;
//        MySQLReservationUtils instance = new MySQLReservationUtils();
//        Reservation expResult = null;
//        Reservation result = instance.searchReservation(reservationID);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of updateReservation method, of class MySQLReservationUtils.
//     */
//    @Test
//    public void testUpdateReservation() {
//        System.out.println("updateReservation");
//        Reservation reservation = null;
//        MySQLReservationUtils instance = new MySQLReservationUtils();
//        boolean expResult = false;
//        boolean result = instance.updateReservation(reservation);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getAllReservationsByCustomer method, of class MySQLReservationUtils.
//     */
//    @Test
//    public void testGetAllReservationsByCustomer() {
//        System.out.println("getAllReservationsByCustomer");
//        String userName = "";
//        MySQLReservationUtils instance = new MySQLReservationUtils();
//        List<Reservation> expResult = null;
//        List<Reservation> result = instance.getAllReservationsByCustomer(userName);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
