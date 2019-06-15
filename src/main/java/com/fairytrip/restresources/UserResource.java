package com.fairytrip.restresources;

import com.fairytrip.data.entities.User;
import com.fairytrip.restresources.jwtconfiguration.JsonTokenNeeded;
import com.fairytrip.restresources.repository.CRUD;
import com.fairytrip.restresources.repository.UserRepository;
import com.fairytrip.restresources.repository.UserRepositoryStub;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.text.SimpleDateFormat;

@Path("user")
public class UserResource {
    UserRepository userRepository = new UserRepositoryStub();
    CRUD crud = new CRUD();

    @OPTIONS
    @Path("create_user")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createUser(){
        try {
            return crud.options().build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @OPTIONS
    @Path("check_user")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response checkUser(){
        try {
            return crud.options().build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @OPTIONS
    @Path("refresh_token")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response refreshToken(){
        try {
            return crud.options().build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("create_user")
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    @Produces({MediaType.APPLICATION_JSON})
    public Response create(@FormDataParam("file") InputStream uploadedInputStream,
                           @FormDataParam("file") FormDataContentDisposition fileDetail,
                           @FormDataParam("json") FormDataBodyPart json) {
        try {
            json.setMediaType(MediaType.APPLICATION_JSON_TYPE);
            User user = json.getValueAs(User.class);
            //user.setBirthdayDate(new SimpleDateFormat("yyyy-MM-dd").parse(user.getBirthdayDate()));
            userRepository.writeImage(json, uploadedInputStream, fileDetail, user);
            user = userRepository.createUser(user);
            if(user == null)
                return Response.status(Response.Status.CONFLICT).build();
            return crud.options()
                    .entity(user)
                    .build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @POST
    @Path("check_user")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public  Response check(User user) {
        try {
//            String token = null;
//            Date date = null;
            boolean checkPass = userRepository.checkUser(user);
            if(checkPass){
//                token = JwTokenHelper.getInstance().generatePrivateKey(admin);
//                date = JwTokenHelper.getInstance().getExpirationDate();
                return crud.options()
                        .entity(checkPass)
//                        .header("privateKey", "Bearer " + token)
//                        .header("expirationDate", date)
//                        .header("refreshLink", "/refresh_token")
                        .build();
            }else {
                return Response.status(Response.Status.UNAUTHORIZED).entity(checkPass).build();
            }

        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public  Response getUser(@FormParam("email") String email) {
        try {
            User user = userRepository.findUser(email);
            //System.out.println("AAA    " + user.getBirthdayDate());
            //System.out.println("BBB    " + user.getRegistrationDate());
            user.setBirthdayDate(new SimpleDateFormat("yyyy-MM-dd").parse(user.getBirthdayDate().toString()));
            return crud.options()
                    .entity(user)
                    .build();

        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("refresh_token")
    @JsonTokenNeeded
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public  Response refresh(User user) {
        try {
//            String token = null;
//            Date date = null;
//            token = JwTokenHelper.getInstance().generatePrivateKey(user);
//            date = JwTokenHelper.getInstance().getExpirationDate();
            return crud.options()
                    .entity(true)
//                    .header("privateKey", "Bearer " + token)
//                    .header("expirationDate", date)
//                    .header("refreshLink", "/refresh_token")
                    .build();


        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
