package com.fairytrip.data.entities;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Blob;

@XmlRootElement
@Entity
@Table(name = "admin_login")
public class Admin {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Long adminId;

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "password")
    private String password;


    @Column(name = "salt")
    private String salt;

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
