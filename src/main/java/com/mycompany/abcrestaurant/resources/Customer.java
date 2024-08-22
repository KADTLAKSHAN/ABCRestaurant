package com.mycompany.abcrestaurant.resources;

/**
 *
 * @author tharindulakshan
 */
public class Customer extends User{

    private String userPhoneNumber;
    private String userAddress;
    private int userAge;

    public Customer(String userPhoneNumber, String userAddress, int userAge, int userID, String userName, String userFirstName, String userLastName, String userEmail, String userPassword, String userType) {
        super(userID, userName, userFirstName, userLastName, userEmail, userPassword, userType);
        this.userPhoneNumber = userPhoneNumber;
        this.userAddress = userAddress;
        this.userAge = userAge;
    }

    

    public Customer() {
        
        
    }

    public String getPhoneNumber() {
        return userPhoneNumber;
    }

    public void setPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getAddress() {
        return userAddress;
    }

    public void setAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public int getAge() {
        return userAge;
    }

    public void setAge(int userAge) {
        this.userAge = userAge;
    }
    
    
    
    
    
   
}
