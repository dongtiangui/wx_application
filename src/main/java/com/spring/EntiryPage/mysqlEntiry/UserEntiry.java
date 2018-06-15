package com.spring.EntiryPage.mysqlEntiry;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
@JsonSerialize
public class UserEntiry implements Serializable {
    private int id;
    private String name;
    private String password;
    private String time_db;
    private String roles;

    public UserEntiry(){
    }

    public UserEntiry(int id, String name, String password, String time_db, String roles) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.time_db = time_db;
        this.roles = roles;
    }

    @JsonSerialize
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @JsonSerialize
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @JsonSerialize
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @JsonSerialize
    public String getTime_db() {
        return time_db;
    }

    public void setTime_db(String time_db) {
        this.time_db = time_db;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @JsonSerialize

    @Override
    public String toString() {
        return "UserEntiry{" + "id=" + id + ", name='" + name + '\'' + ", password='" + password + '\'' + ", time_db='" + time_db + '\'' + ", roles='" + roles + '\'' + '}';
    }
}
