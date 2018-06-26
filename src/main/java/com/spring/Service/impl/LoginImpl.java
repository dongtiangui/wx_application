package com.spring.Service.impl;
import com.github.pagehelper.PageHelper;
import com.spring.EntiryPage.ControllerEntiry.LoginUser;
import com.spring.EntiryPage.mysqlEntiry.UserEntiry;
import com.spring.Mappers.UserInterface;
import com.spring.Service.LoginInterface;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shiro.crypto.hash.SimpleHash;
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
    private SqlSessionFactory sqlSessionFactoryBean;

    @Autowired
    public LoginImpl(@Qualifier("sqlSessionFactoryBeanLocal") SqlSessionFactory sqlSessionFactoryBean) {
        this.sqlSessionFactoryBean = sqlSessionFactoryBean;
    }
    public SqlSession getSqlSession(){
        if (sqlSession!=null){
            return sqlSession;
        }
        sqlSession = sqlSessionFactoryBean.openSession();
        return sqlSession;
    }
    @Override
    public boolean ajax(String name,String password) {
        UserInterface anInterface = getSqlSession().getMapper(UserInterface.class);
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
        UserInterface mapper = getSqlSession().getMapper(UserInterface.class);
        boolean byUser = mapper.insertByUser(loginUser);
        return byUser;
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation = Isolation.SERIALIZABLE)
    @Override
    public boolean updata(String name, String password) {

        SimpleHash hash = new SimpleHash("MD5",password,null,1024);
        UserInterface userInterface = getSqlSession().getMapper(UserInterface.class);
        boolean updata = userInterface.updata(name, hash.toString());
        if (updata){
            return true;
        }
         return false;
    }
    public String getUserName(String name){
        UserInterface userInterface = getSqlSession().getMapper(UserInterface.class);
        String userName = userInterface.getUserRoles(name);
        return userName;
    }
}
