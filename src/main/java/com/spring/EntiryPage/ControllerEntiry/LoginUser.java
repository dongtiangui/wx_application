package com.spring.EntiryPage.ControllerEntiry;

import java.io.Serializable;

public class LoginUser implements Serializable {
    public String name;
    public String password;
    public LoginUser(){}

    public LoginUser(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginUser{" + "name='" + name + '\'' + ", password='" + password + '\'' + '}';
    }
}
