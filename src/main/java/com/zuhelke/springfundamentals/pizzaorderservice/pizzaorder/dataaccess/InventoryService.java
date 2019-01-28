package com.zuhelke.springfundamentals.pizzaorderservice.pizzaorder.dataaccess;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

  public boolean isPizzaAvailable(String pizzaName) {
    return true;
  }
}
