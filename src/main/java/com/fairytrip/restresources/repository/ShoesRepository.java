package com.fairytrip.restresources.repository;


import com.fairytrip.data.entities.Shoes;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.io.InputStream;
import java.util.List;

public interface ShoesRepository {
    List<Shoes> findAllShoes();

    Shoes createShoes(Shoes shoes);

    Shoes updateShoes(Shoes shoes, Long shoesId);

    Shoes deleteShoes(Long shoesId);

    List<Shoes> searchShoes(String s);

    void writeImage(FormDataBodyPart json, InputStream uploadedInputStream, FormDataContentDisposition fileDetail, Shoes shoes);

    void deleteImage(Shoes shoes);
}
