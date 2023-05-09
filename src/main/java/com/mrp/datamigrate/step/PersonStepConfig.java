package com.mrp.datamigrate.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
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
      ClassifierCompositeItemWriter<Person> personClassifierWriter,
      FlatFileItemWriter<Person> invalidPersonWriter) {
    return new StepBuilder("personStep", jobRepository)
        .<Person, Person>chunk(10000, transactionManager)
        .reader(filePeopleReader)
        .writer(personClassifierWriter)
        .stream(invalidPersonWriter)
        .build();
  }
}
