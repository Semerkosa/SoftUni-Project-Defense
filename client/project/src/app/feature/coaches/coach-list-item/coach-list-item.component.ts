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
        const userId = +this.userService.getUserId();

        this.canHire = !this.coach.clients.includes(userId); // if the user is within the clients of the coach, we can't hire him again
    }

    hireCoach(coach: ICoach) {
        console.log("Coach to be hired", coach);

        const userId = +this.userService.getUserId();

        this.userService.getUserById$(userId).subscribe({
            next: userResponse => {
                console.log(userResponse);

                if (userResponse.coach && Object.keys(userResponse.coach).length > 0) {
                    alert('You have already hired a coach. Please cancel him first.');
                    return;
                }

                this.userService.editCoachForGivenUser$(userId, coach).subscribe({
                    next: editedUser => {
                        console.log("Edited user", editedUser);

                        const coachId = coach.id;

                        let coachClients = coach.clients;
                        coachClients.push(userId);

                        this.coachService.editUsersForGivenCoach(coachId, coachClients).subscribe({
                            next: editedCoach => {
                                console.log("Edited coach", editedCoach);
                                this.canHire = false;
                            },
                            error: err => {
                                console.log(err);
                                alert("Something went wrong... Please re-login.");
                            }
                        });
                    },
                    error: err => {
                        console.log(err);
                        alert("Something went wrong... Please re-login.");
                    }
                });
            },
            error: err => {
                console.log(err);
                alert("Something went wrong... Please re-login.");
            }
        });


    }

    cancelCoach(coach: ICoach) {
        console.log("Coach to be cancelled", coach);

        const userId = +this.userService.getUserId();
        const coachId = coach.id;

        this.userService.editCoachForGivenUser$(userId).subscribe({
            next: editedUser => {
                console.log("Edited user", editedUser);
            },
            error: err => {
                console.log(err);
            }
        });

        const coachClients = coach.clients.filter(id => id != userId);

        this.coachService.editUsersForGivenCoach(coachId, coachClients).subscribe({
            next: editedCoach => {
                console.log("Edited coach", editedCoach);
                this.canHire = true;
            },
            error: err => {
                console.log(err);
            }
        });
    }
}
