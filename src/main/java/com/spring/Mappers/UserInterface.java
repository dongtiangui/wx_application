package com.spring.Mappers;
import com.spring.EntiryPage.mysqlEntiry.UserEntiry;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@EnableTransactionManagement
public interface UserInterface {

    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.NESTED)
    UserEntiry select(int id);


    UserEntiry getUserByUser(String name);
}
