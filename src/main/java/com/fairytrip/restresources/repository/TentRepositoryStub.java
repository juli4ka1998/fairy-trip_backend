package com.fairytrip.restresources.repository;

import com.fairytrip.data.entities.Tent;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.io.InputStream;
import java.util.List;

public class TentRepositoryStub implements TentRepository {
    CRUD crud = new CRUD();

    @Override
    public List<Tent> findAllTents() {
        return  crud.read("select t from Tent t");

    }

    @Override
    public Tent createTent(Tent tent) {
        return (Tent) crud.create(tent);
    }

    @Override
    public Tent updateTent(Tent tent, Long tentId) {
        return (Tent) crud.update(tent, tentId);
    }

    @Override
    public Tent deleteTent(Long tentId) {
        Tent tent = new Tent();
        return (Tent) crud.delete(tentId, tent);
    }
    @Override
    public List<Tent> searchTent(String s) {
        return  crud.read("select s from Tent s where name like '%" + s + "%'");

    }

    @Override
    public void writeImage(FormDataBodyPart json, InputStream uploadedInputStream, FormDataContentDisposition fileDetail, Tent tent){
        String filePath = "/images/"
                + fileDetail.getFileName();
        crud.writeImage(json, uploadedInputStream, filePath);
        tent.setImagePath(filePath);
    }

    @Override
    public void deleteImage(Tent tent) {
        crud.deleteImage(tent.getImagePath());
    }

    public Tent setTentProperties(Tent tent, Tent updatedTent){
        updatedTent.setName(tent.getName());
        updatedTent.setCharacteristic(tent.getCharacteristic());
        updatedTent.setImagePath(tent.getImagePath());
        updatedTent.setMaterial(tent.getMaterial());
        updatedTent.setPrice(tent.getPrice());
        updatedTent.setBrand(tent.getBrand());
        updatedTent.setSize(tent.getSize());
        return updatedTent;
    }
}
