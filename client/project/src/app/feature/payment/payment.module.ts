import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CardDetailsComponent } from './card-details/card-details.component';
import { PaymentRoutingModule } from './payment-routing.module';



@NgModule({
  declarations: [
    CardDetailsComponent
  ],
  imports: [
    CommonModule,
    PaymentRoutingModule
  ]
})
export class PaymentModule { }
