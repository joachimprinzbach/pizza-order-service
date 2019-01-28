package com.zuhelke.springfundamentals.pizzaorderservice.pizzaorder.controller;

import com.zuhelke.springfundamentals.pizzaorderservice.pizzaorder.domain.OrderStatus;
import java.util.List;

public class PizzaOrderDto {

    private String orderId;
    private OrderStatus orderStatus;
    private List<PizzaOrderItemDto> orderItems;

    public PizzaOrderDto(String orderId, OrderStatus orderStatus, List<PizzaOrderItemDto> orderItems) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.orderItems = orderItems;
    }

    public String getOrderId() {
        return orderId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public List<PizzaOrderItemDto> getOrderItems() {
        return orderItems;
    }
}
