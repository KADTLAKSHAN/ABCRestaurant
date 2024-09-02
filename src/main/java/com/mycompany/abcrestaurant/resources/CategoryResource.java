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
@Path("category")
public class CategoryResource {
    
    Gson gson = new Gson();
    DBCategoryUtils categoryUtils = new MySQLCategoryUtils();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCategories(){
        
        return Response
                .ok(gson.toJson(categoryUtils.getAllCategories()))
                .build();
        
        
    }
    
    @POST
    @Path("/addcategory")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCategory(String json){
        
        System.out.println(json);
        
        if(categoryUtils.addCategory(gson.fromJson(json, Category.class))){
            return Response
                .status(Response.Status.CREATED)
                .build();
        }else{
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .build();
        }
        
    }
    
    @DELETE
    @Path("/deletecategory/{categoryID}")
    public Response deleteCategory(@PathParam("categoryID") String categoryID){
        
        if(categoryUtils.deleteCategory(categoryID)){
            
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
    @Path("/searchcategory/{categoryID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategory(@PathParam("categoryID") String categoryID){
        
        Category category = categoryUtils.searchCategory(categoryID);
        
        if(category == null){
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }else{
            return Response
                    .ok(gson.toJson(category))
                    .build();
        }
        
        
        
    }
    
    @PUT
    @Path("/updatecategory/{categoryID}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCategory(String json, @PathParam("categoryID") String categoryID){
         
        
        if(categoryUtils.updateCategory(gson.fromJson(json, Category.class))){
            
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
