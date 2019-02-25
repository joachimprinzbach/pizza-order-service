package com.zuhelke.springfundamentals.pizzaorderservice.pizzaorder.dataaccess;

import com.zuhelke.springfundamentals.pizzaorderservice.pizzaorder.dataaccess.inventoryclient.AvailableStockDto;
import com.zuhelke.springfundamentals.pizzaorderservice.pizzaorder.dataaccess.inventoryclient.InventoryClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;

@ConditionalOnExpression("${pizza.inventory.mock:false}==false")
@Service
public class RemotePizzaInventoryService implements PizzaInventoryService {

  private final PizzaInventoryProperties pizzaInventoryProperties;
  private final InventoryClient inventoryClient;

  public RemotePizzaInventoryService(PizzaInventoryProperties pizzaInventoryProperties, InventoryClient inventoryClient) {
    this.pizzaInventoryProperties = pizzaInventoryProperties;
    this.inventoryClient = inventoryClient;
  }

  @Override
  public boolean isPizzaAvailable(String pizzaName) {

    AvailableStockDto lala = inventoryClient.getAvailability("lala");

    System.out.println(lala);

    try {
      Thread.sleep(4000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }


    return pizzaName.hashCode() % 3 != 0;
  }
}
