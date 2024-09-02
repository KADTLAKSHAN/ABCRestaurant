/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.abcrestaurant.resources;

import com.google.gson.Gson;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author tharindulakshan
 */
@Path("product")
public class ProductResource {
    
    Gson gson = new Gson();
    DBProductUtils productUtils = new MySQLProductUtils();
    
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts(){
        
        return Response
                .ok(gson.toJson(productUtils.getAllProduct()))
                .build();
        
    }
    
    @POST
    @Path("/addproduct")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProduct(String json){
        
        Product prod = gson.fromJson(json, Product.class);
        
        boolean isAddedProduct = productUtils.addProduct(prod);
        
        if(isAddedProduct){
            
            return Response
                    .status(Response.Status.CREATED)
                    .build();
            
        }else{
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
        }   
        
    }
    
    @DELETE
    @Path("/deleteproduct/{productID}")
    public Response deleteProduct(@PathParam("productID") String productID){
        
        if(productUtils.deleteProduct(productID)){
            
            return Response
                    .status(Response.Status.OK)
                    .build();
            
        }else{
            
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
            
        }
        
    }
    
    
    @GET
    @Path("/searchproduct/{productID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@PathParam("productID") String productID){
        
        Product product = productUtils.searchProduct(productID);
        
        if(product == null){
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }else{
            return Response
                    .ok(gson.toJson(product))
                    .build();
        }
        
        
        
    }
    
    @PUT
    @Path("/updateproduct/{productID}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(String json, @PathParam("productID") String productID){
         
        
        if(productUtils.updateProduct(gson.fromJson(json, Product.class))){
            
            return Response
                    .status(Response.Status.OK)
                    .build();
            
            
        }else{
            
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
            
        }
        
        
        
    }
    
    

    
}
