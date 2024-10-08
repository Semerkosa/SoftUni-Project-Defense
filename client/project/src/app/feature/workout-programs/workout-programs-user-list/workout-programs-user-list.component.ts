import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { IWorkoutProgram } from 'src/app/core/interfaces';
import { UserService } from 'src/app/core/services/user.service';

@Component({
    selector: 'app-workout-programs-user-list',
    templateUrl: './workout-programs-user-list.component.html',
    styleUrls: ['./workout-programs-user-list.component.scss']
})
export class WorkoutProgramsUserListComponent implements OnInit, OnDestroy {

    subscription: Subscription;
    workoutPrograms: IWorkoutProgram[];
    isLoaded = false;

    constructor(private userService: UserService) { }

    ngOnDestroy(): void {
        this.subscription.unsubscribe();
    }

    ngOnInit(): void {
        this.subscription = this.userService.getUserById$(this.userService.getUserId()).subscribe({
            next: userResponse => {
                console.log("userById", userResponse);

                if (userResponse.workoutPrograms?.length > 0) {
                    this.workoutPrograms = userResponse.workoutPrograms;
                }
            },
            error: err => {
                console.log(err);
            },
            complete: () => {
                this.isLoaded = true;
            }
        });
    }

}
