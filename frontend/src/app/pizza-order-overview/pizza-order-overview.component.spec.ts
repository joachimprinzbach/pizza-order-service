import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PizzaOrderOverviewComponent } from './pizza-order-overview.component';

describe('PizzaOrderOverviewComponent', () => {
  let component: PizzaOrderOverviewComponent;
  let fixture: ComponentFixture<PizzaOrderOverviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PizzaOrderOverviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PizzaOrderOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
