import { RouterModule, Routes } from "@angular/router";
import { WorkoutProgramsListComponent } from "./workout-programs-list/workout-programs-list.component";
import { WorkoutProgramReviewPageComponent } from "./workout-program-review-page/workout-program-review-page.component";
import { WorkoutProgramsUserListComponent } from "./workout-programs-user-list/workout-programs-user-list.component";
import { EditWorkoutProgramComponent } from "./edit-workout-program/edit-workout-program.component";

const routes: Routes = [
    {
        path: 'workout-programs',
        component: WorkoutProgramsListComponent,
    },
    {
        path: 'workout-programs/user/programs',
        component: WorkoutProgramsUserListComponent,
    },
    {
        path: 'workout-programs/:workoutProgramId',
        component: WorkoutProgramReviewPageComponent,
    },
    {
        path: 'workout-programs/edit/:workoutProgramId',
        component: EditWorkoutProgramComponent
    }
];

export const WorkoutProgramsRoutingModule = RouterModule.forChild(routes);