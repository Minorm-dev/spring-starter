package com.minorm.spring;

import com.minorm.spring.database.pool.ConnectionPool;
import com.minorm.spring.database.repository.CompanyRepository;
import com.minorm.spring.database.repository.CrudRepository;
import com.minorm.spring.database.repository.UserRepository;
import com.minorm.spring.ioc.Container;
import com.minorm.spring.service.UserService;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.Serializable;

public class ApplicationRunner {

    public static void main(String[] args) {
        String value = "hello";
        System.out.println(CharSequence.class.isAssignableFrom(value.getClass()));
        System.out.println(BeanFactoryPostProcessor.class.isAssignableFrom(value.getClass()));
        System.out.println(Serializable.class.isAssignableFrom(value.getClass()));

        try (var context = new ClassPathXmlApplicationContext("application.xml")) {
            var connectionPool = context.getBean("p1", ConnectionPool.class);
            System.out.println(connectionPool);


            var companyRepository = context.getBean("companyRepository", CrudRepository.class);
            System.out.println(companyRepository.findById(1));
        }
    }
}