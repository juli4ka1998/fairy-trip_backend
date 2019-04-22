package com.fairytrip.restresources.repository;

import com.fairytrip.data.entities.Backpack;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class BackpackRepositoryStub implements BackpackRepository {
    CRUD crud = new CRUD();

    @Override
    public List<Backpack> findAllBackpacks() {
        return  crud.read("select b from Backpack b");

    }

    @Override
    public Backpack createBackpack(Backpack backpack) {
        return (Backpack) crud.create(backpack);
    }

    @Override
    public Backpack updateBackpack(Backpack backpack, Long backpackId) {
        return (Backpack) crud.update(backpack, backpackId);
    }

    @Override
    public Backpack deleteBackpack(Long backpackId) {
        Backpack backpack = new Backpack();
        return (Backpack) crud.delete(backpackId, backpack);
    }

    @Override
    public List<Backpack> searchBackpack(String s) {
        return  crud.read("select s from Backpack s where name like '%" + s + "%'");

    }

    @Override
    public void writeImage(FormDataBodyPart json, InputStream uploadedInputStream, FormDataContentDisposition fileDetail, Backpack backpack){
        String filePath = "/images/"
                + fileDetail.getFileName();
        crud.writeImage(json, uploadedInputStream, filePath);
        backpack.setImagePath(filePath);
    }

    @Override
    public void deleteImage(Backpack backpack) {
        crud.deleteImage(backpack.getImagePath());
    }

    public Backpack setBackpackProperties(Backpack backpack, Backpack updatedBackpack){
        updatedBackpack.setBrand(backpack.getBrand());
        updatedBackpack.setSizes(backpack.getSizes());
        updatedBackpack.setName(backpack.getName());
        updatedBackpack.setCharacteristic(backpack.getCharacteristic());
        updatedBackpack.setImagePath(backpack.getImagePath());
        updatedBackpack.setPrice(backpack.getPrice());
        updatedBackpack.setMaterial(backpack.getMaterial());
        updatedBackpack.setSex(backpack.getSex());
        return updatedBackpack;
    }


}
