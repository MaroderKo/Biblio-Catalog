package com.smart.catalog;

import org.postgresql.Driver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.sql.SQLException;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories
@EntityScan("com.smart.catalog")
public class CatalogApplication {

    public static void main(String[] args) throws SQLException {
        java.sql.DriverManager.registerDriver(new Driver());
        SpringApplication.run(CatalogApplication.class, args);
    }

}
