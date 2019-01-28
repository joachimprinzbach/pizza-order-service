package com.zuhelke.springfundamentals.pizzaorderservice.pizzaorder.domain;

import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class PizzaOrder {

  @Id
  @GeneratedValue
  private UUID id;

  private OrderStatus orderStatus;

  @OneToMany(cascade = CascadeType.ALL)
  private List<PizzaOrderItem> orderItems;

  private PizzaOrder() {
  }

  public PizzaOrder(List<PizzaOrderItem> orderItems) {
    this.orderStatus = OrderStatus.OPEN;
    this.orderItems = orderItems;
  }

  public PizzaOrder(OrderStatus orderStatus, List<PizzaOrderItem> orderItems) {
    this.orderStatus = orderStatus;
    this.orderItems = orderItems;
  }

  public UUID getId() {
    return id;
  }

  public OrderStatus getOrderStatus() {
    return orderStatus;
  }

  public List<PizzaOrderItem> getOrderItems() {
    return orderItems;
  }

  public void setOrderItems(List<PizzaOrderItem> orderItems) {
    this.orderItems = orderItems;
  }
}
