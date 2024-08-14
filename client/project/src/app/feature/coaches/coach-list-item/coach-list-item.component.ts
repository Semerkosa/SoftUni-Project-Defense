import { Component, Input, OnInit } from '@angular/core';
import { ICoach } from 'src/app/core/interfaces';
import { CoachService } from 'src/app/core/services/coach.service';
import { UserService } from 'src/app/core/services/user.service';

@Component({
    selector: 'app-coach-list-item',
    templateUrl: './coach-list-item.component.html',
    styleUrls: ['./coach-list-item.component.scss']
})
export class CoachListItemComponent implements OnInit {

    canHire = true;

    @Input() coach: ICoach;

    constructor(
        private coachService: CoachService,
        private userService: UserService) { }

    ngOnInit(): void {
        const userId = this.userService.getUserId();

        this.canHire = !this.coach.clients.includes(userId); // if the user is within the clients of the coach, we can't hire him again
    }

    hireCoach(coach: ICoach) {
        console.log("Coach to be hired", coach);

        const userId = this.userService.getUserId();
        const coachId = coach.id;

        this.userService.getUserById$(userId).subscribe({
            next: userResponse => {
                console.log(userResponse);

                if (userResponse.coach) {
                    alert('You have already hired a coach. Please cancel him first.');
                    return;
                }

                this.coachService.hireCoach$(userId, coachId).subscribe({
                    next: response => {
                        console.log("Hired coach? ", response);

                        this.canHire = false;
                    },
                    error: err => {
                        console.log(err);
                        alert("Invalid reference!")
                    }
                });
            },
            error: err => {
                console.log(err);
                alert("Invalid reference!")
            }
        });


    }

    cancelCoach(coach: ICoach) {
        console.log("Coach to be cancelled", coach);

        const userId = this.userService.getUserId();
        const coachId = coach.id;

        this.coachService.cancelCoach$(userId, coachId).subscribe({
            next: response => {
                console.log("Cancelled coach? ", response);

                this.canHire = true;
            },
            error: err => {
                console.log(err);
                alert("Invalid reference!");
            }
        });
    }
}
