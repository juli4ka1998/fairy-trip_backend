package com.fairytrip.restresources.repository;

import com.fairytrip.data.entities.Backpack;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.io.InputStream;
import java.util.List;

public interface BackpackRepository {
    List<Backpack> findAllBackpacks();

    Backpack createBackpack(Backpack backpack);

    Backpack updateBackpack(Backpack backpack, Long backpackId);

    Backpack deleteBackpack(Long backpackId);

    List<Backpack> searchBackpack(String s);

    void writeImage(FormDataBodyPart json, InputStream uploadedInputStream, FormDataContentDisposition fileDetail, Backpack backpack);

    void deleteImage(Backpack backpack);
}
