package com.fairytrip.restresources.repository;

import com.fairytrip.data.entities.Clothes;

import java.util.List;

public interface ClothesRepository {
    List<Clothes> findAllClothes();

    Clothes createClothes(Clothes clothes);

    Clothes updateClothes(Clothes clothes, Long clothesId);

    boolean deleteClothes(Long clothesId);
}
