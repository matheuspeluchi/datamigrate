package com.mrp.datamigrate.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import com.mrp.datamigrate.domain.Person;


@Configuration
public class PersonStepConfig {

  @Bean
  public Step personStep(JobRepository jobRepository,
      PlatformTransactionManager transactionManager,
      ItemReader<Person> filePeopleReader,
      ItemWriter<Person> databasePeopleWriter) {
    return new StepBuilder("personStep", jobRepository)
        .<Person, Person>chunk(1, transactionManager)
        .reader(filePeopleReader)
        .writer(databasePeopleWriter)
        .build();
  }
}
