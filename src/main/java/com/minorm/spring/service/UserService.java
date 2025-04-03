package com.minorm.spring.service;

import com.minorm.spring.database.entity.Company;
import com.minorm.spring.database.repository.CompanyRepository;
import com.minorm.spring.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

}
