package com.TractorParts.dao.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Formatter;

@Entity
@Table(name = "dbuser")
public class User {

    private int     idUser;
    private String  login,
                    password,
                    name,
                    phone;

    public User() {    }
    public User(String login, String password, String name, String phone) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "user_id")
    public int getIdUser() {
        return idUser;
    }
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Column(name = "user_login")
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = "user_password")
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "user_name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "user_phone")
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return new Formatter().format("User: id=%d, name=%s, login=%s, pass=%s, phone=%s",
                idUser,name,login,password,phone).toString();
    }
}
