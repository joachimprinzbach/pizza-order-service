package com.zuhelke.springfundamentals.pizzaorderservice.pizzaorder.controller;

import com.zuhelke.springfundamentals.pizzaorderservice.pizzaorder.domain.OrderStatus;
import java.util.List;

public class PizzaOrderDto {

    private String orderId;
    private OrderStatus orderStatus;
    private boolean isEditable;
    private boolean isCancellable;
    private boolean noMoreChangesAllowed;
    private List<PizzaOrderItemDto> orderItems;

    public PizzaOrderDto(String orderId, OrderStatus orderStatus, List<PizzaOrderItemDto> orderItems) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.orderItems = orderItems;
    }

    public PizzaOrderDto(String orderId, OrderStatus orderStatus, boolean isEditable, boolean isCancellable, boolean noMoreChangesAllowed,
        List<PizzaOrderItemDto> orderItems) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.isEditable = isEditable;
        this.isCancellable = isCancellable;
        this.noMoreChangesAllowed = noMoreChangesAllowed;
        this.orderItems = orderItems;
    }

    public String getOrderId() {
        return orderId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public boolean isEditable() {
        return isEditable;
    }

    public boolean isCancellable() {
        return isCancellable;
    }

    public boolean isNoMoreChangesAllowed() {
        return noMoreChangesAllowed;
    }

    public List<PizzaOrderItemDto> getOrderItems() {
        return orderItems;
    }
}
