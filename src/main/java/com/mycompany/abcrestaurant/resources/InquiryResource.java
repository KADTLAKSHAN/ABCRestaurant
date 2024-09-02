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
@Path("inquiry")
public class InquiryResource {

    Gson gson = new Gson();
    DBInquiryUtils inquiryUtils = new MySQLInquryUtils();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllInquries(){
        
        return Response
                .ok(gson.toJson(inquiryUtils.getAllInquiry()))
                .build();
        
        
    }
    
    @POST
    @Path("/addinquiry")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addInquiry(String json){
        
        if(inquiryUtils.addInquiry(gson.fromJson(json, Inquiry.class))){
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
    @Path("/deleteinquiry/{inquiryid}")
    public Response deleteInquiry(@PathParam("inquiryid") int inquiryid){
        
        if(inquiryUtils.deleteInquiry(inquiryid)){
            
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
    @Path("/searchinquiry/{inquiryid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInquiry(@PathParam("inquiryid") int inquiryid){
        
        Inquiry inquiry = inquiryUtils.searchInquiry(inquiryid);
        
        if(inquiry == null){
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }else{
            return Response
                    .ok(gson.toJson(inquiry))
                    .build();
        }
        
        
        
    }
    
    @PUT
    @Path("/updateinquiry/{inquiryid}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateInquiry(String json, @PathParam("inquiryid") int inquiryid){
         
        
        if(inquiryUtils.updateInquiry(gson.fromJson(json, Inquiry.class))){
            
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
