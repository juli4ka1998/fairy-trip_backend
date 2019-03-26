package com.fairytrip.restresources.repository;

import com.fairytrip.data.HibernateUtil;
import com.fairytrip.data.entities.Clothes;
import com.fairytrip.data.entities.Shoes;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ClothesRepositoryStub implements ClothesRepository {
    CRUD crud = new CRUD();

    @Override
    public List<Clothes> findAllClothes() {
        return  crud.read("select c from Clothes c");

    }

    @Override
    public Clothes createClothes(Clothes clothes) {
        return (Clothes) crud.create(clothes);
    }

    @Override
    public Clothes updateClothes(Clothes clothes,  Long clothesId) {
        return (Clothes) crud.update(clothes, clothesId);
    }
    @Override
    public boolean deleteClothes(Long clothesId) {
        Clothes clothes = new Clothes();
        return crud.delete(clothesId, clothes);
    }

    public Clothes setClothesProperties(Clothes clothes, Clothes updatedClothes){
        updatedClothes.setName(clothes.getName());
        updatedClothes.setImagePath(clothes.getImagePath());
        updatedClothes.setCharacteristic(clothes.getCharacteristic());
        updatedClothes.setPrice(clothes.getPrice());
        updatedClothes.setSex(clothes.getSex());
        updatedClothes.setBrand(clothes.getBrand());
        updatedClothes.setMaterial(clothes.getMaterial());
        updatedClothes.setSizes(clothes.getSizes());
        return updatedClothes;
    }
}
