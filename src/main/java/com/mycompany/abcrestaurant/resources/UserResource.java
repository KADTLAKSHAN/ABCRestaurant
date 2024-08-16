package com.mycompany.abcrestaurant.resources;

import com.google.gson.Gson;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author tharindulakshan
 */
@Path("users")
public class UserResource {

    Gson gson = new Gson();
    DBUserUtils utils = new MySQLUserUtils();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers(){
        
        return Response
                .ok(gson.toJson(utils.getUsers()))
                .build();
        
    }
    
    
}
