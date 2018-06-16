package com.spring.Service;

import com.spring.EntiryPage.ControllerEntiry.LoginUser;

public interface LoginInterface {

    boolean ajax(String name,String password);

    boolean insert(String name,String password,String roles);

    boolean updata(String name,String password);

}
