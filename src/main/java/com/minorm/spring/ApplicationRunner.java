package com.minorm.spring;

import com.minorm.spring.database.pool.ConnectionPool;
import com.minorm.spring.database.repository.CompanyRepository;
import com.minorm.spring.database.repository.UserRepository;
import com.minorm.spring.ioc.Container;
import com.minorm.spring.service.UserService;

public class ApplicationRunner {

    public static void main(String[] args) {
        var container = new Container();

//        var connectionPool = new ConnectionPool();
//        var userRepository = new UserRepository(connectionPool);
//        var companyRepository = new CompanyRepository(connectionPool);
//        var userService = new UserService(userRepository, companyRepository);

        // В данном случае мы не управляем созданием наших объектов
//        var connectionPool = container.get(ConnectionPool.class);
//        var userRepository = container.get(UserRepository.class);
//        var companyRepository = container.get(CompanyRepository.class);

        var userService = container.get(UserService.class);
        // TODO: 20.03.2025 userService
    }
}
