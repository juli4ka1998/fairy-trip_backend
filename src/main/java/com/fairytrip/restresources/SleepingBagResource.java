package com.fairytrip.restresources;

import com.fairytrip.data.entities.SleepingBag;
import com.fairytrip.restresources.jwtconfiguration.JsonTokenNeeded;
import com.fairytrip.restresources.repository.CRUD;
import com.fairytrip.restresources.repository.SleepingBagRepository;
import com.fairytrip.restresources.repository.SleepingBagRepositoryStub;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.List;

@Path("sleeping_bag")
@JsonTokenNeeded
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
    @Path("search")
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    @Produces({MediaType.APPLICATION_JSON})
    public Response searchSleepingBag(@FormDataParam("search") String s) {

        try {
            List<SleepingBag> sleepingBags = sleepingBagRepository.searchSleepingBag(s);
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
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    @Produces({MediaType.APPLICATION_JSON})
    public Response create(@FormDataParam("file") InputStream uploadedInputStream,
                           @FormDataParam("file") FormDataContentDisposition fileDetail,
                           @FormDataParam("json") FormDataBodyPart json) {

        try {
            json.setMediaType(MediaType.APPLICATION_JSON_TYPE);
            SleepingBag sleepingBag = json.getValueAs(SleepingBag.class);
            sleepingBagRepository.writeImage(json, uploadedInputStream, fileDetail, sleepingBag);
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
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    @Produces({MediaType.APPLICATION_JSON})
    public Response update(@FormDataParam("file") InputStream uploadedInputStream,
                           @FormDataParam("file") FormDataContentDisposition fileDetail,
                           @FormDataParam("json") FormDataBodyPart json,
                           @PathParam("sleepingBagId") Long sleepingBagId) {
        try {
            json.setMediaType(MediaType.APPLICATION_JSON_TYPE);
            SleepingBag sleepingBag = json.getValueAs(SleepingBag.class);
            if(fileDetail.getFileName() != null){
                sleepingBagRepository.writeImage(json, uploadedInputStream, fileDetail, sleepingBag);
            }
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
            SleepingBag sleepingBag = sleepingBagRepository.deleteSleepingBag(sleepingBagId);
            sleepingBagRepository.deleteImage(sleepingBag);
            return crud.options().build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
