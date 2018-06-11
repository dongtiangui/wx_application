package com.spring.Service;


import com.spring.EntiryPage.ControllerEntiry.LoginUser;
import com.spring.EntiryPage.mysqlEntiry.UserEntiry;

public interface ServiceInterface {

    UserEntiry getUser(int id);

    UserEntiry getUserByUserEntiry(String name);

}
