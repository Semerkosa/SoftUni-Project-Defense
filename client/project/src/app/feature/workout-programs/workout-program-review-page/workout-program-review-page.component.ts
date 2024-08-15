import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { IWorkoutProgram } from 'src/app/core/interfaces';
import { UserService } from 'src/app/core/services/user.service';
import { WorkoutProgramService } from 'src/app/core/services/workout-program.service';

@Component({
  selector: 'app-workout-program-review-page',
  templateUrl: './workout-program-review-page.component.html',
  styleUrls: ['./workout-program-review-page.component.scss']
})
export class WorkoutProgramReviewPageComponent implements OnInit, OnDestroy {

  subscription: Subscription;
  isLoaded = false;
  isLoggedIn = false;
  shouldShowReviewForm = false;

  workoutProgram: IWorkoutProgram;

  constructor(
    private workoutProgramService: WorkoutProgramService, 
    private userService: UserService, 
    private activatedRoute: ActivatedRoute,
    private router: Router) { }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  ngOnInit(): void {
    this.isLoggedIn = this.userService.isLoggedIn();

    this.activatedRoute.params.subscribe(params => {
      const programId = params["workoutProgramId"];

      this.subscription = this.workoutProgramService.getWorkoutProgramById$(programId).subscribe({
        next: program => {
          this.isLoaded = true;

          console.log(program);
          this.workoutProgram = program;
        },
        error: (err) => {
          this.isLoaded = true;
          console.log(err);

          this.router.navigate(['not-found']);
        }
      });
    });
  }

  toggleReviewForm() {
    this.shouldShowReviewForm = !this.shouldShowReviewForm;
  }

  addReviewToList(review: string) {
    console.log("Current reviews list: ", this.workoutProgram.reviews);
    this.workoutProgram.reviews.push(review);
    console.log("New reviews list: ", this.workoutProgram.reviews);
    
    this.toggleReviewForm();
  }
}
