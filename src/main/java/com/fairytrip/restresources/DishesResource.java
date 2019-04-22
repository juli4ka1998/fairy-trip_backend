package com.fairytrip.restresources;

import com.fairytrip.data.entities.Dishes;
import com.fairytrip.restresources.repository.CRUD;
import com.fairytrip.restresources.repository.DishesRepository;
import com.fairytrip.restresources.repository.DishesRepositoryStub;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.util.List;

@Path("dishes")
public class DishesResource {

    DishesRepository dishesRepository = new DishesRepositoryStub();
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
    @Path("new_dishes")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createShoes(){
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
    @Path("{dishesId}")
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
    public Response getAllDishes(){
        try {
            List<Dishes> dishes = dishesRepository.findAllDishes();
            if (dishes == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }else {
                return crud.options()
                        .entity(dishes)
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
    public Response searchDishes(@FormDataParam("search") String s) {

        try {
            List<Dishes> dishes = dishesRepository.searchDishes(s);
            if (dishes == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }else {
                return crud.options()
                        .entity(dishes)
                        .build();
            }
        }catch (Exception e) {

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    @POST
    @Path("new_dishes")
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    @Produces({MediaType.APPLICATION_JSON})
    public Response create(@FormDataParam("file") InputStream uploadedInputStream,
                           @FormDataParam("file") FormDataContentDisposition fileDetail,
                           @FormDataParam("json") FormDataBodyPart json) {

        try {
            json.setMediaType(MediaType.APPLICATION_JSON_TYPE);
            Dishes dishes = json.getValueAs(Dishes.class);
            dishesRepository.writeImage(json, uploadedInputStream, fileDetail, dishes);
            dishes = dishesRepository.createDishes(dishes);
            return crud.options()
                    .entity(dishes)
                    .build();

        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PUT
    @Path("{dishesId}")
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    @Produces({MediaType.APPLICATION_JSON})
    public Response update(@FormDataParam("file") InputStream uploadedInputStream,
                           @FormDataParam("file") FormDataContentDisposition fileDetail,
                           @FormDataParam("json") FormDataBodyPart json,
                           @PathParam("dishesId") Long dishesId) {
        try {
            json.setMediaType(MediaType.APPLICATION_JSON_TYPE);
            Dishes dishes = json.getValueAs(Dishes.class);
            if(fileDetail.getFileName() != null){
                dishesRepository.writeImage(json, uploadedInputStream, fileDetail, dishes);
            }
            dishes = dishesRepository.updateDishes(dishes, dishesId);
            return crud.options()
                    .entity(dishes)
                    .build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("{dishesId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response delete(@PathParam("dishesId") Long dishesId) {
        try {
            Dishes dishes = dishesRepository.deleteDishes(dishesId);
            dishesRepository.deleteImage(dishes);
            return crud.options().build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
