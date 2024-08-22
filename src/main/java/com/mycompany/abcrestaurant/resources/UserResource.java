package com.mycompany.abcrestaurant.resources;

import com.google.gson.Gson;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
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
    
    @POST
    @Path("/customerregister")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCustomer(String json){
        
        utils.addCustomer(gson.fromJson(json, Customer.class));
        return Response
                .status(Response.Status.CREATED)
                .build();
        
    }
    
    
    @POST
    @Path("/userlogin")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response userLogin(String json){
        
        User user = gson.fromJson(json, User.class);
        if(utils.userLogin(user.getUserName(), user.getUserPassword(), user.getUserType())){
            
            return Response
                    .status(Response.Status.OK)
                    .build();
            
        }else{
            
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .build();
            
        }
        
        
    }
    
    
    
    
}
