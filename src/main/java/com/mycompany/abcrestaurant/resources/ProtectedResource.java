/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.abcrestaurant.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author tharindulakshan
 */
@Path("/protected")
public class ProtectedResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProtectedResources(@HeaderParam("Authorization") String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        String token = authHeader.substring(7);
        if (isValidToken(token)) {
            return Response.ok("Protected resource accessed").build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

    }
    
    
    private boolean isValidToken(String token) {
        
        return token != null && !token.isEmpty();
    }

}
