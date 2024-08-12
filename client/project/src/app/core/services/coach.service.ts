import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { ICoach } from '../interfaces';
import { Observable } from 'rxjs';
import { UserService } from './user.service';

const serverUrl = `${environment.apiUrl}/coaches`;

@Injectable({
  providedIn: 'root'
})
export class CoachService {

  constructor(private http: HttpClient, private userService: UserService) { }

  getCoaches$(): Observable<ICoach[]> {
    return this.http.get<ICoach[]>(`${serverUrl}/all`, this.userService.getUpdatedHttpOptions());
  }

  getCoachById$(id: string): Observable<ICoach> {
    return this.http.get<ICoach>(`${serverUrl}/${id}`);
  }

  editUsersForGivenCoach(coachId: string, userIds: string[]): Observable<ICoach> {
    const body = {
      "clients": userIds
    }

    return this.http.patch<ICoach>(`${serverUrl}/${coachId}`, body, this.userService.getUpdatedHttpOptions());
  }
}
