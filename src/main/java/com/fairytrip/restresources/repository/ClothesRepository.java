package com.fairytrip.restresources.repository;

import com.fairytrip.data.entities.Clothes;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.io.InputStream;
import java.util.List;

public interface ClothesRepository {
    List<Clothes> findAllClothes();

    Clothes createClothes(Clothes clothes);

    Clothes updateClothes(Clothes clothes, Long clothesId);

    Clothes deleteClothes(Long clothesId);

    List<Clothes> searchClothes(String s);

    void writeImage(FormDataBodyPart json, InputStream uploadedInputStream, FormDataContentDisposition fileDetail, Clothes clothes);

    void deleteImage(Clothes clothes);
}
