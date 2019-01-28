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
import java.util.UUID;
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

    public PizzaOrderDto update(PizzaOrderDto pizzaOrderDto) {
        PizzaOrder pizzaOrder = pizzaOrderRepository
            .findById(UUID.fromString(pizzaOrderDto.getOrderId()))
            .orElseThrow(IllegalArgumentException::new);

        if (!pizzaOrder.isEditable()) {
            throw new IllegalStateException("Order can't be edited anymore!");
        }

        pizzaOrder.setOrderItems(pizzaOrderDto.getOrderItems().stream().map(this::mapPizzaOrderItemFromDto).collect(toList()));

        return mapToPizzaOrderDto(pizzaOrderRepository.save(pizzaOrder));
    }

    public void cancel(String id) {
        PizzaOrder pizzaOrder = pizzaOrderRepository
            .findById(UUID.fromString(id))
            .orElseThrow(IllegalArgumentException::new);

        if (!pizzaOrder.isCancellable()) {
            throw new IllegalStateException("Order can't be cancelled anymore!");
        }

        pizzaOrderRepository.delete(pizzaOrder);
    }

    private PizzaOrderDto mapToPizzaOrderDto(PizzaOrder pizzaOrder) {
        List<PizzaOrderItemDto> orderItems = pizzaOrder.getOrderItems().stream()
            .map(orderItem -> new PizzaOrderItemDto(orderItem.getName(), orderItem.getQuantity()))
            .collect(toList());
        return new PizzaOrderDto(pizzaOrder.getId().toString(),
            pizzaOrder.getOrderStatus(),
            pizzaOrder.isEditable(),
            pizzaOrder.isCancellable(),
            pizzaOrder.noMoreChangesAllowed(),
            orderItems);
    }

    private PizzaOrder mapPizzaOrderFromDto(CreatePizzaOrderDto createPizzaOrderDto) {
        return new PizzaOrder(createPizzaOrderDto.getOrderItems().stream()
            .map(this::mapPizzaOrderItemFromDto)
            .collect(toList()));
    }

    private PizzaOrderItem mapPizzaOrderItemFromDto(PizzaOrderItemDto orderItem) {
        return new PizzaOrderItem(orderItem.getName(), orderItem.getQuantity());
    }
}
