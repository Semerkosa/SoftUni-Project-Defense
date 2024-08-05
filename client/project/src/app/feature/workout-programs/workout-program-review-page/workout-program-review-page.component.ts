import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { IWorkoutProgram } from 'src/app/core/interfaces';
import { WorkoutProgramService } from 'src/app/core/services/workout-program.service';

@Component({
  selector: 'app-workout-program-review-page',
  templateUrl: './workout-program-review-page.component.html',
  styleUrls: ['./workout-program-review-page.component.scss']
})
export class WorkoutProgramReviewPageComponent implements OnInit, OnDestroy {

  subscription: Subscription;
  isLoaded = false;

  workoutProgram: IWorkoutProgram;

  constructor(
    private workoutProgramService: WorkoutProgramService, 
    private activatedRoute: ActivatedRoute,
    private router: Router) { }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  ngOnInit(): void {
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

}
