import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { ICoach } from 'src/app/core/interfaces';
import { CoachService } from 'src/app/core/services/coach.service';

@Component({
  selector: 'app-coach-list',
  templateUrl: './coach-list.component.html',
  styleUrls: ['./coach-list.component.scss']
})
export class CoachListComponent implements OnInit, OnDestroy {

  subscription: Subscription;
  isLoaded: boolean = false;
  coaches: ICoach[];

  constructor(private coachService: CoachService) { }
  
  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  ngOnInit(): void {
    this.subscription = this.coachService.getCoaches$().subscribe({
      next: (coaches) => {
        this.isLoaded = true;

        console.log(coaches);
        this.coaches = coaches;
      }, 
      error: (err) => {
        this.isLoaded = true;
        console.log(err);
      }
    });
  }

}
