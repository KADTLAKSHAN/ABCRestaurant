/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.abcrestaurant.resources;

import com.google.gson.Gson;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
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

    
}
