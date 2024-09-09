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
@Path("reservations")
public class ReservationResource {
    
    Gson gson = new Gson();
    DBReservationUtils reservationUtils = new MySQLReservationUtils();
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllReservation(){
        
        return Response
                .ok(gson.toJson(reservationUtils.getAllReservations()))
                .build();
        
        
    }
    
    @GET
    @Path("/findreservations/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReservations(@PathParam("userName") String userName){
        
        
        return Response
                .ok(gson.toJson(reservationUtils.getAllReservationsByCustomer(userName)))
                .build();
        
    }
    
    
    
    
    @POST
    @Path("/makereservation")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response makeReservation(String json){
        
        if(reservationUtils.makeReservation(gson.fromJson(json, Reservation.class))){
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
    @Path("/deletereservations/{reservationid}")
    public Response deleteReservation(@PathParam("reservationid") int reservationid){
        
        if(reservationUtils.deleteReservation(reservationid)){
            
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
    @Path("/searchreservation/{reservationid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReservation(@PathParam("reservationid") int reservationid){
        
        Reservation reservation = reservationUtils.searchReservation(reservationid);
        
        if(reservation == null){
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }else{
            return Response
                    .ok(gson.toJson(reservation))
                    .build();
        }
        
        
        
    }
    
    
    @PUT
    @Path("/updatereservation/{reservationid}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateReservation(String json, @PathParam("reservationid") int reservationID){
         
        
        if(reservationUtils.updateReservation(gson.fromJson(json, Reservation.class))){
            
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
    @Path("/getDisabledates")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDisableDates(){
        
        return Response.ok(gson.toJson(reservationUtils.availability()))
                .build();
        
        
    }

    
}
