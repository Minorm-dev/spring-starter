package com.minorm.spring.service;

import com.minorm.spring.database.entity.Company;
import com.minorm.spring.database.repository.CompanyRepository;
import com.minorm.spring.database.repository.CrudRepository;
import com.minorm.spring.database.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CrudRepository<Integer, Company> companyRepository;

    public UserService(UserRepository userRepository,
                       CrudRepository<Integer, Company> companyRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }

}
