package com.mrp.datamigrate.domain;

import java.util.Date;
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
}
