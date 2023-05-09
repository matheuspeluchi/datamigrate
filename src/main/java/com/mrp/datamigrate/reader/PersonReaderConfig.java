package com.mrp.datamigrate.reader;

import java.util.Date;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.validation.BindException;
import com.mrp.datamigrate.domain.Person;

@Configuration
public class PersonReaderConfig {

  @Bean
  public FlatFileItemReader<Person> personReader() {
    return new FlatFileItemReaderBuilder<Person>()
        .name("personReader")
        .resource(new FileSystemResource("files/pessoas.csv"))
        .delimited()
        .names("name", "email", "birthDate", "age", "id")
        .addComment("--")
        .fieldSetMapper(fieldSetMapper())
        .build();
  }

  private FieldSetMapper<Person> fieldSetMapper() {
    return new FieldSetMapper<Person>() {

      @Override
      public Person mapFieldSet(FieldSet fieldSet) throws BindException {
        Person person = new Person();
        person.setName(fieldSet.readString("name"));
        person.setEmail(fieldSet.readString("email"));
        person.setBirthDate(
            new Date(fieldSet.readDate("birthDate", "yyyy-MM-dd HH:mm:ss").getTime()));
        person.setAge(fieldSet.readInt("age"));
        person.setId(fieldSet.readInt("id"));
        return person;
      }

    };

  }

}
