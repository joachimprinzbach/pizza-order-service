package com.zuhelke.springfundamentals.pizzaorderservice.pizzamenu.dataaccess;

import static org.assertj.core.api.Assertions.assertThat;

import com.zuhelke.springfundamentals.pizzaorderservice.pizzamenu.domain.PizzaMenuItem;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PizzaMenuRepositoryTest {

  @Autowired
  private TestEntityManager testEntityManager;

  @Autowired
  private PizzaMenuRepository pizzaMenuRepository;

  @Test
  public void pizzasWithSalamiAndWithout_findByNameContaining_allPizzasContainingTheWord() {
    String wordToContain = "Salami";
    PizzaMenuItem pizzaWithSalami1 = new PizzaMenuItem("100", "Pizza Salami", 15);
    PizzaMenuItem pizzaWithSalami2 = new PizzaMenuItem("300", "Pizza Salami Schinken", 19);
    PizzaMenuItem pizzaWithoutSalami = new PizzaMenuItem("200", "Pizza Schinken", 15);
    testEntityManager.persist(pizzaWithSalami1);
    testEntityManager.persist(pizzaWithoutSalami);
    testEntityManager.persist(pizzaWithSalami2);

    List<PizzaMenuItem> pizzaWithWord = pizzaMenuRepository.findByNameContaining(wordToContain);

    assertThat(pizzaWithWord).containsExactlyInAnyOrder(pizzaWithSalami1, pizzaWithSalami2);
  }
}