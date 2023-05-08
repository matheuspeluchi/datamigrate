package com.mrp.datamigrate.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BankData {
  private int id;
  private int personId;
  private int agency;
  private int account;
  private int bank;
}
