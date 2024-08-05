import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { IEditProgram } from 'src/app/core/interfaces';
import { UserService } from 'src/app/core/services/user.service';
import { WorkoutProgramService } from 'src/app/core/services/workout-program.service';

@Component({
    selector: 'app-edit-workout-program',
    templateUrl: './edit-workout-program.component.html',
    styleUrls: ['./edit-workout-program.component.scss']
})
export class EditWorkoutProgramComponent implements OnInit {

    errorMessage = "";

    programId: number;
    name: string;
    price: number;
    description: string;
    details: string;

    constructor(
        private userService: UserService,
        private workoutProgramService: WorkoutProgramService,
        private activatedRoute: ActivatedRoute,
        private router: Router) { }

    ngOnInit(): void {
        if (!this.userService.isAdmin()) {
            alert("Unauthorised access!");
            this.router.navigate(['/']);
        }

        this.activatedRoute.params.subscribe(params => {
            this.programId = params['workoutProgramId'];

            this.workoutProgramService.getWorkoutProgramById$(this.programId).subscribe({
                next: workoutProgram => {
                    console.log("Workout program to be edited", workoutProgram);
                    
                    this.name = workoutProgram.name;
                    this.price = workoutProgram.price;
                    this.description = workoutProgram.description;
                    this.details = workoutProgram.details;
                },
                error: err => {
                    console.log(err);
                    this.router.navigate(['/not-found']);              
                }
            });
        });
    }

    saveChanges() {
        if (this.name.length === 0 || this.description.length === 0 || this.details.length === 0) {
            this.errorMessage = "Fill in all the fields!";
            return;
        } else if (this.price < 1) {
            this.errorMessage = "Min value for price is 1";
            return;
        }

        const program: IEditProgram = {
            name: this.name,
            price: this.price,
            description: this.description,
            details: this.details
        }        

        console.log("Fields to be edited", program);
        
        this.workoutProgramService.editWorkoutProgramById$(this.programId, program).subscribe({
            next: editedProgram => {
                console.log("Edited program", editedProgram);

                this.router.navigate(['/workout-programs']);
            }, 
            error: err => {
                console.log(err);
                this.errorMessage = err;                
            }
        });
    }
}
