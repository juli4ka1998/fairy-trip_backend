package com.fairytrip.restresources.repository;

import com.fairytrip.data.entities.Tent;

import java.util.List;

public interface TentRepository {
    List<Tent> findAllTents();

    Tent createTent(Tent tent);

    Tent updateTent(Tent tent, Long tentId);

    boolean deleteTent(Long tentId);
}
