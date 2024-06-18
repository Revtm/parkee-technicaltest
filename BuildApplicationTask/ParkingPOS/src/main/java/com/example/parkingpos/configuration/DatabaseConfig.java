package com.example.parkingpos.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {
    @Value("${db.postgres.driver}")
    private String driver;
    @Value("${db.postgres.url}")
    private String url;
    @Value("${db.postgres.username}")
    private String userName;
    @Value("${db.postgres.password}")
    private String password;

    @Bean
    public DataSource getPostgreDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);

        return dataSource;
    }

    @Bean
    public JdbcTemplate getFactory(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
