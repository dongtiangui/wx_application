package com.spring.Mappers;
import com.spring.EntiryPage.ControllerEntiry.LoginUser;
import com.spring.EntiryPage.mysqlEntiry.UserEntiry;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@EnableTransactionManagement
public interface UserInterface {

    UserEntiry getUserByUser(String name);

    boolean insertByUser(LoginUser loginUser);


    boolean updata(@Param("UserName") String name,@Param("password") String pass);


}
