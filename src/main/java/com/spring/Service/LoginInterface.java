package com.spring.Service;


public interface LoginInterface {

    boolean ajax(String name,String password);

    boolean insert(String name,String password,String roles);

    boolean updata(String name,String password);

    String getUserName(String name);
}
