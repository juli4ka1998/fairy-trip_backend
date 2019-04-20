package com.fairytrip.restresources.repository;

import com.fairytrip.data.HibernateUtil;
import com.fairytrip.data.entities.Clothes;
import com.fairytrip.data.entities.Shoes;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.ws.rs.core.Response;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CRUD {

    public Object create(Object o) {
        SessionFactory factory = null;
        Session session = null;
        Transaction transaction = null;

        try {
            factory = HibernateUtil.getSessionFactory();
            session = factory.openSession();
            transaction = session.beginTransaction();

            session.save(o);

            transaction.commit();

        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }finally {
            session.close();
        }
        return o;
    }

    public List read(String s) {

        List commodities = new ArrayList();
        SessionFactory factory = null;
        Session session = null;
        Transaction transaction = null;

        try {
            factory = HibernateUtil.getSessionFactory();
            session = factory.openSession();
            transaction = session.beginTransaction();

            Query query = session.createQuery(s);
            commodities = query.list();

            transaction.commit();

        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }finally {
            session.close();
            // factory.close();
        }
        return commodities;
    }

    public Object update( Object o, Long objectId) {
        Object updatedObject = new Object();
        SessionFactory factory = null;
        Session session = null;
        Transaction transaction = null;

        try {
            factory = HibernateUtil.getSessionFactory();
            session = factory.openSession();
            transaction = session.beginTransaction();

            updatedObject = setProperties(o, updatedObject, session, objectId);

            session.update(updatedObject);

            transaction.commit();

        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }finally {
            session.close();
            //factory.close();
        }
        return updatedObject;
    }

    public Object delete(Long objectId, Object deletedObject) {

        SessionFactory factory = null;
        Session session = null;
        Transaction transaction = null;
        boolean del;

        try {
            factory = HibernateUtil.getSessionFactory();
            session = factory.openSession();
            transaction = session.beginTransaction();

            deletedObject = session.get(deletedObject.getClass(), objectId);
            session.delete(deletedObject);

            transaction.commit();
            del = true;

        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            del = false;
        }finally {
            session.close();
        }
        return deletedObject;
    }

    public Object check(String s, String login) {

        Object o = new Object();
        SessionFactory factory = null;
        Session session = null;
        Transaction transaction = null;

        try {
            factory = HibernateUtil.getSessionFactory();
            session = factory.openSession();
            transaction = session.beginTransaction();

            Query query = session.createQuery(s);
            query.setParameter(1, login);
            o = query.getSingleResult();
            transaction.commit();

        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }finally {
            session.close();
            // factory.close();
        }
        return o;
    }

    public Object setProperties(Object o, Object updatedObject, Session session, Long objectId){

        if (o.getClass() == Shoes.class){
            updatedObject = session.get(Shoes.class, objectId);
            Shoes shoes = (Shoes) o;
            Shoes updatedShoes = (Shoes) updatedObject;
            ShoesRepositoryStub srs = new ShoesRepositoryStub();

            return srs.setShoesProperties(shoes, updatedShoes);
        }
        if(o.getClass() == Clothes.class){
            updatedObject = session.get(Clothes.class, objectId);
            Clothes clothes = (Clothes) o;
            Clothes updatedClothes = (Clothes) updatedObject;
            ClothesRepositoryStub crs = new ClothesRepositoryStub();

            return crs.setClothesProperties(clothes, updatedClothes);
        }
        return updatedObject;
    }

    public Object getObject(Object o, Session session, Long objectId){
        if (o.getClass() == Shoes.class) {
            o = session.get(Shoes.class, objectId);
            Shoes deletedShoes = (Shoes) o;
            return deletedShoes;
        }
        if (o.getClass() == Clothes.class) {
            o = session.get(Clothes.class, objectId);
            Clothes deletedClothes = (Clothes) o;
            return deletedClothes;
        }

        return o;
    }

    public Response.ResponseBuilder options() {
       return Response.ok()
              .header("Access-Control-Allow-Origin", "*")
              .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS, DELETE")
              .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");
    }

    public void writeToFile(InputStream uploadedInputStream,
                            String uploadedFileLocation) {

        try {
            OutputStream out = new FileOutputStream(new File(
                    uploadedFileLocation));
            int read = 0;
            byte[] bytes = new byte[1024];

            out = new FileOutputStream(new File(uploadedFileLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            //System.out.println("ll");
        }
    }

}
