import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { WorkoutProgramsListComponent } from './workout-programs-list/workout-programs-list.component';
import { WorkoutProgramReviewPageComponent } from './workout-program-review-page/workout-program-review-page.component';
import { WorkoutProgramsRoutingModule } from './workout-programs-routing.module';
import { WorkoutProgramsUserListComponent } from './workout-programs-user-list/workout-programs-user-list.component';
import { WorkoutProgramListItemComponent } from './workout-program-list-item/workout-program-list-item.component';
import { AddWorkoutProgramComponent } from './add-workout-program/add-workout-program.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { EditWorkoutProgramComponent } from './edit-workout-program/edit-workout-program.component';



@NgModule({
  declarations: [
    WorkoutProgramsListComponent,
    WorkoutProgramReviewPageComponent,
    WorkoutProgramsUserListComponent,
    WorkoutProgramListItemComponent,
    AddWorkoutProgramComponent,
    EditWorkoutProgramComponent,
  ],
  imports: [
    CommonModule,
    WorkoutProgramsRoutingModule,
    ReactiveFormsModule,
    FormsModule
  ]
})
export class WorkoutProgramsModule { }
