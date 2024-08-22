package com.mycompany.abcrestaurant.resources;

import java.util.List;

/**
 *
 * @author tharindulakshan
 */
public interface DBUserUtils {
    
    public List<User> getUsers();
    
    public User getUser();
    
    public boolean addUser();
    
    public boolean updateUser();
    
    public boolean deleteUser();
    
    public boolean userLogin();
    
    public boolean addCustomer(Customer customer);
    
    
}
