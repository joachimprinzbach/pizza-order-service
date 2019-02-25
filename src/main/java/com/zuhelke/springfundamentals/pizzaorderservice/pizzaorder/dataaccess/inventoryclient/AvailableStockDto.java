package com.zuhelke.springfundamentals.pizzaorderservice.pizzaorder.dataaccess.inventoryclient;

public class AvailableStockDto {

  private int amount;

  public AvailableStockDto(int amount) {
    this.amount = amount;
  }

  public int getAmount() {
    return amount;
  }
}
