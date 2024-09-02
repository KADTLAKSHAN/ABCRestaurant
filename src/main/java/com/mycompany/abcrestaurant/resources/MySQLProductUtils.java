/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.abcrestaurant.resources;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 *
 * @author tharindulakshan
 */
public class MySQLProductUtils implements DBProductUtils {

    @Override
    public boolean addProduct(Product product) {

        String sql = "INSERT INTO tblProduct (productID, productName, productDescription, productPrice, productImage, productDiscountAvailability, productDiscount, categoryID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {

            MyConnection myConnection = MyConnection.getInstance();
            myConnection.getConnection();

            PreparedStatement pst = myConnection.getPreparedStatement(sql);
            pst.setString(1, product.getProductID());
            pst.setString(2, product.getProductName());
            pst.setString(3, product.getProductDescription());
            pst.setDouble(4, product.getProductPrice());

            pst.setString(5, product.getProductImage());

            pst.setString(6, product.getProductDiscountAvailability());
            pst.setDouble(7, product.getProductDiscount());
            pst.setString(8, product.getCategoryID());

            int rowAffected = pst.executeUpdate();

            return rowAffected == 1;

        } catch (SQLException e) {

            System.out.println("addProduct MySQL Util Error (Database) ::::" + e);
            return false;

        }

    }

    @Override
    public boolean deleteProduct(String productID) {

        String sql = "DELETE FROM tblProduct WHERE productID=?";

        try {

            MyConnection myConnection = MyConnection.getInstance();
            myConnection.getConnection();
            PreparedStatement pst = myConnection.getPreparedStatement(sql);
            pst.setString(1, productID);

            if (pst.executeUpdate() == 1) {

                return true;

            }

        } catch (Exception e) {

            System.out.println("deleteProduct Function error Database::: " + e);

        }

        return false;

    }

    @Override
    public boolean updateProduct(Product product) {

        String sql = "UPDATE tblProduct SET productName=?, productDescription=?, productPrice=?, productImage=?, productDiscountAvailability=?, productDiscount=?, categoryID=? WHERE productID=?";

        try {

            MyConnection myConnection = MyConnection.getInstance();
            myConnection.getConnection();
            PreparedStatement pst = myConnection.getPreparedStatement(sql);
            pst.setString(1, product.getProductName());
            pst.setString(2, product.getProductDescription());
            pst.setDouble(3, product.getProductPrice());
            pst.setString(4, product.getProductImage());
            pst.setString(5, product.getProductDiscountAvailability());
            pst.setDouble(6, product.getProductDiscount());
            pst.setString(7, product.getCategoryID());
            pst.setString(8, product.getProductID());

            if (pst.executeUpdate() == 1) {
                return true;
            }

        } catch (Exception e) {

            System.out.println("updateProduct Function error Database::: " + e);

        }

        return false;

    }

    @Override
    public Product searchProduct(String productID) {

        String sql = "SELECT * FROM tblProduct WHERE productID=?";
        Product product = null;

        try {

            MyConnection myConnection = MyConnection.getInstance();
            myConnection.getConnection();
            PreparedStatement pst = myConnection.getPreparedStatement(sql);
            pst.setString(1, productID);

            ResultSet resultSet = pst.executeQuery();

            if (resultSet.next()) {

                product = new Product(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDouble(4), resultSet.getString(5), resultSet.getString(6), resultSet.getDouble(7), resultSet.getString(8));

            }

        } catch (Exception e) {

            System.out.println("Search User MySQL Util Error (Database) ::::" + e);

            return null;

        }

        return product;

    }

    @Override
    public List<Product> getAllProduct() {
        List<Product> products = new ArrayList<>();

        String sql = "SELECT * FROM tblProduct";

        try {

            MyConnection myCon = MyConnection.getInstance();
            myCon.getConnection();
            ResultSet rs = myCon.executeQuery(myCon.getPreparedStatement(sql));

            while (rs.next()) {

                products.add(new Product(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5), rs.getString(6), rs.getDouble(7), rs.getString(8)));

            }

        } catch (SQLException e) {

            return null;

        }

        return products;
    }

}
