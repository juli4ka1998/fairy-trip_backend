package com.fairytrip.restresources.repository;

import com.fairytrip.data.entities.SleepingBag;

import java.util.List;

public interface SleepingBagRepository {
    List<SleepingBag> findAllSleepingBags();

    SleepingBag createSleepingBag(SleepingBag sleepingBag);

    SleepingBag updateSleepingBag(SleepingBag sleepingBag, Long sleepingBagId);

    SleepingBag deleteSleepingBag(Long sleepingBagId);
}
