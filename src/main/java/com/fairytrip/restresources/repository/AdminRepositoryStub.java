package com.fairytrip.restresources.repository;

import com.fairytrip.data.HashPass;
import com.fairytrip.data.entities.Admin;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class AdminRepositoryStub implements AdminRepository {
    CRUD crud = new CRUD();

    @Override
    public boolean checkAdmin(Admin admin) throws IOException, NoSuchAlgorithmException {
        String login = admin.getLogin();
        String pass = admin.getPassword();

        Admin selectedAdmin = (Admin) crud.check("select a from Admin a where a.login=?1", login);

        String saltKey= selectedAdmin.getSalt();
        String correctPass = selectedAdmin.getPassword();
        HashPass hp = new HashPass();
        return hp.checkPassword(pass, saltKey, correctPass);
    }

    @Override
    public Admin createAdmin(Admin admin) throws SQLException, IOException, NoSuchAlgorithmException {
        String pass = admin.getPassword();
        HashPass hp = new HashPass();
        String saltKey = hp.generateSaltKey();
        pass = hp.hashPassword(pass, saltKey);
        admin.setPassword(pass);
        admin.setSalt(saltKey);
        return (Admin) crud.create(admin);
    }



}
