import { Component, EventEmitter, Input, OnChanges, Output } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { IWorkoutProgram } from 'src/app/core/interfaces';
import { UserService } from 'src/app/core/services/user.service';
import { WorkoutProgramService } from 'src/app/core/services/workout-program.service';

@Component({
    selector: 'app-workout-program-list-item',
    templateUrl: './workout-program-list-item.component.html',
    styleUrls: ['./workout-program-list-item.component.scss']
})
export class WorkoutProgramListItemComponent implements OnChanges {

    isLoggedIn = false;
    isAdmin = false;
    canPurchase = true;
    errorMessage = "";

    constructor(
        private userService: UserService,
        private workoutProgramService: WorkoutProgramService,
        private router: Router) { }

    @Input() program: IWorkoutProgram;
    @Output() onProgramDelete = new EventEmitter<string>();

    ngOnChanges(): void {
        console.log("Program", this.program);
        
        this.isAdmin = this.userService.isAdmin();
        this.isLoggedIn = this.userService.isLoggedIn();

        const userId = this.userService.getUserId();

        if (userId) {
            this.canPurchase = !this.program.customers.includes(userId); // if id is included - we return false
        }
    }

    showDetails() {
        if (!this.isLoggedIn) {
            alert("To view more details, please login.")
            this.router.navigate(['/login']);
            return;
        } 

        this.router.navigate(['/workout-programs', this.program.id]);
    }

    purchaseProgram(event: Event, program: IWorkoutProgram) {
        if (!this.isLoggedIn) {
            alert("To buy a program, please login.")
            this.router.navigate(['/login']);
            return;
        }

        const btn = event.target as HTMLElement;
        const programId = program.id;
        const userId = this.userService.getUserId();

        this.workoutProgramService.purchaseProgram$(userId, programId).subscribe({
            next: response => {
                console.log(response);                
    
                btn.textContent = 'Purchased';
                btn.style.backgroundColor = 'darkGreen';
                btn.style.color = 'white';
                btn.setAttribute('disabled', '');
            },
            error: err => {
                console.log(err);
            }
        });
    }

    editProgram() {
        this.router.navigate(["/workout-programs/edit", this.program.id]);
    }

    deleteProgram() {
        if (confirm("Do you really want to delete this Workout Program? This action cannot be undone!")) {
            const programId = this.program.id;

            this.workoutProgramService.deleteWorkoutProgramById$(programId).subscribe({
                next: deletedProgram => {
                    console.log("Deleted the program with id ", programId);
                    console.log(deletedProgram);

                    this.onProgramDelete.emit(programId);
                },
                error: err => {
                    console.log("Failed to delete the program with id ", programId);
                    console.log(err);
                    
                }
            });
        }

    }

    // shouldShowErrorForControl(
    // 	controlName: string,
    // 	sourceGroup: FormGroup = this.editProgramFormGroup
    // ) {    
    // 	return (
    // 		sourceGroup.controls[controlName].touched &&
    // 		sourceGroup.controls[controlName].invalid
    // 	);
    // }
}
