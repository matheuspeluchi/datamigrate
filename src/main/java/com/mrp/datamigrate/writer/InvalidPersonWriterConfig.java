package com.mrp.datamigrate.writer;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import com.mrp.datamigrate.domain.Person;

@Configuration
public class InvalidPersonWriterConfig {

  @Bean
  public FlatFileItemWriter<Person> invalidPersonWriter() {
    return new FlatFileItemWriterBuilder<Person>()
        .name("invalidPersonWriter")
        .resource(new FileSystemResource("files/invalid_person.csv"))
        .delimited()
        .names("id")
        .build();

  }

}
