package com.smart.catalog.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@org.springframework.context.annotation.Configuration
public class Configuration {
//    @Bean
//    public CommonsRequestLoggingFilter requestLoggingFilter() {
//        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
//        loggingFilter.setIncludeClientInfo(true);
//        loggingFilter.setIncludeQueryString(true);
//        loggingFilter.setIncludePayload(true);
//        loggingFilter.setMaxPayloadLength(64000);
//        return loggingFilter;
//    }

//    @Bean
//    @ConfigurationProperties("spring.datasource")
//    public HikariDataSource hikariDataSource() {
//        return DataSourceBuilder
//                .create()
//                .driverClassName("org.postgresql.Driver")
//                .username(" postgres")
//                .password(" postgres")
//                .url("jdbc:postgresql://localhost:5432/catalog")
//                .type(HikariDataSource.class)
//                .build();
//    }
}
