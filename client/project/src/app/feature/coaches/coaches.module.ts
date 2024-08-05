import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CoachListComponent } from './coach-list/coach-list.component';
import { CoachReviewPageComponent } from './coach-review-page/coach-review-page.component';
import { CoachesRoutingModule } from './coaches-routing.module';
import { CoachListItemComponent } from './coach-list-item/coach-list-item.component';



@NgModule({
  declarations: [
    CoachListComponent,
    CoachReviewPageComponent,
    CoachListItemComponent
  ],
  imports: [
    CommonModule,
    CoachesRoutingModule,
  ]
})
export class CoachesModule { 
  
}
