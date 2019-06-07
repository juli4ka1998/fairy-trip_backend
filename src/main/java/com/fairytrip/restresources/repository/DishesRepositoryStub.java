package com.fairytrip.restresources.repository;


import com.fairytrip.data.entities.Dishes;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.io.InputStream;
import java.util.List;

public class DishesRepositoryStub implements DishesRepository {
    CRUD crud = new CRUD();

    @Override
    public List<Dishes> findAllDishes() {
        return  crud.read("select d from Dishes d");

    }

    @Override
    public Dishes createDishes(Dishes dishes) {
        return (Dishes) crud.create(dishes);
    }

    @Override
    public Dishes updateDishes(Dishes dishes, Long dishesId) {
        return (Dishes) crud.update(dishes, dishesId);
    }

    @Override
    public Dishes deleteDishes(Long dishesId) {
        Dishes dishes = new Dishes();
        return (Dishes) crud.delete(dishesId, dishes);
    }

    @Override
    public List<Dishes> searchDishes(String s) {
        return  crud.read("select s from Dishes s where name like '%" + s + "%'");

    }

    @Override
    public void writeImage(FormDataBodyPart json, InputStream uploadedInputStream, FormDataContentDisposition fileDetail, Dishes dishes){
        String filePath = "/images/"
                + fileDetail.getFileName();
        crud.writeImage(json, uploadedInputStream, filePath);
        dishes.setImagePath(filePath);
    }

    @Override
    public void deleteImage(Dishes dishes) {
        crud.deleteImage(dishes.getImagePath());
    }

    public Dishes setDishesProperties(Dishes dishes, Dishes updatedDishes){
        updatedDishes.setName(dishes.getName());
        updatedDishes.setCharacteristic(dishes.getCharacteristic());
        updatedDishes.setImagePath(dishes.getImagePath());
        updatedDishes.setSize(dishes.getSize());
        updatedDishes.setPrice(dishes.getPrice());
        updatedDishes.setBrand(dishes.getBrand());
        updatedDishes.setMaterial(dishes.getMaterial());
        return updatedDishes;
    }
}
