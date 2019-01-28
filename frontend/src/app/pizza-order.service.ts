import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PizzaOrderService {

  constructor(private httpClient: HttpClient) { }

  getAllPizzaOrders() {
    this.httpClient.get('/pizza-orders').subscribe(pizzaOrders => {
      console.log(pizzaOrders);
    })
  }
}
