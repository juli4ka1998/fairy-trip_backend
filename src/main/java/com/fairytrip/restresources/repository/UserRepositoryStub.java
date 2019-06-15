package com.fairytrip.restresources.repository;

import com.fairytrip.data.HashPass;
import com.fairytrip.data.entities.User;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

public class UserRepositoryStub implements UserRepository {
    CRUD crud = new CRUD();

    @Override
    public boolean checkUser(User user) throws IOException, NoSuchAlgorithmException {
        String email = user.getEmail();
        String pass = user.getPassword();
        try {
            User selectedUser = (User) crud.check("select a from User a where a.email=?1", email);

            String saltKey= selectedUser.getSalt();
            String correctPass = selectedUser.getPassword();
            HashPass hp = new HashPass();
            return hp.checkPassword(pass, saltKey, correctPass);
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public User createUser(User user) throws SQLException, IOException, NoSuchAlgorithmException {
        String email = user.getEmail();
        String pass = user.getPassword();
        System.out.println(user.getBirthdayDate());
        try {
            User existedUser = (User) crud.check("select a from User a where a.email=?1", email);
            //if (existedUser == null) {
            return null;
            //}
        }catch (Exception e) {
            HashPass hp = new HashPass();
            String saltKey = hp.generateSaltKey();
            pass = hp.hashPassword(pass, saltKey);
            user.setPassword(pass);
            user.setSalt(saltKey);
            return (User) crud.create(user);

        }
    }

    @Override
    public User findUser(String email) {
        return  (User) crud.check("select a from User a where a.email=?1", email);
    }

    @Override
    public void writeImage(FormDataBodyPart json, InputStream uploadedInputStream, FormDataContentDisposition fileDetail, User user){
        String filePath = "/images/"
                + fileDetail.getFileName();
        crud.writeImage(json, uploadedInputStream, filePath);
        user.setPhotoPath(filePath);
    }

    @Override
    public void deleteImage(User user) {
        crud.deleteImage(user.getPhotoPath());
    }



}

