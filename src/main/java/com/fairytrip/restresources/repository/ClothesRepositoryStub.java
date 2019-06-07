package com.fairytrip.restresources.repository;

import com.fairytrip.data.entities.Clothes;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.io.InputStream;
import java.util.List;

public class ClothesRepositoryStub implements ClothesRepository {
    CRUD crud = new CRUD();

    @Override
    public List<Clothes> findAllClothes() {
        return  crud.read("select c from Clothes c");

    }

    @Override
    public Clothes createClothes(Clothes clothes) {
        return (Clothes) crud.create(clothes);
    }

    @Override
    public Clothes updateClothes(Clothes clothes,  Long clothesId) {
        return (Clothes) crud.update(clothes, clothesId);
    }

    @Override
    public Clothes deleteClothes(Long clothesId) {
        Clothes clothes = new Clothes();
        return (Clothes) crud.delete(clothesId, clothes);
    }

    @Override
    public List<Clothes> searchClothes(String s) {
        return  crud.read("select s from Clothes s where name like '%" + s + "%'");

    }

    @Override
    public void writeImage(FormDataBodyPart json, InputStream uploadedInputStream, FormDataContentDisposition fileDetail, Clothes clothes){
        String filePath = "/images/"
                + fileDetail.getFileName();
        crud.writeImage(json, uploadedInputStream, filePath);
        clothes.setImagePath(filePath);
    }

    @Override
    public void deleteImage(Clothes clothes) {
        crud.deleteImage(clothes.getImagePath());
    }

    public Clothes setClothesProperties(Clothes clothes, Clothes updatedClothes){
        updatedClothes.setName(clothes.getName());
        updatedClothes.setImagePath(clothes.getImagePath());
        updatedClothes.setCharacteristic(clothes.getCharacteristic());
        updatedClothes.setPrice(clothes.getPrice());
        updatedClothes.setSex(clothes.getSex());
        updatedClothes.setBrand(clothes.getBrand());
        updatedClothes.setMaterial(clothes.getMaterial());
        updatedClothes.setSizes(clothes.getSizes());
        return updatedClothes;
    }
}
