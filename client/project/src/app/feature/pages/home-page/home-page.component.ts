import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { UserService } from 'src/app/core/services/user.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})
export class HomePageComponent implements OnInit {

  subscription: Subscription;
  isLogged = false;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.subscription = this.userService.isAuthenticated$.subscribe(loginStatus => {
      this.isLogged = loginStatus;
    })
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

}
