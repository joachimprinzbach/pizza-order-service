package com.zuhelke.springfundamentals.pizzaorderservice.common.testdata;

import static java.util.Arrays.asList;

import com.zuhelke.springfundamentals.pizzaorderservice.pizzaorder.domain.OrderStatus;
import com.zuhelke.springfundamentals.pizzaorderservice.pizzaorder.domain.PizzaOrderItem;
import com.zuhelke.springfundamentals.pizzaorderservice.pizzaorder.dataaccess.PizzaOrderRepository;
import com.zuhelke.springfundamentals.pizzaorderservice.pizzaorder.domain.PizzaOrder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"default", "dev"})
public class TestDataGenerator implements CommandLineRunner {

  private final PizzaOrderRepository pizzaOrderRepository;

  public TestDataGenerator(PizzaOrderRepository pizzaOrderRepository) {
    this.pizzaOrderRepository = pizzaOrderRepository;
  }

  @Override
  public void run(String... args) {
    pizzaOrderRepository.save(
        new PizzaOrder(asList(
            new PizzaOrderItem("Pizza Salami", 2),
            new PizzaOrderItem("Pizza Margherita", 2)
        ))
    );
    pizzaOrderRepository.save(
        new PizzaOrder(OrderStatus.IN_PREPARATION,
            asList(
                new PizzaOrderItem("Pizza Schinken", 1)
            ))
    );
    pizzaOrderRepository.save(
        new PizzaOrder(OrderStatus.IN_DELIVERY,
            asList(
                new PizzaOrderItem("Pizza Salami", 3),
                new PizzaOrderItem("Pizza Tonno", 1),
                new PizzaOrderItem("Pizza Calzone", 2)
            ))
    );
    pizzaOrderRepository.save(
        new PizzaOrder(OrderStatus.COMPLETED,
            asList(
                new PizzaOrderItem("Pizza Quattro Formaggi", 2)
            ))
    );
  }
}
