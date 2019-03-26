package com.fairytrip.restresources.repository;

import com.fairytrip.data.entities.Food;

import java.util.List;

public interface FoodRepository {
    List<Food> findAllFood();

    Food createFood(Food food);

    Food updateFood(Food food, Long foodId);

    boolean deleteFood(Long foodId);
}
