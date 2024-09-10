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
public interface DBInquiryUtils {
    
    public boolean addInquiry(Inquiry inquiry);
    
    public boolean updateInquiry(Inquiry inquiry);
    
    public boolean deleteInquiry(int inquireID);
    
    public Inquiry searchInquiry(int inquireID);
    
    public List<Inquiry> getAllInquiry();
    
    public boolean checkUserExist(String userName);
    
    public List<Inquiry> getAllInquiryByUser(String userName);
    
    public List<Inquiry> getAllSortedInquiryForManager();
    
    
    
}
