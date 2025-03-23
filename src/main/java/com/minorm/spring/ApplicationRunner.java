package com.minorm.spring;

import com.minorm.spring.database.pool.ConnectionPool;
import com.minorm.spring.database.repository.CompanyRepository;
import com.minorm.spring.database.repository.UserRepository;
import com.minorm.spring.ioc.Container;
import com.minorm.spring.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationRunner {

    public static void main(String[] args) {
        CompanyRepository companyRepository;
        try (var context = new ClassPathXmlApplicationContext("application.xml")) {
            var connectionPool = context.getBean("p1", ConnectionPool.class);
            System.out.println(connectionPool);


            companyRepository = context.getBean("companyRepository", CompanyRepository.class);
            System.out.println(companyRepository);
        }
    }
}