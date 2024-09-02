/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.abcrestaurant.resources;

/**
 *
 * @author tharindulakshan
 */
public class Inquiry {

    private int inquireID;
    private String userName;
    private String inquireType;
    private String inquireDescription;
    private String inquireFeedback;
    private String inquireReply;
    private String inquireDate;

    public Inquiry() {
    }

    public Inquiry(int inquireID, String userName, String inquireType, String inquireDescription, String inquireFeedback, String inquireReply, String inquireDate) {
        this.inquireID = inquireID;
        this.userName = userName;
        this.inquireType = inquireType;
        this.inquireDescription = inquireDescription;
        this.inquireFeedback = inquireFeedback;
        this.inquireReply = inquireReply;
        this.inquireDate = inquireDate;
    }

    public int getInquireID() {
        return inquireID;
    }

    public void setInquireID(int inquireID) {
        this.inquireID = inquireID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getInquireType() {
        return inquireType;
    }

    public void setInquireType(String inquireType) {
        this.inquireType = inquireType;
    }

    public String getInquireDescription() {
        return inquireDescription;
    }

    public void setInquireDescription(String inquireDescription) {
        this.inquireDescription = inquireDescription;
    }

    public String getInquireFeedback() {
        return inquireFeedback;
    }

    public void setInquireFeedback(String inquireFeedback) {
        this.inquireFeedback = inquireFeedback;
    }

    public String getInquireReply() {
        return inquireReply;
    }

    public void setInquireReply(String inquireReply) {
        this.inquireReply = inquireReply;
    }

    public String getInquireDate() {
        return inquireDate;
    }

    public void setInquireDate(String inquireDate) {
        this.inquireDate = inquireDate;
    }
    
    
    
    
    
    
}
