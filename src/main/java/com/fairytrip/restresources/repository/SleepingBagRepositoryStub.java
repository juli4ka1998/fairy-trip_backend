package com.fairytrip.restresources.repository;

import com.fairytrip.data.entities.SleepingBag;

import java.util.List;

public class SleepingBagRepositoryStub implements SleepingBagRepository {
    CRUD crud = new CRUD();

    @Override
    public List<SleepingBag> findAllSleepingBags() {
        return  crud.read("select sb from SleepingBag sb");

    }

    @Override
    public SleepingBag createSleepingBag(SleepingBag sleepingBag) {
        return (SleepingBag) crud.create(sleepingBag);
    }

    @Override
    public SleepingBag updateSleepingBag(SleepingBag sleepingBag, Long sleepingBagId) {
        return (SleepingBag) crud.update(sleepingBag, sleepingBagId);
    }

    @Override
    public boolean deleteSleepingBag(Long sleepingBagId) {
        SleepingBag sleepingBag = new SleepingBag();
        return crud.delete(sleepingBagId, sleepingBag);
    }

    public SleepingBag setSleepingBagProperties(SleepingBag sleepingBag, SleepingBag updatedSleepingBag){
        updatedSleepingBag.setName(sleepingBag.getName());
        updatedSleepingBag.setCharacteristic(sleepingBag.getCharacteristic());
        updatedSleepingBag.setBrand(sleepingBag.getBrand());
        updatedSleepingBag.setImagePath(sleepingBag.getImagePath());
        updatedSleepingBag.setPrice(sleepingBag.getPrice());
        updatedSleepingBag.setSize(sleepingBag.getSize());
        updatedSleepingBag.setMaterial(sleepingBag.getMaterial());
        return updatedSleepingBag;
    }
}
