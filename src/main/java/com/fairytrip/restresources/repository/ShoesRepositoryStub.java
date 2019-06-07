package com.fairytrip.restresources.repository;


import com.fairytrip.data.entities.Shoes;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.io.InputStream;
import java.util.List;

public class ShoesRepositoryStub implements ShoesRepository {
    CRUD crud = new CRUD();

    @Override
    public List<Shoes> findAllShoes() {
        return  crud.read("select s from Shoes s");

    }

    @Override
    public Shoes createShoes(Shoes shoes) {
        return (Shoes) crud.create(shoes);
    }

    @Override
    public Shoes updateShoes(Shoes shoes,  Long shoesId) {
        return (Shoes) crud.update(shoes, shoesId);
    }

    @Override
    public Shoes deleteShoes(Long shoesId) {
        Shoes shoes = new Shoes();
        return (Shoes) crud.delete(shoesId, shoes);
    }

    @Override
    public List<Shoes> searchShoes(String s) {
        return  crud.read("select s from Shoes s where name like '%" + s + "%'");

    }

    @Override
    public void writeImage(FormDataBodyPart json, InputStream uploadedInputStream, FormDataContentDisposition fileDetail, Shoes shoes){
        String filePath = "/images/"
                + fileDetail.getFileName();
        crud.writeImage(json, uploadedInputStream, filePath);
        shoes.setImagePath(filePath);
    }

    @Override
    public void deleteImage(Shoes shoes) {
        crud.deleteImage(shoes.getImagePath());
    }

    public Shoes setShoesProperties(Shoes shoes, Shoes updatedShoes){
        updatedShoes.setName(shoes.getName());
        updatedShoes.setCharacteristic(shoes.getCharacteristic());
        if(shoes.getImagePath() != null)
            updatedShoes.setImagePath(shoes.getImagePath());
        updatedShoes.setPrice(shoes.getPrice());
        updatedShoes.setBrand(shoes.getBrand());
        updatedShoes.setSizes(shoes.getSizes());
        updatedShoes.setMaterial(shoes.getMaterial());
        updatedShoes.setSex(shoes.getSex());
        return updatedShoes;
    }



}
