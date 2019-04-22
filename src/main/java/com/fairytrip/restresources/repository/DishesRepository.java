package com.fairytrip.restresources.repository;

import com.fairytrip.data.entities.Dishes;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.io.InputStream;
import java.util.List;

public interface DishesRepository {
    List<Dishes> findAllDishes();

    Dishes createDishes(Dishes dishes);

    Dishes updateDishes(Dishes dishes, Long dishesId);

    Dishes deleteDishes(Long dishesId);

    List<Dishes> searchDishes(String s);

    void writeImage(FormDataBodyPart json, InputStream uploadedInputStream, FormDataContentDisposition fileDetail, Dishes dishes);

    void deleteImage(Dishes dishes);
}
