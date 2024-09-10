package com.mycompany.abcrestaurant.resources;

import java.util.List;

/**
 *
 * @author tharindulakshan
 */
public interface DBUserUtils {
    
    public List<Customer> getUsers();
    
    public Customer searchUser(String userName);
    
    public boolean addUser(Customer customer);
    
    public boolean updateUsers(Customer user);
    
    public boolean deleteUser(String userName);
    
    public boolean userLogin(String userName, String userPassword, String userType);
    
    public boolean addCustomer(Customer customer);
    
    public boolean updateCustomerProfile(Customer customer);
    
    
}
