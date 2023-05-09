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
import com.mrp.datamigrate.domain.BankData;

@Configuration
public class BankDataReaderConfig {

  @Bean
  public FlatFileItemReader<BankData> bankDataReader() {
    return new FlatFileItemReaderBuilder<BankData>()
        .name("bankDataReader")
        .resource(new FileSystemResource("files/dados_bancarios.csv"))
        .delimited()
        .names(
            "person_id", "agency", "account", "bank", "id")
        .addComment("--")
        .targetType(BankData.class)
        .build();
  }
}
