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
public class MySQLUserUtilsTest {
    
    public MySQLUserUtilsTest() {
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

    /**
     * Test of getUsers method, of class MySQLUserUtils.
     */
//    @Test
//    public void testGetUsers() {
//        System.out.println("getUsers");
//        MySQLUserUtils instance = new MySQLUserUtils();
//        List<Customer> expResult = null;
//        List<Customer> result = instance.getUsers();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of searchUser method, of class MySQLUserUtils.
     */
//    @Test
//    public void testSearchUser() {
//        System.out.println("searchUser");
//        String userName = "";
//        MySQLUserUtils instance = new MySQLUserUtils();
//        Customer expResult = null;
//        Customer result = instance.searchUser(userName);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of addUser method, of class MySQLUserUtils.
     */
//    @Test
//    public void testAddUser() {
//        System.out.println("addUser");
//        Customer user = null;
//        MySQLUserUtils instance = new MySQLUserUtils();
//        boolean expResult = false;
//        boolean result = instance.addUser(user);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of updateUsers method, of class MySQLUserUtils.
     */
//    @Test
//    public void testUpdateUsers() {
//        System.out.println("updateUsers");
//        Customer user = null;
//        MySQLUserUtils instance = new MySQLUserUtils();
//        boolean expResult = false;
//        boolean result = instance.updateUsers(user);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of deleteUser method, of class MySQLUserUtils.
     */
//    @Test
//    public void testDeleteUser() {
//        System.out.println("deleteUser");
//        String userName = "";
//        MySQLUserUtils instance = new MySQLUserUtils();
//        boolean expResult = false;
//        boolean result = instance.deleteUser(userName);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of userLogin method, of class MySQLUserUtils.
     */
//    @Test
//    public void testUserLogin() {
//        System.out.println("userLogin");
//        String userName = "testmanager";
//        String userPassword = "manager";
//        String userType = "Manager";
//        MySQLUserUtils instance = new MySQLUserUtils();
//        boolean expResult = true;
//        boolean result = instance.userLogin(userName, userPassword, userType);
//        assertEquals(expResult, result);
//    }

    /**
     * Test of addCustomer method, of class MySQLUserUtils.
     */
//    @Test
//    public void testAddCustomer() {
//        System.out.println("addCustomer");
//        Customer customer = new Customer("0711233211", "Colombo", 0, "testcustomer2", "Tharindu", "Lakshan", "test@gmail.com", "1234", "Customer");
//        MySQLUserUtils instance = new MySQLUserUtils();
//        boolean expResult = true;
//        boolean result = instance.addCustomer(customer);
//        assertEquals(expResult, result);
//    }

    /**
     * Test of updateCustomerProfile method, of class MySQLUserUtils.
     */
//    @Test
//    public void testUpdateCustomerProfile() {
//        System.out.println("updateCustomerProfile");
//        Customer customer = null;
//        MySQLUserUtils instance = new MySQLUserUtils();
//        boolean expResult = false;
//        boolean result = instance.updateCustomerProfile(customer);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
    @Test
    public void testUserMangement() {
        System.out.println("user manage function - add/search/update/delete");
        MySQLUserUtils instance = new MySQLUserUtils();
        Customer customer = new Customer("0711234123", "Kandy", 25, "Kasun01", "Kasun", "Malshan", "kasun@gmail.com", "1234", "Customer");
        
        // Testing Add Customer
        boolean addResult = instance.addCustomer(customer);
        assertEquals(true, addResult);

        // Testing get Customer
        Customer result = instance.searchUser(customer.getUserName());
        assertEquals(customer.getUserName(), result.getUserName());
        assertEquals(customer.getPhoneNumber(), result.getPhoneNumber());
        assertEquals(customer.getAddress(), result.getAddress());
        assertEquals(customer.getAge(), result.getAge());
        assertEquals(customer.getUserFirstName(), result.getUserFirstName());
        assertEquals(customer.getUserLastName(), result.getUserLastName());
        assertEquals(customer.getUserEmail(), result.getUserEmail());
        assertEquals(customer.getUserPassword(), result.getUserPassword());
        assertEquals(customer.getUserType(), result.getUserType());
        
        // Testing update customer
        customer = new Customer("0711234123", "Kandy - updated", 25, "Kasun01", "Kasun", "Malshan", "kasun@gmail.com", "1234", "Customer");
        boolean updateResult = instance.updateCustomerProfile(customer);
        assertEquals(true, updateResult);
        result = instance.searchUser(customer.getUserName());
        assertEquals(customer.getUserName(), result.getUserName());
        assertEquals(customer.getPhoneNumber(), result.getPhoneNumber());
        assertEquals(customer.getAddress(), result.getAddress());
        assertEquals(customer.getAge(), result.getAge());
        assertEquals(customer.getUserFirstName(), result.getUserFirstName());
        assertEquals(customer.getUserLastName(), result.getUserLastName());
        assertEquals(customer.getUserEmail(), result.getUserEmail());
        assertEquals(customer.getUserPassword(), result.getUserPassword());
        assertEquals(customer.getUserType(), result.getUserType());
        
        // Testing delete customer
        boolean deleteResult = instance.deleteUser(customer.getUserName());
        assertEquals(true, deleteResult);
        result = instance.searchUser(customer.getUserName());
        assertEquals(null, result);
    }
    
}
