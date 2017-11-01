package com.facturacion.sanjorge.SanJorgeFacturacion.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.undertow.Undertow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.undertow.UndertowBuilderCustomizer;
import org.springframework.boot.context.embedded.undertow.UndertowEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by SISTEMAS03-PC on 26/10/2017.
 */
@Configuration
public class DataBaseConfig {


    @Autowired
    private Environment environment;


    @Bean(name = "sqlDataSource", destroyMethod = "close")
    @Primary
    public DataSource sqlDataSource() {
        Properties props = new Properties();
        props.setProperty("poolName", environment.getRequiredProperty("spring.datasource.primary.hikari.pool-name"));
        props.setProperty("dataSourceClassName", environment.getRequiredProperty("spring.datasource.primary.driver-class-name"));
        props.setProperty("dataSource.url", environment.getRequiredProperty("spring.datasource.primary.url"));
        props.setProperty("dataSource.user", environment.getRequiredProperty("spring.datasource.primary.hikari.username"));
        props.setProperty("dataSource.password", environment.getRequiredProperty("spring.datasource.primary.hikari.password"));
        props.setProperty("connectionTestQuery", environment.getRequiredProperty("spring.datasource.primary.hikari.connection-test-query"));
        props.setProperty("minimumIdle", environment.getRequiredProperty("spring.datasource.primary.hikari.minimum-idle"));
        props.setProperty("maximumPoolSize", environment.getRequiredProperty("spring.datasource.primary.hikari.maximum-pool-size"));
        props.setProperty("poolName", environment.getRequiredProperty("spring.datasource.primary.hikari.pool-name"));
        HikariConfig config = new HikariConfig(props);
        HikariDataSource ds = new HikariDataSource(config);

        return ds;
    }


    @Bean(name = "h2DataSource", destroyMethod = "close")
    public DataSource h2DataSource() {
        Properties props = new Properties();
        props.setProperty("poolName", environment.getRequiredProperty("spring.datasource.secondary.hikari.pool-name"));
        props.setProperty("dataSourceClassName", environment.getRequiredProperty("spring.datasource.secondary.driver-class-name"));
        props.setProperty("dataSource.url", environment.getRequiredProperty("spring.datasource.secondary.url"));
        props.setProperty("dataSource.user", environment.getRequiredProperty("spring.datasource.secondary.hikari.username"));
        props.setProperty("dataSource.password", environment.getRequiredProperty("spring.datasource.secondary.hikari.password"));
        props.setProperty("connectionTestQuery", environment.getRequiredProperty("spring.datasource.secondary.hikari.connection-test-query"));
        props.setProperty("minimumIdle", environment.getRequiredProperty("spring.datasource.secondary.hikari.minimum-idle"));
        props.setProperty("maximumPoolSize", environment.getRequiredProperty("spring.datasource.secondary.hikari.maximum-pool-size"));
        props.setProperty("poolName", environment.getRequiredProperty("spring.datasource.secondary.hikari.pool-name"));
        HikariConfig config = new HikariConfig(props);
        HikariDataSource ds = new HikariDataSource(config);

        return ds;

    }



}


