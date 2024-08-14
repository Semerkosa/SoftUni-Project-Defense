import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { UserService } from './user.service';

const serverUrl = `${environment.apiUrl}/reviews`;

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  constructor(private http: HttpClient, private userService: UserService) { }

  addReview$(review: String): Observable<any> {
    const body = {
      review
    };

		return this.http.post<any>(`${serverUrl}/post`, null, this.userService.getUpdatedHttpOptions());
  }
}
