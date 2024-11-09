package com.thinker;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CareerPortalApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    @DisplayName("Context Load Test")
    void contextLoads() {
        assertNotNull(applicationContext, "Application context should load");
    }

    @Test
    @DisplayName("Application Main Class Test")
    void mainClassTest() {
        CareerPortalApplication.main(new String[]{});
    }
}