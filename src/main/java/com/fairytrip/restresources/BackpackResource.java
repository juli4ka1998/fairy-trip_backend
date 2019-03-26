package com.fairytrip.restresources;

import com.fairytrip.data.entities.Backpack;
import com.fairytrip.restresources.repository.BackpackRepository;
import com.fairytrip.restresources.repository.BackpackRepositoryStub;
import com.fairytrip.restresources.repository.CRUD;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("backpack")
public class BackpackResource {
    BackpackRepository backpackRepository = new BackpackRepositoryStub();
    CRUD crud = new CRUD();
    @OPTIONS
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getBackpack(){
        try {
            return crud.options().build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    @OPTIONS
    @Path("new_backpack")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createBackpack(){
        try {
            return crud.options().build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @OPTIONS
    @Path("{backpackId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateBackpack(){
        try {
            return crud.options().build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllBackpacks(){
        try {
            List<Backpack> backpacks = backpackRepository.findAllBackpacks();
            if (backpacks == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }else {
                return crud.options()
                        .entity(backpacks)
                        .build();
            }
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("new_backpack")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public  Response create(Backpack backpack) {
        try {
            backpack = backpackRepository.createBackpack(backpack);
            return crud.options()
                    .entity(backpack)
                    .build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("{backpackId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response update(Backpack backpack, @PathParam("backpackId") Long backpackId) {
        try {
            backpack = backpackRepository.updateBackpack(backpack, backpackId);
            return crud.options()
                    .entity(backpack)
                    .build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("{backpackId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response delete(@PathParam("backpackId") Long backpackId) {
        try {
            if(backpackRepository.deleteBackpack(backpackId)) {
                return crud.options().build();
            }
            else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
