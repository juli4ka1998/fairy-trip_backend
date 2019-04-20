package com.fairytrip.restresources.repository;

import com.fairytrip.data.entities.Dishes;

import java.util.List;

public interface DishesRepository {
    List<Dishes> findAllDishes();

    Dishes createDishes(Dishes dishes);

    Dishes updateDishes(Dishes dishes, Long dishesId);

    Dishes deleteDishes(Long dishesId);
}
