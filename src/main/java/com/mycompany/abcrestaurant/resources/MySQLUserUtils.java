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
    public List<Customer> getUsers() {

        List<Customer> user = new ArrayList<>();

        String sql = "SELECT * FROM tblUser";

        try {

            MyConnection myCon = MyConnection.getInstance();
            myCon.getConnection();
            ResultSet rs = myCon.executeQuery(myCon.getPreparedStatement(sql));

            while (rs.next()) {

                user.add(new Customer(rs.getString("userPhoneNumber"),rs.getString("userAddress"),rs.getInt("userAge"),rs.getString("userName"), rs.getString("userFirstName"), rs.getString("userLastName"), rs.getString("userEmail"), rs.getString("userPassword"), rs.getString("userType")));

            }


        } catch (SQLException e) {

            return null;

        }

        return user;

    }

    @Override
    public Customer searchUser(String userName) {
        String sql = "SELECT * FROM tblUser WHERE userName=?";
        Customer user = null;
        
        try {
            
            MyConnection myConnection = MyConnection.getInstance();
            myConnection.getConnection();
            PreparedStatement pst = myConnection.getPreparedStatement(sql);
            pst.setString(1, userName);
            
            ResultSet resultSet = pst.executeQuery();
            
            if(resultSet.next()){
                
                user = new Customer(resultSet.getString(7), resultSet.getString(5), resultSet.getInt(6), resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),resultSet.getString(4) , resultSet.getString(8), resultSet.getString(9));
   
            }
            
            
        } catch (Exception e) {
            
            System.out.println("Search User MySQL Util Error (Database) ::::" + e);
            
            return null;
            
        }
        
        return user;
        
        
    }

    @Override
    public boolean addUser(Customer user) {
        String sql = "INSERT INTO tblUser (userName, userFirstName, userLastName, userEmail, userAddress, userAge, userPhoneNumber, userPassword, userType) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {

            MyConnection myConnection = MyConnection.getInstance();
            myConnection.getConnection();
            PreparedStatement pst = myConnection.getPreparedStatement(sql);
            pst.setString(1, user.getUserName());
            pst.setString(2, user.getUserFirstName());
            pst.setString(3, user.getUserLastName());
            pst.setString(4, user.getUserEmail());
            pst.setString(5, user.getAddress());
            pst.setInt(6, user.getAge());
            pst.setString(7, user.getPhoneNumber());
            pst.setString(8, user.getUserPassword());
            pst.setString(9, user.getUserType());

            int rowAffected = pst.executeUpdate();

            return rowAffected == 1;

        } catch (Exception e) {

            System.out.println("Add User MySQL Util Error (Database) ::::" + e);
            return false;

        }
    }

    @Override
    public boolean updateUser() {
        return false;
    }

    @Override
    public boolean deleteUser(String userName) {
        String sql = "DELETE FROM tblUser WHERE userName=?";
        
        try {
            
            MyConnection myConnection = MyConnection.getInstance();
            myConnection.getConnection();
            PreparedStatement pst = myConnection.getPreparedStatement(sql);
            pst.setString(1, userName);
            
            if(pst.executeUpdate() == 1){
                
                return true;
                
            }
            
        } catch (Exception e) {
            
            System.out.println("deleteUser Function error Database::: " + e);
            
        }
        
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
