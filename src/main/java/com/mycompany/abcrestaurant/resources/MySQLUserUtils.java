package com.mycompany.abcrestaurant.resources;

import java.sql.Connection;
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
            
            while(rs.next()){
                
                user.add(new User(rs.getInt("userID"),rs.getString("userName"),rs.getString("userFirstName"),rs.getString("userLastName"),rs.getString("userEmail"),rs.getString("userPassword"),rs.getString("userType")));
  
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
    public boolean userLogin() {
        return false;
    }


}
