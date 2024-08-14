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
    return this.http.get<ICoach>(`${serverUrl}/${id}`, this.userService.getUpdatedHttpOptions());
  }

  hireCoach$(userId: string, coachId: string): Observable<any> {
    const url = `${serverUrl}/hire?userId=${userId}&coachId=${coachId}`;
		return this.http.post<any>(url, null, this.userService.getUpdatedHttpOptions());
  }

  cancelCoach$(userId: string, coachId: string): Observable<any> {
    const url = `${serverUrl}/cancel?userId=${userId}&coachId=${coachId}`;
		return this.http.post<any>(url, null, this.userService.getUpdatedHttpOptions());
  }
}
