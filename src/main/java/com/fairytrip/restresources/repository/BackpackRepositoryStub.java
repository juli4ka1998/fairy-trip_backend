package com.fairytrip.restresources.repository;

import com.fairytrip.data.entities.Backpack;

import java.util.List;

public class BackpackRepositoryStub implements BackpackRepository {
    CRUD crud = new CRUD();

    @Override
    public List<Backpack> findAllBackpacks() {
        return  crud.read("select b from Backpack b");

    }

    @Override
    public Backpack createBackpack(Backpack backpack) {
        return (Backpack) crud.create(backpack);
    }

    @Override
    public Backpack updateBackpack(Backpack backpack, Long backpackId) {
        return (Backpack) crud.update(backpack, backpackId);
    }

    @Override
    public Backpack deleteBackpack(Long backpackId) {
        Backpack backpack = new Backpack();
        return (Backpack) crud.delete(backpackId, backpack);
    }

    public Backpack setBackpackProperties(Backpack backpack, Backpack updatedBackpack){
        updatedBackpack.setBrand(backpack.getBrand());
        updatedBackpack.setSizes(backpack.getSizes());
        updatedBackpack.setName(backpack.getName());
        updatedBackpack.setCharacteristic(backpack.getCharacteristic());
        updatedBackpack.setImagePath(backpack.getImagePath());
        updatedBackpack.setPrice(backpack.getPrice());
        updatedBackpack.setMaterial(backpack.getMaterial());
        updatedBackpack.setSex(backpack.getSex());
        return updatedBackpack;
    }
}
