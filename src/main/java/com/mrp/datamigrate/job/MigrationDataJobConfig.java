package com.mrp.datamigrate.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
public class MigrationDataJobConfig {

  @Bean
  public Job migrationDataJob(JobRepository repository,
      @Qualifier("personStep") Step personStep, @Qualifier("bankDataStep") Step bankDataStep) {
    return new JobBuilder("migrationDataJob", repository)
        .start(parallelSteps(personStep, bankDataStep))
        .end()
        .incrementer(new RunIdIncrementer())
        .build();
  }

  private Flow parallelSteps(Step personStep, Step bankDataStep) {
    Flow migrateBankData = new FlowBuilder<Flow>("bankDataMigrateFlow")
        .start(bankDataStep)
        .build();

    return new FlowBuilder<Flow>("parallelStepsFlow")
        .start(personStep)
        .split(new SimpleAsyncTaskExecutor())
        .add(migrateBankData)
        .build();


  }

}
