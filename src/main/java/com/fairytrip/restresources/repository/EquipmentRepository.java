package com.fairytrip.restresources.repository;

import com.fairytrip.data.entities.Equipment;

import java.util.List;

public interface EquipmentRepository {
    List<Equipment> findAllEquipment();

    Equipment createEquipment(Equipment equipment);

    Equipment updateEquipment(Equipment equipment, Long equipmentId);

    boolean deleteEquipment(Long equipmentId);
}
