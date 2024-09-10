/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
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
public class MySQLCategoryUtils implements DBCategoryUtils{

    @Override
    public boolean addCategory(Category category) {
        String sql = "INSERT INTO tblCategory (categoryID, categoryName) VALUES (?, ?)";

        try {

            
                MyConnection myConnection = MyConnection.getInstance();
                myConnection.getConnection();

                PreparedStatement pst = myConnection.getPreparedStatement(sql);
                pst.setString(1, category.getCategoryID());
                pst.setString(2, category.getCategoryName());

                int rowAffected = pst.executeUpdate();

                return rowAffected == 1;


        } catch (SQLException e) {

            System.out.println("add category MySQL Util Error (Database) ::::" + e);
            return false;

        }
    }

    @Override
    public boolean updateCategory(Category category) {
        String sql = "UPDATE tblCategory SET categoryName=? WHERE categoryID=?";
        
        try {
            
            MyConnection myConnection = MyConnection.getInstance();
            myConnection.getConnection();
            PreparedStatement pst = myConnection.getPreparedStatement(sql);
            pst.setString(1, category.getCategoryName());
            pst.setString(2, category.getCategoryID());
            
            if(pst.executeUpdate() == 1){
                return true;
            }
            
            
            
        } catch (Exception e) {
            
            System.out.println("updateCategory Function error Database::: " + e);
            
        }
        
        return false;
    }

    @Override
    public boolean deleteCategory(String categoryID) {
        String sql = "DELETE FROM tblCategory WHERE categoryID=?";
        
        try {
            
            MyConnection myConnection = MyConnection.getInstance();
            myConnection.getConnection();
            PreparedStatement pst = myConnection.getPreparedStatement(sql);
            pst.setString(1, categoryID);
            
            if(pst.executeUpdate() == 1){
                
                return true;
                
            }
            
        } catch (Exception e) {
            
            System.out.println("deleteCategory Function error Database::: " + e);
            
        }
        
        return false;
    }

    @Override
    public Category searchCategory(String categoryID) {
        String sql = "SELECT * FROM tblCategory WHERE categoryID=?";
        Category category = null;
        
        try {
            
            MyConnection myConnection = MyConnection.getInstance();
            myConnection.getConnection();
            PreparedStatement pst = myConnection.getPreparedStatement(sql);
            pst.setString(1, categoryID);
            
            ResultSet resultSet = pst.executeQuery();
            
            if(resultSet.next()){
                
                category = new Category(resultSet.getString(1), resultSet.getString(2));
                
   
            }
            
            
        } catch (Exception e) {
            
            System.out.println("Search Category MySQL Util Error (Database) ::::" + e);
            
            return null;
            
        }
        
        return category;
    }

    @Override
    public List<Category> getAllCategories() {
        
        List<Category> categories = new ArrayList<>();

        String sql = "SELECT * FROM tblCategory";

        try {

            MyConnection myCon = MyConnection.getInstance();
            myCon.getConnection();
            ResultSet rs = myCon.executeQuery(myCon.getPreparedStatement(sql));

            while (rs.next()) {
                
                categories.add(new Category(rs.getString(1), rs.getString(2)));


            }


        } catch (SQLException e) {

            return null;

        }

        return categories;
        
    }

    
}
