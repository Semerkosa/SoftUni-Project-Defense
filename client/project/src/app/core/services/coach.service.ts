import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { ICoach } from '../interfaces';
import { Observable } from 'rxjs';

const serverUrl = `${environment.apiUrl}/coaches`;

@Injectable({
  providedIn: 'root'
})
export class CoachService {

  constructor(private http: HttpClient) { }

  getCoaches$(): Observable<ICoach[]> {
    return this.http.get<ICoach[]>(serverUrl);
  }

  getCoachById$(id: number): Observable<ICoach> {
    return this.http.get<ICoach>(`${serverUrl}/${id}`);
  }

  editUsersForGivenCoach(coachId: number, userIds: number[]): Observable<ICoach> {
    const body = {
      "clients": userIds
    }

    return this.http.patch<ICoach>(`${serverUrl}/${coachId}`, body, environment.httpOptions);
  }
}
