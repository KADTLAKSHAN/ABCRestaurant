package com.mycompany.abcrestaurant.resources;

/**
 *
 * @author tharindulakshan
 */
public class Customer extends User{

    private String phoneNumber;
    private String address;
    private int age;

    public Customer(String phoneNumber, String address, int age, int userID, String userName, String userFirstName, String userLastName, String userEmail, String userPassword, String userType) {
        super(userID, userName, userFirstName, userLastName, userEmail, userPassword, userType);
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.age = age;
    }

    

    public Customer() {
        
        
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    
    
    
    
   
}
