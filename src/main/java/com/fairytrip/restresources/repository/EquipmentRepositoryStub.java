package com.fairytrip.restresources.repository;

import com.fairytrip.data.entities.Equipment;

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
    public boolean deleteEquipment(Long equipmentId) {
        Equipment equipment = new Equipment();
        return crud.delete(equipmentId, equipment);
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
