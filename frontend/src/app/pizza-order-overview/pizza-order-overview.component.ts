import {Component, OnInit} from '@angular/core';
import {PizzaOrderService} from './pizza-order.service';
import {PizzaOrderDto} from './pizza-order-dto';
import {PizzaOrderStatus} from './pizza-order-status';

@Component({
  selector: 'app-pizza-order-overview',
  templateUrl: './pizza-order-overview.component.html',
  styleUrls: ['./pizza-order-overview.component.scss']
})
export class PizzaOrderOverviewComponent implements OnInit {

  pizzaOrders: PizzaOrderDto[];

  constructor(private pizzaOrderService: PizzaOrderService) {
  }

  ngOnInit() {
    this.pizzaOrderService.getAllPizzaOrders().subscribe(pizzaOrders => this.pizzaOrders = pizzaOrders);
  }

  cancelPizzaOrder(pizzaOrder: PizzaOrderDto) {
    this.pizzaOrderService.cancelPizzaOrder(pizzaOrder).subscribe(() => {
      this.pizzaOrderService.getAllPizzaOrders().subscribe(pizzaOrders => this.pizzaOrders = pizzaOrders);
    });
  }
}
