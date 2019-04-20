package com.fairytrip.restresources.repository;

import com.fairytrip.data.entities.Tent;

import java.util.List;

public class TentRepositoryStub implements TentRepository {
    CRUD crud = new CRUD();

    @Override
    public List<Tent> findAllTents() {
        return  crud.read("select t from Tent t");

    }

    @Override
    public Tent createTent(Tent tent) {
        return (Tent) crud.create(tent);
    }

    @Override
    public Tent updateTent(Tent tent, Long tentId) {
        return (Tent) crud.update(tent, tentId);
    }

    @Override
    public Tent deleteTent(Long tentId) {
        Tent tent = new Tent();
        return (Tent) crud.delete(tentId, tent);
    }

    public Tent setTentProperties(Tent tent, Tent updatedTent){
        updatedTent.setName(tent.getName());
        updatedTent.setCharacteristic(tent.getCharacteristic());
        updatedTent.setImagePath(tent.getImagePath());
        updatedTent.setMaterial(tent.getMaterial());
        updatedTent.setPrice(tent.getPrice());
        updatedTent.setBrand(tent.getBrand());
        updatedTent.setSize(tent.getSize());
        return updatedTent;
    }
}
