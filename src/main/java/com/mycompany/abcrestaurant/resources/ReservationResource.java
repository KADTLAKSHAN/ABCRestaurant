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
import java.util.HashMap;
import java.util.Map;

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
    public Response getAllReservation() {

        return Response
                .ok(gson.toJson(reservationUtils.getAllReservations()))
                .build();

    }

    @GET
    @Path("/findreservations/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReservations(@PathParam("userName") String userName) {

        return Response
                .ok(gson.toJson(reservationUtils.getAllReservationsByCustomer(userName)))
                .build();

    }

    @POST
    @Path("/makereservation")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response makeReservation(String json) {

        Reservation reservation = gson.fromJson(json, Reservation.class);

        if (reservationUtils.makeReservation(reservation)) {
            return Response
                    .status(Response.Status.CREATED)
                    .build();
        } else {
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .build();
        }

    }

    @DELETE
    @Path("/deletereservations/{reservationid}")
    public Response deleteReservation(@PathParam("reservationid") String reservationid) {

        if (reservationUtils.deleteReservation(reservationid)) {

            return Response
                    .status(Response.Status.OK)
                    .build();

        } else {

            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();

        }

    }

    @GET
    @Path("/searchreservation/{reservationid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReservation(@PathParam("reservationid") String reservationid) {

        Reservation reservation = reservationUtils.searchReservation(reservationid);

        if (reservation == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        } else {
            return Response
                    .ok(gson.toJson(reservation))
                    .build();
        }

    }

    @PUT
    @Path("/updatereservation/{reservationid}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateReservation(String json, @PathParam("reservationid") int reservationID) {

        if (reservationUtils.updateReservation(gson.fromJson(json, Reservation.class))) {

            return Response
                    .status(Response.Status.OK)
                    .build();

        } else {

            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();

        }

    }

    @GET
    @Path("/getGenerateID/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGeneratedID(@PathParam("userName") String userName) {

        String generatedReservationID = reservationUtils.generatedID(userName);

        if (generatedReservationID.equals("")) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        } else {

            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("generatedReservationID", generatedReservationID);
            return Response
                    .ok(gson.toJson(responseMap))
                    .build();

        }
    }

    @POST
    @Path("/makepayment")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response makePayment(String json) {

        Payment payment = gson.fromJson(json, Payment.class);

        if (reservationUtils.reservationPayment(payment)) {
            return Response
                    .status(Response.Status.CREATED)
                    .build();
        } else {
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .build();
        }

    }

    @GET
    @Path("/getDisabledates")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDisableDates() {

        return Response.ok(gson.toJson(reservationUtils.availability()))
                .build();

    }

    @GET
    @Path("/getAllDisableDates")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDisableDates() {

        return Response
                .ok(gson.toJson(reservationUtils.getAllReservationDates()))
                .build();

    }

    @POST
    @Path("/addDate")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDisableDate(String json) {

        Date date = gson.fromJson(json, Date.class);

        if (reservationUtils.addReservationDate(date)) {
            return Response
                    .status(Response.Status.CREATED)
                    .build();
        } else {
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .build();
        }

    }

    @PUT
    @Path("/updateDate/{date}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDisableDate(String json, @PathParam("date") String date) {

        if (reservationUtils.updateReservationDate(gson.fromJson(json, Date.class))) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }
    
    @DELETE
    @Path("/deleteDate/{date}")
    public Response deleteDisableDate(@PathParam("date") String date){
        
        if(reservationUtils.deleteReservationDate(date)){
            
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
    @Path("/searchDate/{date}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchDisableDate(@PathParam("date") String date){
        
        Date dt = reservationUtils.searchDate(date);
        
        if(dt == null){
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }else{
            return Response
                    .ok(gson.toJson(dt))
                    .build();
        }
        
        
    }

}
