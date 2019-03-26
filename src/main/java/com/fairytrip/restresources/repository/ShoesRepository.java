package com.fairytrip.restresources.repository;


import com.fairytrip.data.entities.Shoes;

import java.util.List;

public interface ShoesRepository {
    List<Shoes> findAllShoes();

    Shoes createShoes(Shoes shoes);

    Shoes updateShoes(Shoes shoes, Long shoesId);

    boolean deleteShoes(Long shoesId);
}
