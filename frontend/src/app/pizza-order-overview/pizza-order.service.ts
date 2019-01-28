import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {PizzaOrderDto} from './pizza-order-dto';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PizzaOrderService {

  constructor(private httpClient: HttpClient) { }

  getAllPizzaOrders(): Observable<PizzaOrderDto[]>{
    return this.httpClient.get<PizzaOrderDto[]>('/pizza-orders');
  }

  cancelPizzaOrder(pizzaOrder: PizzaOrderDto) {
    return this.httpClient.delete('/pizza-orders/' + pizzaOrder.orderId);
  }
}
