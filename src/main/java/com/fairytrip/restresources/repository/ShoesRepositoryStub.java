package com.fairytrip.restresources.repository;


import com.fairytrip.data.entities.Shoes;

import java.util.List;

public class ShoesRepositoryStub implements ShoesRepository {
    CRUD crud = new CRUD();

    @Override
    public List<Shoes> findAllShoes() {
        return  crud.read("select s from Shoes s");

    }

    @Override
    public Shoes createShoes(Shoes shoes) {
        return (Shoes) crud.create(shoes);
    }

    @Override
    public Shoes updateShoes(Shoes shoes,  Long shoesId) {
        return (Shoes) crud.update(shoes, shoesId);
    }

    @Override
    public boolean deleteShoes(Long shoesId) {
        Shoes shoes = new Shoes();
        return crud.delete(shoesId, shoes);
    }

    public Shoes setShoesProperties(Shoes shoes, Shoes updatedShoes){
        updatedShoes.setName(shoes.getName());
        updatedShoes.setCharacteristic(shoes.getCharacteristic());
        updatedShoes.setImagePath(shoes.getImagePath());
        updatedShoes.setPrice(shoes.getPrice());
        updatedShoes.setBrand(shoes.getBrand());
        updatedShoes.setSizes(shoes.getSizes());
        updatedShoes.setMaterial(shoes.getMaterial());
        updatedShoes.setSex(shoes.getSex());
        return updatedShoes;
    }

}
