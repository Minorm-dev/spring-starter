package com.minorm.spring.database.repository;

import com.minorm.spring.database.entity.Role;
import com.minorm.spring.database.entity.User;
import com.minorm.spring.dto.PersonalInfo;
import com.minorm.spring.dto.UserFilter;

import java.util.List;

public interface FilterUserRepository {

    List<User> findAllByFilter(UserFilter filter);

    List<PersonalInfo> findAllByCompanyIdAndRole(Integer companyId, Role role);
}
