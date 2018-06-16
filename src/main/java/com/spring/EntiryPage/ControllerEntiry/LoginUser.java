package com.spring.EntiryPage.ControllerEntiry;

import java.io.Serializable;

public class LoginUser implements Serializable {
    public String name;
    public String password;
    public String roles;

    public LoginUser(){}

    public LoginUser(String name, String password,String roles) {
        this.name = name;
        this.password = password;
        this.roles = roles;
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

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "LoginUser{" + "name='" + name + '\'' + ", password='" + password + '\'' + ", roles='" + roles + '\'' + '}';
    }
}
