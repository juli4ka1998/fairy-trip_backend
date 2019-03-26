package com.fairytrip.restresources.repository;


import com.fairytrip.data.entities.Dishes;

import java.util.List;

public class DishesRepositoryStub implements DishesRepository {
    CRUD crud = new CRUD();

    @Override
    public List<Dishes> findAllDishes() {
        return  crud.read("select d from Dishes d");

    }

    @Override
    public Dishes createDishes(Dishes dishes) {
        return (Dishes) crud.create(dishes);
    }

    @Override
    public Dishes updateDishes(Dishes dishes, Long dishesId) {
        return (Dishes) crud.update(dishes, dishesId);
    }

    @Override
    public boolean deleteDishes(Long dishesId) {
        Dishes dishes = new Dishes();
        return crud.delete(dishesId, dishes);
    }

    public Dishes setDishesProperties(Dishes dishes, Dishes updatedDishes){
        updatedDishes.setName(dishes.getName());
        updatedDishes.setCharacteristic(dishes.getCharacteristic());
        updatedDishes.setImagePath(dishes.getImagePath());
        updatedDishes.setSize(dishes.getSize());
        updatedDishes.setPrice(dishes.getPrice());
        updatedDishes.setBrand(dishes.getBrand());
        updatedDishes.setMaterial(dishes.getMaterial());
        return updatedDishes;
    }
}
