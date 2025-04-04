package com.minorm.spring.dto;

import org.springframework.beans.factory.annotation.Value;

public interface PersonalInfo2 {

    String getFirstname();

    String getLastname();

    String getBirthDate();

    @Value("#{target.firstname + ' ' + target.lastname}") // projection позволяет использовать SEL, также можно передавать бины
    String getFullName();
}
