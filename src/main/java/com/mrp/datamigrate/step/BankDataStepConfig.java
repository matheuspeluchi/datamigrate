package com.mrp.datamigrate.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import com.mrp.datamigrate.domain.BankData;

@Configuration
public class BankDataStepConfig {

  @Bean
  public Step bankDataStep(JobRepository jobRepository,
      PlatformTransactionManager transactionManager,
      ItemReader<BankData> bankDataReader,
      ItemWriter<BankData> bankDataWriter) {
    return new StepBuilder("bankDataStep", jobRepository)
        .<BankData, BankData>chunk(1, transactionManager)
        .reader(bankDataReader)
        .writer(bankDataWriter)
        .build();
  }
}
