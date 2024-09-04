/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.abcrestaurant.resources;

import com.google.gson.Gson;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author tharindulakshan
 */
@Path("rates")
public class RateResource {

    Gson gson = new Gson();
    DBRateUtils rateUtils = new MySQLRateUtils();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRates(){
        
        return Response
                .ok(gson.toJson(rateUtils.getAllRates()))
                .build();
        
        
    }
    
    @GET
    @Path("/findrate/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRatesByUser(@PathParam("userName") String userName){
        
        return Response
                .ok(gson.toJson(rateUtils.getAllRateByUser(userName)))
                .build();
        
    }
    
    @POST
    @Path("/addrate")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addRate(String json){
        
        if (rateUtils.addRate(gson.fromJson(json, Rate.class))) {
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
