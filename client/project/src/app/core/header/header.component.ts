import { Component, OnDestroy, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit, OnDestroy {

  subscription: Subscription;
  isLogged = false;
  userFullName: string = '';

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    this.subscription = this.userService.isAuthenticated$.subscribe(loginStatus => {
      this.isLogged = loginStatus;
      this.userFullName = this.userService.getUserFullName();
    });
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  logout() {
    this.userService.logout();
    this.router.navigate(["/login"]);
  }
}
