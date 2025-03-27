package com.minorm.spring.integration.service;

import com.minorm.spring.database.pool.ConnectionPool;
import com.minorm.spring.integration.annotation.IT;
import com.minorm.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

@IT
@RequiredArgsConstructor
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserServiceIT {

    private final UserService userService;
    private final ConnectionPool pool1;

    @Test
    void test(){
        System.out.println();
    }

    @Test
    void test2(){
        System.out.println();
    }
}
