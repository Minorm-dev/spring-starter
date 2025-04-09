package com.minorm.spring.dto;

import com.minorm.spring.database.entity.Role;
import com.minorm.spring.validation.UserInfo;
import com.minorm.spring.validation.group.CreateAction;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Value
@FieldNameConstants
@UserInfo(groups = CreateAction.class)
public class UserCreateEditDto {

    @Email
    String username;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate birthDate;

    @Size(min = 3, max = 64)
    // @NotNull лучше не использовать над строками
    String firstname;

    String lastname;

    Role role;

    Integer companyId;

    MultipartFile image;
}
