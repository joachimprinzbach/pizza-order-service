package com.zuhelke.springfundamentals.pizzaorderservice.pizzamenu.controller;

import com.zuhelke.springfundamentals.pizzaorderservice.common.exceptionhandling.ResourceNotFoundException;
import com.zuhelke.springfundamentals.pizzaorderservice.common.testdata.TestDataGenerator;
import com.zuhelke.springfundamentals.pizzaorderservice.pizzamenu.service.PizzaMenuService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PizzaMenuController {

    private static final Logger logger = LoggerFactory.getLogger(TestDataGenerator.class);

    private final PizzaMenuService pizzaMenuService;

    public PizzaMenuController(PizzaMenuService pizzaMenuService) {
        this.pizzaMenuService = pizzaMenuService;
    }

    @PostMapping("pizzas")
    public PizzaMenuItemDto create(@RequestBody PizzaMenuItemDto pizzaMenuItem) {
        return pizzaMenuService.create(pizzaMenuItem);
    }

    @GetMapping("pizzas")
    public List<PizzaMenuItemDto> getAll(@RequestParam Optional<String> nameContains) {
        if (nameContains.isPresent()) {
            return pizzaMenuService.findByNameContaining(nameContains.get());
        }
        return pizzaMenuService.findAll();
    }

    @GetMapping("pizzas/{id}")
    public PizzaMenuItemDto getById(@PathVariable String id) {
        return pizzaMenuService.findById(id)
            .orElseThrow(ResourceNotFoundException::new);
    }
}
