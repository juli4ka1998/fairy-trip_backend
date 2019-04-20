package com.fairytrip.restresources.repository;

import com.fairytrip.data.entities.Backpack;

import java.util.List;

public interface BackpackRepository {
    List<Backpack> findAllBackpacks();

    Backpack createBackpack(Backpack backpack);

    Backpack updateBackpack(Backpack backpack, Long backpackId);

    Backpack deleteBackpack(Long backpackId);
}
