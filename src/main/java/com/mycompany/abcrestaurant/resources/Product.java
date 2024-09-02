/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.abcrestaurant.resources;

/**
 *
 * @author tharindulakshan
 */
public class Product {

    private String productID;
    private String productName;
    private String productDescription;
    private double productPrice;
    private String productImage;
    private String productDiscountAvailability;
    private double productDiscount;
    private String categoryID;

    public Product() {
    }


    public Product(String productID, String productName, String productDescription, double productPrice, String productImage, String productDiscountAvailability, double productDiscount, String categoryID) {
        this.productID = productID;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productImage = productImage;
        this.productDiscountAvailability = productDiscountAvailability;
        this.productDiscount = productDiscount;
        this.categoryID = categoryID;
    }
    

    public String getProductDiscountAvailability() {
        return productDiscountAvailability;
    }

    public void setProductDiscountAvailability(String productDiscountAvailability) {
        this.productDiscountAvailability = productDiscountAvailability;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
    
    

    
    

    public double getProductDiscount() {
        return productDiscount;
    }

    public void setProductDiscount(double productDiscount) {
        this.productDiscount = productDiscount;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }
    
    
}
