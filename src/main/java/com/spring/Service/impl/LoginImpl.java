package com.spring.Service.impl;

import com.spring.EntiryPage.mysqlEntiry.UserEntiry;
import com.spring.Mappers.UserInterface;
import com.spring.Service.LoginInterface;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@org.springframework.stereotype.Service
public class LoginImpl implements LoginInterface {
    private SqlSession sqlSession;
    private Logger logger = Logger.getLogger(Service.class);
    private SqlSessionFactory sqlSessionFactoryBean;

    @Autowired
    public LoginImpl(@Qualifier("sqlSessionFactoryBeanLocal") SqlSessionFactory sqlSessionFactoryBean) {
        this.sqlSessionFactoryBean = sqlSessionFactoryBean;
    }



    @Override
    public boolean ajax(String name,String password) {
        sqlSession =sqlSessionFactoryBean.openSession();
        UserInterface anInterface = sqlSession.getMapper(UserInterface.class);
        UserEntiry user = anInterface.getUserByUser(name);
        if (!user.equals(null)){
            if (user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
    public UserEntiry getUser(String name){
        sqlSession = sqlSessionFactoryBean.openSession();
        UserInterface anInterface = sqlSession.getMapper(UserInterface.class);
        UserEntiry user = anInterface.getUserByUser(name);
        return user;
    }
}
