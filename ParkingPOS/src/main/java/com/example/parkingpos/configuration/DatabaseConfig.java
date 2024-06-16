package com.example.parkingpos.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.DataSourceFactory;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {
    @Bean
    public DataSource getPostgreDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/parking-pos");
        dataSource.setUsername("postgres");
        dataSource.setPassword("pergipergi");

        return dataSource;
    }

    @Bean
    public JdbcTemplate getFactory(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
