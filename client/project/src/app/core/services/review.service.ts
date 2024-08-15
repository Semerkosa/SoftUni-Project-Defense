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

  addReviewForWorkoutProgram$(review: string, workoutProgramId: string): Observable<any> {
    const body = {
      review,
      entityId: workoutProgramId,
    };

    console.log(body);
    

		return this.http.post<any>(`${serverUrl}/post/workout-program`, body, this.userService.getUpdatedHttpOptions());
  }
}
