package com.fairytrip.restresources.repository;

import com.fairytrip.data.entities.SleepingBag;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.io.InputStream;
import java.util.List;

public interface SleepingBagRepository {
    List<SleepingBag> findAllSleepingBags();

    SleepingBag createSleepingBag(SleepingBag sleepingBag);

    SleepingBag updateSleepingBag(SleepingBag sleepingBag, Long sleepingBagId);

    SleepingBag deleteSleepingBag(Long sleepingBagId);

    List<SleepingBag> searchSleepingBag(String s);

    void writeImage(FormDataBodyPart json, InputStream uploadedInputStream, FormDataContentDisposition fileDetail, SleepingBag sleepingBag);

    void deleteImage(SleepingBag sleepingBag);
}
