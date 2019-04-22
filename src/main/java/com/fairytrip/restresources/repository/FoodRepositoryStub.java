package com.fairytrip.restresources.repository;

import com.fairytrip.data.entities.Food;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.io.InputStream;
import java.util.List;

public class FoodRepositoryStub implements FoodRepository {
    CRUD crud = new CRUD();

    @Override
    public List<Food> findAllFood() {
        return  crud.read("select f from Food f");

    }

    @Override
    public Food createFood(Food food) {
        return (Food) crud.create(food);
    }

    @Override
    public Food updateFood(Food food, Long foodId) {
        return (Food) crud.update(food, foodId);
    }

    @Override
    public Food deleteFood(Long foodId) {
        Food food = new Food();
        return (Food) crud.delete(foodId, food);
    }

    @Override
    public List<Food> searchFood(String s) {
        return  crud.read("select s from Food s where name like '%" + s + "%'");

    }

    @Override
    public void writeImage(FormDataBodyPart json, InputStream uploadedInputStream, FormDataContentDisposition fileDetail, Food food){
        String filePath = "/images/"
                + fileDetail.getFileName();
        crud.writeImage(json, uploadedInputStream, filePath);
        food.setImagePath(filePath);
    }

    @Override
    public void deleteImage(Food food) {
        crud.deleteImage(food.getImagePath());
    }

    public Food setFoodProperties(Food food, Food updatedFood){
        updatedFood.setName(food.getName());
        updatedFood.setCharacteristic(food.getCharacteristic());
        updatedFood.setImagePath(food.getImagePath());
        updatedFood.setWeight(food.getWeight());
        updatedFood.setPrice(food.getPrice());
        updatedFood.setBrand(food.getBrand());
        return updatedFood;
    }
}
