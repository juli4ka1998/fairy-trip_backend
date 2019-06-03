package com.fairytrip.restresources;

import com.fairytrip.data.entities.Admin;
import com.fairytrip.restresources.jwtconfiguration.JsonTokenNeeded;
import com.fairytrip.restresources.jwtconfiguration.JwTokenHelper;
import com.fairytrip.restresources.repository.AdminRepository;
import com.fairytrip.restresources.repository.AdminRepositoryStub;
import com.fairytrip.restresources.repository.CRUD;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

@Path("admin")
//@JsonTokenNeeded
public class AdminResource {
    AdminRepository adminRepository = new AdminRepositoryStub();
    CRUD crud = new CRUD();

    @OPTIONS
    @Path("create_admin")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createAdmin(){
        try {
            return crud.options().build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @OPTIONS
    @Path("check_admin")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response checkAdmin(){
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
    @Path("create_admin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public  Response create(Admin admin) {
        try {
            admin = adminRepository.createAdmin(admin);
            return crud.options()
                    .entity(admin)
                    .build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @POST
    @Path("check_admin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public  Response check(Admin admin) {
        try {
            String token = null;
            Date date = null;
            boolean checkPass = adminRepository.checkAdmin(admin);
            if(checkPass){
                token = JwTokenHelper.getInstance().generatePrivateKey(admin);
                date = JwTokenHelper.getInstance().getExpirationDate();
                return crud.options()
                        .entity(checkPass)
                        .header("privateKey", "Bearer " + token)
                        .header("expirationDate", date)
                        .header("refreshLink", "/refresh_token")
                        .build();
            }else {
                return Response.status(Response.Status.UNAUTHORIZED).entity(checkPass).build();
            }

        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("refresh_token")
    @JsonTokenNeeded
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public  Response refresh(Admin admin) {
        try {
            String token = null;
            Date date = null;
                token = JwTokenHelper.getInstance().generatePrivateKey(admin);
                date = JwTokenHelper.getInstance().getExpirationDate();
                return crud.options()
                        .entity(true)
                        .header("privateKey", "Bearer " + token)
                        .header("expirationDate", date)
                        .header("refreshLink", "/refresh_token")
                        .build();


        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
