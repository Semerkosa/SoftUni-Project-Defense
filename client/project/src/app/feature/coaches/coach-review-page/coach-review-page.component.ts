import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { ICoach } from 'src/app/core/interfaces';
import { CoachService } from 'src/app/core/services/coach.service';

@Component({
  selector: 'app-coach-review-page',
  templateUrl: './coach-review-page.component.html',
  styleUrls: ['./coach-review-page.component.scss']
})
export class CoachReviewPageComponent implements OnInit, OnDestroy {

  subscription: Subscription;
  isLoaded: boolean = false;
  coach: ICoach;

  constructor(private coachService: CoachService, private activatedRoute: ActivatedRoute, private router: Router) { }
  
  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      const coachId = params['coachId'];

      this.subscription = this.coachService.getCoachById$(coachId).subscribe({
        next: (coach) => {
          this.isLoaded = true;
  
          console.log(coach);
          this.coach = coach;
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
