package com.mrp.datamigrate.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemWriterBuilder;
import org.springframework.classify.Classifier;
import org.springframework.classify.ClassifierAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.mrp.datamigrate.domain.Person;

@Configuration
public class PersonClassifierWriterConfig {

  @Bean
  public ClassifierCompositeItemWriter<Person> personClassifierWriter(
      FlatFileItemWriter<Person> invalidPersonWriter,
      JdbcBatchItemWriter<Person> validPersonWriter) {
    return new ClassifierCompositeItemWriterBuilder<Person>()
        .classifier(classifier(invalidPersonWriter, validPersonWriter))
        .build();
  }

  private Classifier<Person, ItemWriter<? super Person>> classifier(
      FlatFileItemWriter<Person> invalidPersonWriter,
      JdbcBatchItemWriter<Person> validPersonWriter) {
    return person -> {
      if (person.isValid())
        return validPersonWriter;
      else
        return invalidPersonWriter;
    };
  }



}
