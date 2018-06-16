package com.spring.Service.impl;

import com.spring.EntiryPage.ControllerEntiry.LoginUser;
import com.spring.EntiryPage.mysqlEntiry.UserEntiry;
import com.spring.Mappers.UserInterface;
import com.spring.Service.LoginInterface;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@EnableTransactionManagement
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

    @Override
    public boolean insert(String name, String password,String roles) {
        SimpleHash hash = new SimpleHash("MD5",password,null,1024);
        LoginUser loginUser = new LoginUser();
        loginUser.setName(name);
        loginUser.setPassword(hash.toString());
        loginUser.setRoles(roles);
        sqlSession = sqlSessionFactoryBean.openSession();
        UserInterface mapper = sqlSession.getMapper(UserInterface.class);
        boolean byUser = mapper.insertByUser(loginUser);
        return byUser;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation = Isolation.SERIALIZABLE)
    @Override
    public boolean updata(String name, String password) {

        SimpleHash hash = new SimpleHash("MD5",password,null,1024);
        sqlSession = sqlSessionFactoryBean.openSession();

        UserInterface userInterface = sqlSession.getMapper(UserInterface.class);

        boolean updata = userInterface.updata(name, hash.toString());

        if (updata){

            return true;
        }
         return false;

    }

}
