package com.fairytrip.restresources;

import com.fairytrip.data.entities.Food;
import com.fairytrip.restresources.repository.CRUD;
import com.fairytrip.restresources.repository.FoodRepository;
import com.fairytrip.restresources.repository.FoodRepositoryStub;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("food")
public class FoodResource {
    FoodRepository foodRepository = new FoodRepositoryStub();
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
    @Path("new_food")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createShoes(){
        try {
            return crud.options().build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    @OPTIONS
    @Path("{foodId}")
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
    public Response getAllFood(){
        try {
            List<Food> food = foodRepository.findAllFood();
            if (food == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }else {
                return crud.options()
                        .entity(food)
                        .build();
            }
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("new_food")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response create(Food food) {
        try {
            food = foodRepository.createFood(food);
            return crud.options()
                    .entity(food)
                    .build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("{foodId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response update(Food food, @PathParam("foodId") Long foodId) {
        try {
            food = foodRepository.updateFood(food, foodId);
            return crud.options()
                    .entity(food)
                    .build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("{foodId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response delete(@PathParam("foodId") Long foodId) {
        try {
            if(foodRepository.deleteFood(foodId)) {
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
