package com.fairytrip.restresources.repository;

import com.fairytrip.data.entities.Food;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.io.InputStream;
import java.util.List;

public interface FoodRepository {
    List<Food> findAllFood();

    Food createFood(Food food);

    Food updateFood(Food food, Long foodId);

    Food deleteFood(Long foodId);

    List<Food> searchFood(String s);

    void writeImage(FormDataBodyPart json, InputStream uploadedInputStream, FormDataContentDisposition fileDetail, Food food);

    void deleteImage(Food food);
}
