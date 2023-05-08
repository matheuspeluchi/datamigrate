package com.mrp.datamigrate.config;

import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

public class DataSourceConfig {
  @Primary
  @Bean
  @ConfigurationProperties(prefix = "spring.datasource")
  public DataSource springDataSource() {
    return DataSourceBuilder.create().build();

  }

  @Bean
  @ConfigurationProperties(prefix = "app.datasource")
  public DataSource appDataSource() {
    return DataSourceBuilder.create().build();
  }

}
