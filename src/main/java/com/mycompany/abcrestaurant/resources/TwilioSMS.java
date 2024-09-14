package com.mycompany.abcrestaurant.resources;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author tharindulakshan
 */
public class TwilioSMS {

    public void sendSms(Reservation reservation) {

//        String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
//        String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");
//        String TWILIO_PHONE_NUMBER = System.getenv("TWILIO_PHONE_NUMBER");



        String configFilePath = "/Users/tharindulakshan/Documents/twilio_config.txt";

        Map<String, String> config = readConfigFile(configFilePath);

        String ACCOUNT_SID = config.get("TWILIO_ACCOUNT_SID");
        String AUTH_TOKEN = config.get("TWILIO_AUTH_TOKEN");
        String TWILIO_PHONE_NUMBER = config.get("TWILIO_PHONE_NUMBER");

        String textMessage = formatCustomerMessage(reservation);
        String receiver_phone_number = formatedPhonenumber(reservation.getUserName());

        if (ACCOUNT_SID == null || AUTH_TOKEN == null) {
            System.err.println("Environment variables TWILIO_ACCOUNT_SID and TWILIO_AUTH_TOKEN must be set.");
            return;
        }

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(
                new PhoneNumber(receiver_phone_number),
                new PhoneNumber(TWILIO_PHONE_NUMBER),
                textMessage
        ).create();

        System.out.println(message.getSid());

    }

    private Map<String, String> readConfigFile(String filePath) {
        Map<String, String> config = new HashMap<>();
        try {
            Files.lines(Paths.get(filePath)).forEach(line -> {
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    config.put(parts[0].trim(), parts[1].trim());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }

    public String formatCustomerMessage(Reservation reservation) {

        DBUserUtils user = new MySQLUserUtils();
        Customer customer = user.searchUser(reservation.getUserName());
        String user_fullname = customer.getUserFirstName() + " " + customer.getUserLastName();

        String customerName = user_fullname;
        String restaurantName = "ABC Restaurant";
        String reservationId = reservation.getReservationID();
        String reservationEmail = reservation.getReservationEmail();
        String reservationDate = reservation.getReservationDate();
        String reservationTime = reservation.getReservationTime();
        int reservationPeople = reservation.getReservationPeople();
        String loginUserName = reservation.getUserName();

        String message = String.format(
                "Hi %s, your reservation at %s is confirmed.\n\n"
                + "Reservation ID: %s\n"
                + "Email: %s\n"
                + "Date: %s\n"
                + "Time: %s\n"
                + "People: %d\n"
                + "Username: %s\n\n"
                + "If you need to make changes, reply to this message or call us at [Restaurant Phone Number]. Looking forward to serving you!",
                customerName, restaurantName, reservationId, reservationEmail,
                reservationDate, reservationTime, reservationPeople, loginUserName
        );

        return message;
    }

    public String formatedPhonenumber(String username) {

        DBUserUtils user = new MySQLUserUtils();
        Customer cust = user.searchUser(username);
        String phonenumber = cust.getPhoneNumber();

        phonenumber = phonenumber.trim();

        if (phonenumber.startsWith("0")) {
            return "+94" + phonenumber.substring(1);
        }

        return phonenumber;

    }

}
