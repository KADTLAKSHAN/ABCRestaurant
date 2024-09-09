package com.mycompany.abcrestaurant.resources;

/**
 *
 * @author tharindulakshan
 */
public class Reservation {
    
    private int reservationID;
    private String reservationEmail;
    private String reservationDate;
    private String reservationTime;
    private int reservationPeople;
    private String userName;

    public Reservation(int reservationID, String reservationEmail, String reservationDate, String reservationTime, int reservationPeople, String userName) {
        this.reservationID = reservationID;
        this.reservationEmail = reservationEmail;
        this.reservationDate = reservationDate;
        this.reservationTime = reservationTime;
        this.reservationPeople = reservationPeople;
        this.userName = userName;
    }

    public Reservation(String reservationEmail, String reservationDate, String reservationTime, int reservationPeople, String userName) {
        this.reservationEmail = reservationEmail;
        this.reservationDate = reservationDate;
        this.reservationTime = reservationTime;
        this.reservationPeople = reservationPeople;
        this.userName = userName;
    }
    
    

    public Reservation() {
        
    }

    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public String getReservationEmail() {
        return reservationEmail;
    }

    public void setReservationEmail(String reservationEmail) {
        this.reservationEmail = reservationEmail;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(String reservationTime) {
        this.reservationTime = reservationTime;
    }

    public int getReservationPeople() {
        return reservationPeople;
    }

    public void setReservationPeople(int reservationPeople) {
        this.reservationPeople = reservationPeople;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    
    
    
    
    
    

    
}
