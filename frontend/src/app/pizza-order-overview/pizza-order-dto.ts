import {PizzaOrderStatus} from './pizza-order-status';
import {PizzaOrderItemDto} from './pizza-order-item';

export interface PizzaOrderDto {
  orderId: String;
  orderStatus: PizzaOrderStatus;
  editable: boolean;
  cancellable: boolean;
  noMoreChangesAllowed: boolean;
  orderItems: PizzaOrderItemDto[];
}
