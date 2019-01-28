package com.zuhelke.springfundamentals.pizzaorderservice.pizzaorder.controller;

import static org.springframework.http.HttpStatus.NO_CONTENT;

import com.zuhelke.springfundamentals.pizzaorderservice.pizzaorder.service.PizzaOrderService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PizzaOrderController {

    private final PizzaOrderService pizzaOrderService;

    public PizzaOrderController(PizzaOrderService pizzaOrderService) {
        this.pizzaOrderService = pizzaOrderService;
    }

    @PostMapping("/pizza-orders")
    public PizzaOrderDto create(@RequestBody CreatePizzaOrderDto createPizzaOrderDto) {
        return pizzaOrderService.create(createPizzaOrderDto);
    }

    @GetMapping("/pizza-orders")
    public List<PizzaOrderDto> getAll() {
        return pizzaOrderService.findAll();
    }

    @PutMapping("/pizza-orders/{id}")
    public PizzaOrderDto update(@PathVariable String id, @RequestBody PizzaOrderDto pizzaOrderDto) {
        if (!id.equals(pizzaOrderDto.getOrderId())) {
            throw new IllegalStateException();
        }
        return pizzaOrderService.update(pizzaOrderDto);
    }

    @DeleteMapping("/pizza-orders/{id}")
    @ResponseStatus(NO_CONTENT)
    public void cancel(@PathVariable String id) {
        this.pizzaOrderService.cancel(id);
    }

}
