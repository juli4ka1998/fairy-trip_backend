package com.fairytrip.restresources.repository;

import com.fairytrip.data.entities.Equipment;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.io.InputStream;
import java.util.List;

public interface EquipmentRepository {
    List<Equipment> findAllEquipment();

    Equipment createEquipment(Equipment equipment);

    Equipment updateEquipment(Equipment equipment, Long equipmentId);

    Equipment deleteEquipment(Long equipmentId);

    List<Equipment> searchEquipment(String s);

    void writeImage(FormDataBodyPart json, InputStream uploadedInputStream, FormDataContentDisposition fileDetail, Equipment equipment);

    void deleteImage(Equipment equipment);
}
