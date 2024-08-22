package com.mycompany.abcrestaurant.resources;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tharindulakshan
 */
public class MySQLUserUtils implements DBUserUtils {

    @Override
    public List<User> getUsers() {

        List<User> user = new ArrayList<>();

        String sql = "SELECT * FROM tblUser";

        try {

            MyConnection myCon = MyConnection.getInstance();
            myCon.getConnection();
            ResultSet rs = myCon.executeQuery(myCon.getPreparedStatement(sql));

            while (rs.next()) {

                user.add(new User(rs.getString("userName"), rs.getString("userFirstName"), rs.getString("userLastName"), rs.getString("userEmail"), rs.getString("userPassword"), rs.getString("userType")));

            }

            myCon.clear();

        } catch (SQLException e) {

            return null;

        }

        return user;

    }

    @Override
    public User getUser() {
        return null;
    }

    @Override
    public boolean addUser() {
        return false;
    }

    @Override
    public boolean updateUser() {
        return false;
    }

    @Override
    public boolean deleteUser() {
        return false;
    }

    @Override
    public boolean userLogin(String userName, String userPassword, String userType) {

        String sql = "SELECT * FROM tblUser WHERE BINARY userName=? AND userPassword=? AND userType=?";

        try {

            MyConnection myConnection = MyConnection.getInstance();
            myConnection.getConnection();
            PreparedStatement pst = myConnection.getPreparedStatement(sql);
            pst.setString(1, userName);
            pst.setString(2, userPassword);
            pst.setString(3, userType);

            ResultSet resultSet = pst.executeQuery();
            

            if (resultSet.isBeforeFirst()) {

                return true;

            }

        } catch (Exception e) {

            System.out.println("userLogin Function error Database::: " + e);

        }

        return false;

    }

    @Override
    public boolean addCustomer(Customer customer) {

        String sql = "INSERT INTO tblUser (userName, userFirstName, userLastName, userEmail, userAddress, userAge, userPhoneNumber, userPassword, userType) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {

            MyConnection myConnection = MyConnection.getInstance();
            myConnection.getConnection();
            PreparedStatement pst = myConnection.getPreparedStatement(sql);
            pst.setString(1, customer.getUserName());
            pst.setString(2, customer.getUserFirstName());
            pst.setString(3, customer.getUserLastName());
            pst.setString(4, customer.getUserEmail());
            pst.setString(5, customer.getAddress());
            pst.setInt(6, customer.getAge());
            pst.setString(7, customer.getPhoneNumber());
            pst.setString(8, customer.getUserPassword());
            pst.setString(9, customer.getUserType());

            int rowAffected = pst.executeUpdate();

            return rowAffected == 1;

        } catch (Exception e) {

            System.out.println("Add Customer MySQL Util Error (Database) ::::" + e);
            return false;

        }

    }

}
