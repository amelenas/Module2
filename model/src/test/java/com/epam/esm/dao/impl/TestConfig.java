package com.epam.esm.dao.impl;

import com.epam.esm.dao.config.DBParameter;
import com.epam.esm.dao.config.DBResourceManager;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
/*
@Configuration
@ComponentScan("com.epam.esm")
@PropertySource("classpath:database.properties")
@Profile("test")
public class TestConfig {
    @Bean
    public DataSource dataSource(@Value("${db.user}") String user,
                                 @Value("${db.password}") String password,
                                 @Value("${db.driver}") String className,
                                 @Value("${db.url}") String connectionUrl,
                                 @Value("${db.connections}") Integer connectionsNumber) {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUsername(user);
        basicDataSource.setPassword(password);
        basicDataSource.setDriverClassName(className);
        basicDataSource.setUrl(connectionUrl);
        basicDataSource.setMaxActive(connectionsNumber);

        Resource initData = new ClassPathResource("creatingTestTables.sql");
        Resource fillData = new ClassPathResource("fillingTestTables.sql");
        DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initData, fillData);
        DatabasePopulatorUtils.execute(databasePopulator, basicDataSource);
        return basicDataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }*/
@ComponentScan("com.epam.esm")
@Configuration
public class TestConfig {
    @Autowired
    private DBResourceManager dbResourceManager;

    @Bean
    public ComboPooledDataSource getDataSource() throws PropertyVetoException {
        dbResourceManager.loadProperties("database.properties");
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(dbResourceManager.properties.getProperty(DBParameter.DATABASE_DRIVER));
        comboPooledDataSource.setJdbcUrl(dbResourceManager.properties.getProperty(DBParameter.DATABASE_URL));
        comboPooledDataSource.setUser(dbResourceManager.properties.getProperty(DBParameter.USER));
        comboPooledDataSource.setPassword(dbResourceManager.properties.getProperty(DBParameter.PASSWORD));
        return comboPooledDataSource;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate() throws PropertyVetoException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(getDataSource());
        return jdbcTemplate;
    }


    @Bean
    public PlatformTransactionManager transactionManager() throws PropertyVetoException {
        DataSourceTransactionManager tm =
                new DataSourceTransactionManager();
        tm.setDataSource(getDataSource());
        return tm;
    }

}
