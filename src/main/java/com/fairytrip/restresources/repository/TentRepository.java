package com.fairytrip.restresources.repository;

import com.fairytrip.data.entities.Tent;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.io.InputStream;
import java.util.List;

public interface TentRepository {
    List<Tent> findAllTents();

    Tent createTent(Tent tent);

    Tent updateTent(Tent tent, Long tentId);

    Tent deleteTent(Long tentId);

    List<Tent> searchTent(String s);

    void writeImage(FormDataBodyPart json, InputStream uploadedInputStream, FormDataContentDisposition fileDetail, Tent tent);

    void deleteImage(Tent tent);
}
