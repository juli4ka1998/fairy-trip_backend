package com.fairytrip.restresources;

import com.fairytrip.data.entities.Equipment;
import com.fairytrip.restresources.jwtconfiguration.JsonTokenNeeded;
import com.fairytrip.restresources.repository.CRUD;
import com.fairytrip.restresources.repository.EquipmentRepository;
import com.fairytrip.restresources.repository.EquipmentRepositoryStub;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.List;

@Path("equipment")
@JsonTokenNeeded
public class EquipmentResource {

    EquipmentRepository equipmentRepository = new EquipmentRepositoryStub();
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
    @Path("new_equipment")
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
    @Path("{equipmentId}")
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
    public Response getAllEquipment(){
        try {
            List<Equipment> equipment = equipmentRepository.findAllEquipment();
            if (equipment == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }else {
                return crud.options()
                        .entity(equipment)
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
    public Response searchEquipment(@FormDataParam("search") String s) {

        try {
            List<Equipment> equipment = equipmentRepository.searchEquipment(s);
            if (equipment == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }else {
                return crud.options()
                        .entity(equipment)
                        .build();
            }
        }catch (Exception e) {

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("new_equipment")
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    @Produces({MediaType.APPLICATION_JSON})
    public Response create(@FormDataParam("file") InputStream uploadedInputStream,
                           @FormDataParam("file") FormDataContentDisposition fileDetail,
                           @FormDataParam("json") FormDataBodyPart json) {

        try {
            json.setMediaType(MediaType.APPLICATION_JSON_TYPE);
            Equipment equipment = json.getValueAs(Equipment.class);
            equipmentRepository.writeImage(json, uploadedInputStream, fileDetail, equipment);
            equipment = equipmentRepository.createEquipment(equipment);
            return crud.options()
                    .entity(equipment)
                    .build();

        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PUT
    @Path("{equipmentId}")
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    @Produces({MediaType.APPLICATION_JSON})
    public Response update(@FormDataParam("file") InputStream uploadedInputStream,
                           @FormDataParam("file") FormDataContentDisposition fileDetail,
                           @FormDataParam("json") FormDataBodyPart json,
                           @PathParam("equipmentId") Long equipmentId) {
        try {
            json.setMediaType(MediaType.APPLICATION_JSON_TYPE);
            Equipment equipment = json.getValueAs(Equipment.class);
            if(fileDetail.getFileName() != null){
                equipmentRepository.writeImage(json, uploadedInputStream, fileDetail, equipment);
            }
            equipment = equipmentRepository.updateEquipment(equipment, equipmentId);
            return crud.options()
                    .entity(equipment)
                    .build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("{equipmentId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response delete(@PathParam("equipmentId") Long equipmentId) {
        try {
            Equipment equipment = equipmentRepository.deleteEquipment(equipmentId);
            equipmentRepository.deleteImage(equipment);
            return crud.options().build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
