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
import java.util.UUID;

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
            
            String token = UUID.randomUUID().toString();
            
            LoginResponse loginResponse = new LoginResponse(token, user.getUserName(), user.getUserType());
            
            return Response
                    .status(Response.Status.OK)
                    .entity(loginResponse)
                    .build();
            
        }else{
            
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .build();
            
        }
        
        
    }
    
    @POST
    @Path("/adduser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(String json){
        
        utils.addUser(gson.fromJson(json, Customer.class));
        return Response
                .status(Response.Status.CREATED)
                .build();
        
    }
    
    
    @DELETE
    @Path("/deleteuser/{userName}")
    public Response deleteUser(@PathParam("userName") String userName){
        
        if(utils.deleteUser(userName)){
            
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
    @Path("/searchuser/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("userName") String userName){
        
        Customer user = utils.searchUser(userName);
        
        if(user == null){
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }else{
            return Response
                    .ok(gson.toJson(user))
                    .build();
        }
        
        
        
    }
    
    @PUT
    @Path("/updateuser/{userName}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(String json, @PathParam("userName") String userName){
         
        
        if(utils.updateUsers(gson.fromJson(json, Customer.class))){
            
            return Response
                    .status(Response.Status.OK)
                    .build();
            
            
        }else{
            
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
            
        }
        
        
        
    }
    
    @PUT
    @Path("/updatecustomer")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCustomer(String json){
        
        if(utils.updateCustomerProfile(gson.fromJson(json, Customer.class))){
            
            return Response
                    .status(Response.Status.OK)
                    .build();
            
        }else{
            
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
            
        }
        
        
    }
    
    
    
    public static class LoginResponse{
        
        private String token;
        private String userName;
        private String userType;
        
        public LoginResponse(String token, String userName, String userType){
            
            this.token = token;
            this.userName = userName;
            this.userType = userType;
            
        }
        
        
        public String getToken(){
            
            return token;
            
        }
        
        public void setToken(String token){
            
            this.token = token;
            
            
        }
        
        public String getUserName(){
            
            return userName;
            
        }
        
        public void setUserName(String userName){
            
            this.userName = userName;
            
        }
        
        public String getUserType(){
            
            return userType;
            
        }
        
        public void setUserType(String userType){
            
            this.userType = userType;
            
        }
        
        
        
    }
    
    
    
    
    
}
