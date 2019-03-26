package com.fairytrip.restresources;

import com.fairytrip.data.entities.Clothes;
import com.fairytrip.restresources.repository.CRUD;
import com.fairytrip.restresources.repository.ClothesRepository;
import com.fairytrip.restresources.repository.ClothesRepositoryStub;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("clothes")
public class ClothesResource {
    ClothesRepository clothesRepository = new ClothesRepositoryStub();
    CRUD crud = new CRUD();
    @OPTIONS
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getClothes(){
        try {
            return crud.options().build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    @OPTIONS
    @Path("new_clothes")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createClothes(){
        try {
            return crud.options().build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    @OPTIONS
    @Path("{clothesId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateClothes(){
        try {
            return crud.options().build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllClothes(){
        try {
            List<Clothes> clothes = clothesRepository.findAllClothes();
            if (clothes == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }else {
                return crud.options()
                        .entity(clothes)
                        .build();
            }
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("new_clothes")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public  Response create(Clothes clothes) {
        try {
            clothes = clothesRepository.createClothes(clothes);
            return crud.options()
                    .entity(clothes)
                    .build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("{clothesId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response update(Clothes clothes, @PathParam("clothesId") Long clothesId) {
        try {
            clothes = clothesRepository.updateClothes(clothes, clothesId);
            return crud.options()
                    .entity(clothes)
                    .build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("{clothesId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response delete(@PathParam("clothesId") Long clothesId) {
        try {
            if(clothesRepository.deleteClothes(clothesId)) {
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
