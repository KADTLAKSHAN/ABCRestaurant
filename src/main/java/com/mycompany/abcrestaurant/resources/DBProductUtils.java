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
public interface DBProductUtils {
    
    public boolean addProduct(Product product);
    
    public boolean deleteProduct(String productID);
    
    public boolean updateProduct(Product product);
    
    public Product searchProduct(String productID);
    
    public List<Product> getAllProduct();
    
}
