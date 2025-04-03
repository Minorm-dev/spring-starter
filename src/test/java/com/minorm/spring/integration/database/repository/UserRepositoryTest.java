package com.minorm.spring.integration.database.repository;

import com.minorm.spring.database.entity.Role;
import com.minorm.spring.database.entity.User;
import com.minorm.spring.database.repository.UserRepository;
import com.minorm.spring.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@IT
@RequiredArgsConstructor
class UserRepositoryTest {

    private final UserRepository userRepository;

    @Test
    void checkUpdate() {
        var ivan = userRepository.getById(1L);
        assertSame(Role.ADMIN, ivan.getRole());
        ivan.setBirthDate(LocalDate.now());

        var resultCount = userRepository.updateRole(Role.USER, 1L, 5L);
        assertEquals(2, resultCount);

//        ivan.getCompany().getName(); // test will be failed because Lazy init.

        var theSameIvan = userRepository.getById(1L);
        assertSame(Role.USER, theSameIvan.getRole());
    }

    @Test
    void checkQueries(){
        var users = userRepository.findAllBy("a", "ov");
        assertThat(users).hasSize(3);
        System.out.println(users);
    }

}