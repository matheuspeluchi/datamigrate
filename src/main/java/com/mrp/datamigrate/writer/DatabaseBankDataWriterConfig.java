package com.mrp.datamigrate.writer;

import javax.sql.DataSource;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.mrp.datamigrate.domain.BankData;

@Configuration
public class DatabaseBankDataWriterConfig {

  @Bean
  public JdbcBatchItemWriter<BankData> databaseBankDataWriter(
      @Qualifier("appDataSource") DataSource appDatasource) {
    return new JdbcBatchItemWriterBuilder<BankData>()
        .dataSource(appDatasource)
        .sql(
            "INSERT INTO bank_data (id, person_id, agency, account, bank) values (:id, :personId, :agency, :account, :bank)")
        .beanMapped()
        .build();

  }
}
