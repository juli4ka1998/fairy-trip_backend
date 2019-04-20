package com.fairytrip.restresources;

import com.fairytrip.data.entities.SleepingBag;
import com.fairytrip.restresources.repository.CRUD;
import com.fairytrip.restresources.repository.SleepingBagRepository;
import com.fairytrip.restresources.repository.SleepingBagRepositoryStub;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("sleeping_bag")
public class SleepingBagResource {
    SleepingBagRepository sleepingBagRepository = new SleepingBagRepositoryStub();
    CRUD crud = new CRUD();
    @OPTIONS
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getShoes(){
        try {
            return crud.options().build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    @OPTIONS
    @Path("new_sleeping_bag")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createShoes(){
        try {
            return crud.options().build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    @OPTIONS
    @Path("{sleepingBagId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateShoes(){
        try {
            return crud.options().build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllSleepingBags(){
        try {
            List<SleepingBag> sleepingBags = sleepingBagRepository.findAllSleepingBags();
            if (sleepingBags == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }else {
                return crud.options()
                        .entity(sleepingBags)
                        .build();
            }
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("new_sleeping_bag")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public  Response create(SleepingBag sleepingBag) {
        try {
            sleepingBag = sleepingBagRepository.createSleepingBag(sleepingBag);
            return crud.options()
                    .entity(sleepingBag)
                    .build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("{sleepingBagId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response update(SleepingBag sleepingBag, @PathParam("sleepingBagId") Long sleepingBagId) {
        try {
            sleepingBag = sleepingBagRepository.updateSleepingBag(sleepingBag, sleepingBagId);
            return crud.options()
                    .entity(sleepingBag)
                    .build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("{sleepingBagId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response delete(@PathParam("sleepingBagId") Long sleepingBagId) {
        try {
            //if(sleepingBagRepository.deleteSleepingBag(sleepingBagId)) {
                return crud.options().build();
//            }
//            else {
//                return Response.status(Response.Status.BAD_REQUEST).build();
//            }
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
