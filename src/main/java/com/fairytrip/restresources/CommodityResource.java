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
            List shoes = shoesRepository.findAllShoes();
            commodities.add(shoes);
            List clothes = clothesRepository.findAllClothes();
            commodities.add(clothes);
            List tents = tentRepository.findAllTents();
            commodities.add(tents);
            List sleepingBags = sleepingBagRepository.findAllSleepingBags();
            commodities.add(sleepingBags);
            List dishes = dishesRepository.findAllDishes();
            commodities.add(dishes);
            List food = foodRepository.findAllFood();
            commodities.add(food);
            List backpacks = backpackRepository.findAllBackpacks();
            commodities.add(backpacks);
            List equipment = equipmentRepository.findAllEquipment();
            commodities.add(equipment);

                return crud.options()
                        .entity(commodities)
                        .build();

        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
