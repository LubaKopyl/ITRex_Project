package com.itrex.java.lab.repository;

import com.itrex.java.lab.config.ApplicationContextConfiguration;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public abstract class BaseRepositoryTest {

    private final Flyway flyway;
    private final ApplicationContext applicationContext;

    public BaseRepositoryTest () {
        applicationContext = new AnnotationConfigApplicationContext(ApplicationContextConfiguration.class);
        flyway = applicationContext.getBean(Flyway.class);
    }

    @BeforeEach
    public void initDB() {
        flyway.migrate();
    }

    @AfterEach
    public void cleanDB() {
        flyway.clean();
    }
}
