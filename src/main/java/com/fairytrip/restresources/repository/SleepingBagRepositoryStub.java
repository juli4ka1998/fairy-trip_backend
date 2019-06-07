package com.fairytrip.restresources.repository;

import com.fairytrip.data.entities.SleepingBag;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.io.InputStream;
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
    public SleepingBag deleteSleepingBag(Long sleepingBagId) {
        SleepingBag sleepingBag = new SleepingBag();
        return (SleepingBag) crud.delete(sleepingBagId, sleepingBag);
    }
    @Override
    public List<SleepingBag> searchSleepingBag(String s) {
        return  crud.read("select s from SleepingBag s where name like '%" + s + "%'");

    }

    @Override
    public void writeImage(FormDataBodyPart json, InputStream uploadedInputStream, FormDataContentDisposition fileDetail, SleepingBag sleepingBag){
        String filePath = "/images/"
                + fileDetail.getFileName();
        crud.writeImage(json, uploadedInputStream, filePath);
        sleepingBag.setImagePath(filePath);
    }

    @Override
    public void deleteImage(SleepingBag sleepingBag) {
        crud.deleteImage(sleepingBag.getImagePath());
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
