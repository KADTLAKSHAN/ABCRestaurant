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
public interface DBRateUtils {
    
    public boolean addRate(Rate rate);
    
    public List<Rate> getAllRateByUser(String user);
    
    public List<Rate> getAllRates();
    
    public List<Map<String, Object>> getAllRatingWithName();
    
}
