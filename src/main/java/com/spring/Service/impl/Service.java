package com.spring.Service.impl;

import com.spring.EntiryPage.mysqlEntiry.UserEntiry;
import com.spring.Mappers.UserInterface;
import com.spring.Service.ServiceInterface;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@org.springframework.stereotype.Service
@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRES_NEW)
public class Service implements ServiceInterface {

    private SqlSession sqlSession;
    private  Logger logger = Logger.getLogger(Service.class);
    private  SqlSessionFactory sqlSessionFactoryBean;

    @Autowired
    public Service(@Qualifier("sqlSessionFactoryBeanLocal") SqlSessionFactory sqlSessionFactoryBean) {
        this.sqlSessionFactoryBean = sqlSessionFactoryBean;
    }

    public Service() {

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation = Isolation.READ_COMMITTED)
    @Override
    public UserEntiry getUser(int id) {
        logger.info(String.valueOf(Service.class));
        sqlSession = sqlSessionFactoryBean.openSession();
        UserInterface userInterface = sqlSession.getMapper(UserInterface.class);
        UserEntiry userEntiry = userInterface.select(id);
        return userEntiry;
    }                   

    @Override
    public UserEntiry getUserByUserEntiry(String name) {
        sqlSession = sqlSessionFactoryBean.openSession();
        UserInterface mapper = sqlSession.getMapper(UserInterface.class);
        UserEntiry byUser = mapper.getUserByUser(name);
        System.out.println("byUser = " + byUser);
        return byUser;
    }
}
