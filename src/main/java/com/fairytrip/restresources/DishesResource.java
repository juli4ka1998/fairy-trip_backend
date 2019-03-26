package com.fairytrip.restresources;

import com.fairytrip.data.entities.Dishes;
import com.fairytrip.restresources.repository.CRUD;
import com.fairytrip.restresources.repository.DishesRepository;
import com.fairytrip.restresources.repository.DishesRepositoryStub;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    @Path("new_dishes")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response create(Dishes dishes) {
        try {
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
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response update(Dishes dishes, @PathParam("dishesId") Long dishesId) {
        try {
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
            if(dishesRepository.deleteDishes(dishesId)) {
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
