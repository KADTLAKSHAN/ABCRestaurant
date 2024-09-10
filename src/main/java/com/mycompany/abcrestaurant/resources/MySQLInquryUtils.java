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
public class MySQLInquryUtils implements DBInquiryUtils{

    @Override
    public boolean addInquiry(Inquiry inquiry) {
        String sql = "INSERT INTO tblInquire (userName, inquireType, inquireDescription, inquireFeedback, inquireReply, inquireDate) VALUES (?, ?, ?, ?, ?, ?)";

        try {

            if (checkUserExist(inquiry.getUserName())) {
                MyConnection myConnection = MyConnection.getInstance();
                myConnection.getConnection();

                PreparedStatement pst = myConnection.getPreparedStatement(sql);
                pst.setString(1, inquiry.getUserName());
                pst.setString(2, inquiry.getInquireType());
                pst.setString(3, inquiry.getInquireDescription());
                pst.setString(4, inquiry.getInquireFeedback());
                pst.setString(5, inquiry.getInquireReply());
                pst.setString(6, inquiry.getInquireDate());

                int rowAffected = pst.executeUpdate();

                return rowAffected == 1;
            }else{
                System.out.println("User not exist..");
                return false;
            }

        } catch (SQLException e) {

            System.out.println("add inquiry MySQL Util Error (Database) ::::" + e);
            return false;

        }
    }

    @Override
    public boolean updateInquiry(Inquiry inquiry) {
        String sql = "UPDATE tblInquire SET userName=?, inquireType=?, inquireDescription=?, inquireFeedback=?, inquireReply=?, inquireDate=? WHERE inquireID=?";
        
        try {
            
            MyConnection myConnection = MyConnection.getInstance();
            myConnection.getConnection();
            PreparedStatement pst = myConnection.getPreparedStatement(sql);
            pst.setString(1, inquiry.getUserName());
            pst.setString(2, inquiry.getInquireType());
            pst.setString(3, inquiry.getInquireDescription());
            pst.setString(4, inquiry.getInquireFeedback());
            pst.setString(5, inquiry.getInquireReply());
            pst.setString(6, inquiry.getInquireDate());
            pst.setInt(7, inquiry.getInquireID());

            
                 
            if(pst.executeUpdate() == 1){
                return true;
            }
            
            
            
        } catch (Exception e) {
            
            System.out.println("updateInquiry Function error Database::: " + e);
            
        }
        
        return false;
    }

    @Override
    public boolean deleteInquiry(int inquireID) {
        String sql = "DELETE FROM tblInquire WHERE inquireID=?";
        
        try {
            
            MyConnection myConnection = MyConnection.getInstance();
            myConnection.getConnection();
            PreparedStatement pst = myConnection.getPreparedStatement(sql);
            pst.setInt(1, inquireID);
            
            if(pst.executeUpdate() == 1){
                
                return true;
                
            }
            
        } catch (Exception e) {
            
            System.out.println("deleteInquiry Function error Database::: " + e);
            
        }
        
        return false;
    }

    @Override
    public Inquiry searchInquiry(int inquireID) {
        String sql = "SELECT * FROM tblInquire WHERE inquireID=?";
        Inquiry inquiry = null;
        
        try {
            
            MyConnection myConnection = MyConnection.getInstance();
            myConnection.getConnection();
            PreparedStatement pst = myConnection.getPreparedStatement(sql);
            pst.setInt(1, inquireID);
            
            ResultSet resultSet = pst.executeQuery();
            
            if(resultSet.next()){
                
                inquiry = new Inquiry(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7));
   
            }
            
            
        } catch (Exception e) {
            
            System.out.println("Search Inqury MySQL Util Error (Database) ::::" + e);
            
            return null;
            
        }
        
        return inquiry;
    }

    @Override
    public List<Inquiry> getAllInquiry() {
        List<Inquiry> inquiries = new ArrayList<>();

        String sql = "SELECT * FROM tblInquire";

        try {

            MyConnection myCon = MyConnection.getInstance();
            myCon.getConnection();
            ResultSet rs = myCon.executeQuery(myCon.getPreparedStatement(sql));

            while (rs.next()) {

                inquiries.add(new Inquiry(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));

            }


        } catch (SQLException e) {

            return null;

        }

        return inquiries;
    }

    @Override
    public boolean checkUserExist(String userName) {
        String sql = "SELECT * FROM tblUser WHERE BINARY userName=?";

        try {

            MyConnection myConnection = MyConnection.getInstance();
            myConnection.getConnection();
            PreparedStatement pst = myConnection.getPreparedStatement(sql);
            pst.setString(1, userName);

            ResultSet resultSet = pst.executeQuery();

            if (resultSet.isBeforeFirst()) {

                return true;

            }

        } catch (SQLException e) {

            System.out.println("checkUserExist Function error Database::: " + e);

        }

        return false;
    }

    @Override
    public List<Inquiry> getAllInquiryByUser(String userName) {
        List<Inquiry> inquiries = new ArrayList<>();

        String sql = "SELECT * FROM tblInquire WHERE userName=?";

        try {

            MyConnection myCon = MyConnection.getInstance();
            myCon.getConnection();
            PreparedStatement pst = myCon.getPreparedStatement(sql);
            pst.setString(1, userName);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                inquiries.add(new Inquiry(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));

            }


        } catch (SQLException e) {

            return null;

        }

        return inquiries;
    }

    @Override
    public List<Inquiry> getAllSortedInquiryForManager() {
        
        List<Inquiry> inquiries = new ArrayList<>();
        
        String sql = "SELECT * FROM tblInquire ORDER BY CASE WHEN inquireReply='No' THEN 1 WHEN inquireReply='Yes' THEN 2 ELSE 3 END, inquireDate DESC";
        
        try {

            MyConnection myCon = MyConnection.getInstance();
            myCon.getConnection();
            ResultSet rs = myCon.executeQuery(myCon.getPreparedStatement(sql));

            while (rs.next()) {

                inquiries.add(new Inquiry(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));

            }


        } catch (SQLException e) {

            return null;

        }

        return inquiries;
    }

    
}
