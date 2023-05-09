package com.mrp.datamigrate.domain;

import java.util.Date;
import org.apache.logging.log4j.util.Strings;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Person {
  private int id;
  private String name;
  private String email;
  private Date birthDate;
  private int age;

  public boolean isValid() {
    return !Strings.isBlank(name) && !Strings.isBlank(email) && birthDate != null;
  }
}
