import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { IWorkoutProgram } from 'src/app/core/interfaces';
import { UserService } from 'src/app/core/services/user.service';
import { WorkoutProgramService } from 'src/app/core/services/workout-program.service';

@Component({
	selector: 'app-workout-programs-list',
	templateUrl: './workout-programs-list.component.html',
	styleUrls: ['./workout-programs-list.component.scss']
})
export class WorkoutProgramsListComponent implements OnInit, OnDestroy {

	subscription: Subscription;
	isLoaded = false;
	workoutPrograms: IWorkoutProgram[];
	isAdmin = false;
	shouldShowCreateForm = false;

	constructor(
		private workoutProgramService: WorkoutProgramService,
		private userService: UserService) { }

	ngOnDestroy(): void {
		this.subscription.unsubscribe();
	}

	ngOnInit(): void {
		this.isAdmin = this.userService.isAdmin();

		this.subscription = this.workoutProgramService.getWorkoutPrograms$().subscribe({
			next: (programs) => {
				this.isLoaded = true;

				console.log(programs);
				this.workoutPrograms = programs;
			},
			error: (err) => {
				this.isLoaded = true;
				console.log(err);
			}
		});
	}

	toggleCreateForm() {
		this.shouldShowCreateForm = !this.shouldShowCreateForm;
	}

	addWorkoutProgramToList(program: IWorkoutProgram) {
		this.workoutPrograms.push(program);
		console.log("New programs list", this.workoutPrograms);
		
		this.toggleCreateForm();
	}

	removeWorkoutProgramFromList(programId: number) {
		this.workoutPrograms = this.workoutPrograms.filter(p => p.id != programId);
		console.log("New programs list", this.workoutPrograms);
	}
}
