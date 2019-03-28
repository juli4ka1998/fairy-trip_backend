package com.fairytrip.restresources;

import com.fairytrip.data.entities.Dishes;
import com.fairytrip.restresources.repository.*;

import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("commodities")
public class CommodityResource {
    DishesRepository dishesRepository = new DishesRepositoryStub();
    ShoesRepository shoesRepository = new ShoesRepositoryStub();
    ClothesRepository clothesRepository = new ClothesRepositoryStub();
    BackpackRepository backpackRepository = new BackpackRepositoryStub();
    EquipmentRepository equipmentRepository = new EquipmentRepositoryStub();
    FoodRepository foodRepository = new FoodRepositoryStub();
    SleepingBagRepository sleepingBagRepository = new SleepingBagRepositoryStub();
    TentRepository tentRepository = new TentRepositoryStub();

    CRUD crud = new CRUD();
    @OPTIONS
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getCommodities(){
        try {
            return crud.options().build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllCommodities(){
        try {
            List<Object> commodities = new ArrayList<>();
            List dishes = dishesRepository.findAllDishes();
            for(int i = 0; i < dishes.size(); i++)
                commodities.add(dishes.get(i));
            List shoes = shoesRepository.findAllShoes();
            for(int i = 0; i < shoes.size(); i++)
                commodities.add(shoes.get(i));
            List clothes = clothesRepository.findAllClothes();
            for(int i = 0; i < clothes.size(); i++)
                commodities.add(clothes.get(i));
            List backpacks = backpackRepository.findAllBackpacks();
            for(int i = 0; i < backpacks.size(); i++)
                commodities.add(backpacks.get(i));
            List equipment = equipmentRepository.findAllEquipment();
            for(int i = 0; i < equipment.size(); i++)
                commodities.add(equipment.get(i));
            List food = foodRepository.findAllFood();
            for(int i = 0; i < food.size(); i++)
                commodities.add(food.get(i));
            List sleepingBags = sleepingBagRepository.findAllSleepingBags();
            for(int i = 0; i < sleepingBags.size(); i++)
                commodities.add(sleepingBags.get(i));
            List tents = tentRepository.findAllTents();
            for(int i = 0; i < tents.size(); i++)
                commodities.add(tents.get(i));
                return crud.options()
                        .entity(commodities)
                        .build();

        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
