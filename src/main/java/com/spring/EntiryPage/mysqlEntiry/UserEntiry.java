package com.spring.EntiryPage.mysqlEntiry;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
@JsonSerialize
public class UserEntiry implements Serializable {
    private int id;
    private String name;
    private String password;
    private String time_db;
    public UserEntiry(){
    }
    public UserEntiry(int id, String name, String password, String time_db) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.time_db = time_db;
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
    @JsonSerialize
    @Override
    public String toString() {
        return "UserEntiry{" + "Id=" + id + ", user='" + name + '\'' + ", password='" + password + '\'' + ", time_db='" + time_db + '\'' + '}';
    }
}
