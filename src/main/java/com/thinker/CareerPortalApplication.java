package com.thinker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
@ComponentScan(basePackages = "com.thinker")
public class CareerPortalApplication {
    
    private static final Logger logger = LoggerFactory.getLogger(CareerPortalApplication.class);

    public static void main(String[] args) {
        logger.info("Starting Career Portal Application...");
        try {
            SpringApplication.run(CareerPortalApplication.class, args);
            logger.info("Career Portal Application is running successfully!");
        } catch (Exception e) {
            logger.error("Application failed to start", e);
            throw e;
        }
    }
}