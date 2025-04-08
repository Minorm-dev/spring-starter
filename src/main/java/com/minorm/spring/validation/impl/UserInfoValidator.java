package com.minorm.spring.validation.impl;

import com.minorm.spring.database.repository.CompanyRepository;
import com.minorm.spring.dto.UserCreateEditDto;
import com.minorm.spring.validation.UserInfo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static org.springframework.util.StringUtils.*;

@Component
@RequiredArgsConstructor
public class UserInfoValidator implements ConstraintValidator<UserInfo, UserCreateEditDto> {

    private final CompanyRepository companyRepository;

    @Override
    public boolean isValid(UserCreateEditDto value, ConstraintValidatorContext context) {
        return hasText(value.getFirstname()) || hasText(value.getLastname());
    }
}
