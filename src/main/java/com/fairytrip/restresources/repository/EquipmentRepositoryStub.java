package com.fairytrip.restresources.repository;

import com.fairytrip.data.entities.Equipment;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.io.InputStream;
import java.util.List;

public class EquipmentRepositoryStub implements EquipmentRepository {
    CRUD crud = new CRUD();

    @Override
    public List<Equipment> findAllEquipment() {
        return  crud.read("select e from Equipment e");

    }

    @Override
    public Equipment createEquipment(Equipment equipment) {
        return (Equipment) crud.create(equipment);
    }

    @Override
    public Equipment updateEquipment(Equipment equipment, Long equipmentId) {
        return (Equipment) crud.update(equipment, equipmentId);
    }

    @Override
    public Equipment deleteEquipment(Long equipmentId) {
        Equipment equipment = new Equipment();
        return (Equipment) crud.delete(equipmentId, equipment);
    }

    @Override
    public List<Equipment> searchEquipment(String s) {
        return  crud.read("select s from Equipment s where name like '%" + s + "%'");

    }

    @Override
    public void writeImage(FormDataBodyPart json, InputStream uploadedInputStream, FormDataContentDisposition fileDetail, Equipment equipment){
        String filePath = "/images/"
                + fileDetail.getFileName();
        crud.writeImage(json, uploadedInputStream, filePath);
        equipment.setImagePath(filePath);
    }

    @Override
    public void deleteImage(Equipment equipment) {
        crud.deleteImage(equipment.getImagePath());
    }

    public Equipment setEquipmentProperties(Equipment equipment, Equipment updatedEquipment){
        updatedEquipment.setName(equipment.getName());
        updatedEquipment.setCharacteristic(equipment.getCharacteristic());
        updatedEquipment.setMaterial(equipment.getMaterial());
        updatedEquipment.setImagePath(equipment.getImagePath());
        updatedEquipment.setPrice(equipment.getPrice());
        updatedEquipment.setBrand(equipment.getBrand());
        updatedEquipment.setSize(equipment.getSize());
        return updatedEquipment;
    }
}
