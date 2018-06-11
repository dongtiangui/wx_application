//package com.spring.config.mongodb;
//
//import com.mongodb.MongoClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.MongoDbFactory;
//import org.springframework.data.mongodb.core.MongoClientFactoryBean;
//import org.springframework.data.mongodb.core.MongoTemplate;
//
//@Configuration
//public class mongodbConfig {
//
//
//    @Bean
//    public MongoClientFactoryBean mongoClientFactoryBean(){
//
//         MongoClientFactoryBean mongoClientFactoryBean = new MongoClientFactoryBean();
//
//         mongoClientFactoryBean.setHost("localhost");
//         mongoClientFactoryBean.setPort(27017);
//         return mongoClientFactoryBean;
//    }
//
//    @Bean("mongoTemplateLocal")
//    public MongoTemplate mongoTemplate(){
//
//        MongoTemplate template = new MongoTemplate((MongoDbFactory) mongoClientFactoryBean());
//
//        return template;
//    }
//    @Bean
//    public MongoClient mongoClient(){
//
//        MongoClient mongoClient = new MongoClient("localhost",27017);
//        return mongoClient;
//    }
//
//}
