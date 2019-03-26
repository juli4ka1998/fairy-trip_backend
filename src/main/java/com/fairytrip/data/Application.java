package com.fairytrip.data;


import com.fairytrip.data.entities.Shoes;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Shoes shoes = new Shoes();
        shoes.setMaterial("metal");
        shoes.setSex("woman");
        shoes.setBrand("Nike");
        shoes.setPrice(2000);
        shoes.setName("Boots");
        shoes.setImagePath("/img/img.jpg");
        shoes.setCharacteristic("Very good!");

        List<Integer> sizes = new ArrayList<Integer>();
        sizes.add(37);
        sizes.add(38);
        sizes.add(39);
        shoes.setSizes(sizes);



        session.save(shoes);

        session.getTransaction().commit();
        session.close();
    }
}
