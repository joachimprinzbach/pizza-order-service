import { Component, OnInit } from '@angular/core';
import {PizzaOrderService} from '../pizza-order.service';

@Component({
  selector: 'app-pizza-order-overview',
  templateUrl: './pizza-order-overview.component.html',
  styleUrls: ['./pizza-order-overview.component.scss']
})
export class PizzaOrderOverviewComponent implements OnInit {

  constructor(private pizzaOrderService: PizzaOrderService) { }

  ngOnInit() {
    console.log('hello');
    this.pizzaOrderService.getAllPizzaOrders();
  }

}
