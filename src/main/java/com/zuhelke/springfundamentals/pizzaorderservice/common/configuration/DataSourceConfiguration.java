package com.zuhelke.springfundamentals.pizzaorderservice.common.configuration;

import javax.sql.DataSource;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.cloud.service.PooledServiceConnectorConfig.PoolConfig;
import org.springframework.cloud.service.relational.DataSourceConfig;
import org.springframework.cloud.service.relational.DataSourceConfig.ConnectionConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("cloud")
public class DataSourceConfiguration extends AbstractCloudConfig {

  @Bean
  public DataSource bla() {
    PoolConfig poolConfig = new PoolConfig(2, 10, 45000);
    return connectionFactory().dataSource(new DataSourceConfig(poolConfig, new ConnectionConfig("")));
  }

}
