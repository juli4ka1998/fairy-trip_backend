package com.fairytrip.restresources;

import com.fairytrip.data.entities.Shoes;
import com.fairytrip.restresources.jwtconfiguration.JsonTokenNeeded;
import com.fairytrip.restresources.repository.CRUD;
import com.fairytrip.restresources.repository.ShoesRepository;
import com.fairytrip.restresources.repository.ShoesRepositoryStub;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.util.List;

@Path("shoes")
@JsonTokenNeeded
public class ShoesResource {

    ShoesRepository shoesRepository = new ShoesRepositoryStub();
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
    @Path("new_shoes")
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
    @Path("{shoesId}")
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
    public Response getAllShoes(){
        try {
            List<Shoes> shoes = shoesRepository.findAllShoes();
            if (shoes == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }else {
                return crud.options()
                        .entity(shoes)
                        .build();
            }
        }catch (Exception e) {

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
//    @Context ServletContext servletContext;

    @POST
    @Path("search")
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    @Produces({MediaType.APPLICATION_JSON})
    public Response searchShoes(@FormDataParam("search") String s) {

        try {
            List<Shoes> shoes = shoesRepository.searchShoes(s);
            if (shoes == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }else {
                return crud.options()
                        .entity(shoes)
                        .build();
            }
        }catch (Exception e) {

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    @POST
    @Path("new_shoes")
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    @Produces({MediaType.APPLICATION_JSON})
    public Response create(@FormDataParam("file") InputStream uploadedInputStream,
                           @FormDataParam("file") FormDataContentDisposition fileDetail,
                           @FormDataParam("json") FormDataBodyPart json) {

        try {
            json.setMediaType(MediaType.APPLICATION_JSON_TYPE);
            Shoes shoes = json.getValueAs(Shoes.class);
            shoesRepository.writeImage(json, uploadedInputStream, fileDetail, shoes);
            shoes = shoesRepository.createShoes(shoes);
            return crud.options()
                    .entity(shoes)
                    .build();

        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }



    @PUT
    @Path("{shoesId}")
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    @Produces({MediaType.APPLICATION_JSON})
    public Response update(@FormDataParam("file") InputStream uploadedInputStream,
                           @FormDataParam("file") FormDataContentDisposition fileDetail,
                           @FormDataParam("json") FormDataBodyPart json,
                           @PathParam("shoesId") Long shoesId) {
        try {
            json.setMediaType(MediaType.APPLICATION_JSON_TYPE);
            Shoes shoes = json.getValueAs(Shoes.class);
            if(fileDetail.getFileName() != null){
                shoesRepository.writeImage(json, uploadedInputStream, fileDetail, shoes);
            }
            shoes = shoesRepository.updateShoes(shoes, shoesId);
            return crud.options()
                    .entity(shoes)
                    .build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("{shoesId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response delete(@PathParam("shoesId") Long shoesId) {
        try {
            Shoes shoes = shoesRepository.deleteShoes(shoesId);
            shoesRepository.deleteImage(shoes);
            return crud.options().build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


}
