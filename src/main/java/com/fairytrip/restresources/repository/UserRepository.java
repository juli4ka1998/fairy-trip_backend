package com.fairytrip.restresources.repository;

import com.fairytrip.data.entities.User;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public interface UserRepository {
    boolean checkUser(User user) throws IOException, NoSuchAlgorithmException;

    User createUser(User user) throws SQLException, IOException, NoSuchAlgorithmException;

    User findUser(String email);

    void writeImage(FormDataBodyPart json, InputStream uploadedInputStream, FormDataContentDisposition fileDetail, User user);

    void deleteImage(User user);
}
