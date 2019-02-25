package com.zuhelke.springfundamentals.pizzaorderservice.pizzaorder.dataaccess.inventoryclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "inventory-api", qualifier = "inventory-api", url = "${pizza.inventory.url}")
public interface InventoryClient {

    @GetMapping("pizza-inventory")
    AvailableStockDto getAvailability(@RequestParam String pizzaName);
}
