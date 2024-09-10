/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.abcrestaurant.resources;

/**
 *
 * @author tharindulakshan
 */
public class Rate {

    private int rateID;
    private String rateTitle;
    private String rateDescription;
    private String userName;

    public Rate() {
    }

    public Rate(int rateID, String rateTitle, String rateDescription, String userName) {
        this.rateID = rateID;
        this.rateTitle = rateTitle;
        this.rateDescription = rateDescription;
        this.userName = userName;
    }

    public int getRateID() {
        return rateID;
    }

    public void setRateID(int rateID) {
        this.rateID = rateID;
    }

    public String getRateTitle() {
        return rateTitle;
    }

    public void setRateTitle(String rateTitle) {
        this.rateTitle = rateTitle;
    }

    public String getRateDescription() {
        return rateDescription;
    }

    public void setRateDescription(String rateDescription) {
        this.rateDescription = rateDescription;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    
}
