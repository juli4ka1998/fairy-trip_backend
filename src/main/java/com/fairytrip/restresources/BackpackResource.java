package com.fairytrip.restresources;

import com.fairytrip.data.entities.Backpack;
import com.fairytrip.restresources.repository.BackpackRepository;
import com.fairytrip.restresources.repository.BackpackRepositoryStub;
import com.fairytrip.restresources.repository.CRUD;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
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
    @Path("search")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response searchShoes(){
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
    @Path("search")
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    @Produces({MediaType.APPLICATION_JSON})
    public Response searchBackpack(@FormDataParam("search") String s) {

        try {
            List<Backpack> backpacks = backpackRepository.searchBackpack(s);
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
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    @Produces({MediaType.APPLICATION_JSON})
    public Response create(@FormDataParam("file") InputStream uploadedInputStream,
                           @FormDataParam("file") FormDataContentDisposition fileDetail,
                           @FormDataParam("json") FormDataBodyPart json) {

        try {
            json.setMediaType(MediaType.APPLICATION_JSON_TYPE);
            Backpack backpack = json.getValueAs(Backpack.class);
            backpackRepository.writeImage(json, uploadedInputStream, fileDetail, backpack);
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
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    @Produces({MediaType.APPLICATION_JSON})
    public Response update(@FormDataParam("file") InputStream uploadedInputStream,
                           @FormDataParam("file") FormDataContentDisposition fileDetail,
                           @FormDataParam("json") FormDataBodyPart json,
                           @PathParam("backpackId") Long backpackId) {
        try {
            json.setMediaType(MediaType.APPLICATION_JSON_TYPE);
            Backpack backpack = json.getValueAs(Backpack.class);
            if(fileDetail.getFileName() != null){
                backpackRepository.writeImage(json, uploadedInputStream, fileDetail, backpack);
            }
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
            Backpack backpack = backpackRepository.deleteBackpack(backpackId);
            backpackRepository.deleteImage(backpack);
            return crud.options().build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
