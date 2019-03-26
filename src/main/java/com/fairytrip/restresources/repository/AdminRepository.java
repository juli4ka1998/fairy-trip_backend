package com.fairytrip.restresources.repository;

import com.fairytrip.data.entities.Admin;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public interface AdminRepository {
    boolean checkAdmin(Admin admin) throws IOException, NoSuchAlgorithmException;
    Admin createAdmin(Admin admin) throws SQLException, IOException, NoSuchAlgorithmException;
}
