package com.fairytrip.restresources;

import com.fairytrip.data.entities.Dishes;
import com.fairytrip.restresources.repository.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("comm")
public class CommodityResource {
    DishesRepository dishesRepository = new DishesRepositoryStub();
    ShoesRepository shoesRepository = new ShoesRepositoryStub();
    ClothesRepository clothesRepository = new ClothesRepositoryStub();
    BackpackRepository backpackRepository = new BackpackRepositoryStub();
    EquipmentRepository equipmentRepository = new EquipmentRepositoryStub();
    FoodRepository foodRepository = new FoodRepositoryStub();
    SleepingBagRepository sleepingBagRepository = new SleepingBagRepositoryStub();
    TentRepository tentRepository = new TentRepositoryStub();

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<List<Object>> getAllCommodities(){
        List<List<Object>> commodities = new ArrayList<>();
        List dishes = dishesRepository.findAllDishes();
        commodities.add(dishes);
        List shoes = shoesRepository.findAllShoes();
        commodities.add(shoes);
        List clothes = clothesRepository.findAllClothes();
        commodities.add(clothes);
        List backpacks = backpackRepository.findAllBackpacks();
        commodities.add(backpacks);
        List equipment = equipmentRepository.findAllEquipment();
        commodities.add(equipment);
        List food = foodRepository.findAllFood();
        commodities.add(food);
        List sleepingBags = sleepingBagRepository.findAllSleepingBags();
        commodities.add(sleepingBags);
        List tents = tentRepository.findAllTents();
        commodities.add(tents);

        return commodities;
    }
}
