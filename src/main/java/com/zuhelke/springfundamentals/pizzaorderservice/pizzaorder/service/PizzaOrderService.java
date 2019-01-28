package com.zuhelke.springfundamentals.pizzaorderservice.pizzaorder.service;

import static java.util.stream.Collectors.toList;

import com.zuhelke.springfundamentals.pizzaorderservice.pizzaorder.controller.CreatePizzaOrderDto;
import com.zuhelke.springfundamentals.pizzaorderservice.pizzaorder.dataaccess.InventoryService;
import com.zuhelke.springfundamentals.pizzaorderservice.pizzaorder.domain.PizzaOrderItem;
import com.zuhelke.springfundamentals.pizzaorderservice.common.exceptionhandling.ApplicationException;
import com.zuhelke.springfundamentals.pizzaorderservice.pizzaorder.controller.PizzaOrderDto;
import com.zuhelke.springfundamentals.pizzaorderservice.pizzaorder.controller.PizzaOrderItemDto;
import com.zuhelke.springfundamentals.pizzaorderservice.pizzaorder.dataaccess.PizzaOrderRepository;
import com.zuhelke.springfundamentals.pizzaorderservice.pizzaorder.domain.PizzaOrder;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PizzaOrderService {

    private final InventoryService pizzaInventoryService;
    private final PizzaOrderRepository pizzaOrderRepository;

    public PizzaOrderService(InventoryService pizzaInventoryService,
        PizzaOrderRepository pizzaOrderRepository) {
        this.pizzaInventoryService = pizzaInventoryService;
        this.pizzaOrderRepository = pizzaOrderRepository;
    }

    public PizzaOrderDto create(CreatePizzaOrderDto createPizzaOrderDto) {
        for (PizzaOrderItemDto orderItem : createPizzaOrderDto.getOrderItems()) {
            boolean isPizzaAvailable = pizzaInventoryService.isPizzaAvailable(orderItem.getName());
            if (!isPizzaAvailable) {
                throw new ApplicationException(orderItem.getName() + " is no longer available. Please choose another dish and place your order again.");
            }
        }

        PizzaOrder newPizzaOrder = pizzaOrderRepository.save(mapPizzaOrderFromDto(createPizzaOrderDto));

        return mapToPizzaOrderDto(newPizzaOrder);
    }

    public List<PizzaOrderDto> findAll() {
        return pizzaOrderRepository.findAll().stream()
            .map(this::mapToPizzaOrderDto)
            .collect(toList());
    }

    private PizzaOrderDto mapToPizzaOrderDto(PizzaOrder pizzaOrder) {
        List<PizzaOrderItemDto> orderItems = pizzaOrder.getOrderItems().stream()
            .map(orderItem -> new PizzaOrderItemDto(orderItem.getName(), orderItem.getQuantity())).collect(toList());
        return new PizzaOrderDto(pizzaOrder.getId().toString(), pizzaOrder.getOrderStatus(), orderItems);
    }

    private PizzaOrder mapPizzaOrderFromDto(CreatePizzaOrderDto createPizzaOrderDto) {
        return new PizzaOrder(createPizzaOrderDto.getOrderItems().stream().map(orderItem -> new PizzaOrderItem(orderItem.getName(), orderItem.getQuantity())).collect(toList()));
    }
}
