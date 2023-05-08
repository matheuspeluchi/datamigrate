package com.mrp.datamigrate.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MigrationDataJobConfig {

  @Bean
  public Job migrationDataJob(JobRepository repository,
      Step personStep, Step bankDataStep) {
    return new JobBuilder("migrationDataJob", repository)
        .start(personStep)
        .next(bankDataStep)
        .incrementer(new RunIdIncrementer())
        .build();


  }

}
