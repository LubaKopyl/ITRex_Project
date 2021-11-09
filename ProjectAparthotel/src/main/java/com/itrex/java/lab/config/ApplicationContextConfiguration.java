package com.itrex.java.lab.config;

import com.itrex.java.lab.util.HibernateUtil;
import org.flywaydb.core.Flyway;
import org.hibernate.Session;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static com.itrex.java.lab.properties.Properties.H2_URL;
import static com.itrex.java.lab.properties.Properties.H2_USER;
import static com.itrex.java.lab.properties.Properties.H2_PASSWORD;
import static com.itrex.java.lab.properties.Properties.MIGRATIONS_LOCATION;

@Configuration
@ComponentScan("com.itrex.java.lab")

public class ApplicationContextConfiguration {
    @Bean(initMethod = "migrate")
    public Flyway flyway() {
        return Flyway.configure()
                .dataSource(H2_URL, H2_USER, H2_PASSWORD)
                .locations(MIGRATIONS_LOCATION)
                .load();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Session session() {
        return HibernateUtil.getSessionFactory().openSession();
    }
}
