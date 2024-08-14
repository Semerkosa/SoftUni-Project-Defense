import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ICreateProgram, IEditProgram, IWorkoutProgram } from '../interfaces';
import { UserService } from './user.service';

const serverUrl = `${environment.apiUrl}/workout-programs`;

@Injectable({
  providedIn: 'root'
})
export class WorkoutProgramService {

  constructor(private http: HttpClient, private userService: UserService) { }

  getWorkoutPrograms$(): Observable<IWorkoutProgram[]> {
    return this.http.get<IWorkoutProgram[]>(`${serverUrl}/all`);
  } 

  getWorkoutProgramById$(id: string): Observable<IWorkoutProgram> {
    return this.http.get<IWorkoutProgram>(`${serverUrl}/${id}`, this.userService.getUpdatedHttpOptions())
  }

  purchaseProgram$(userId: string, programId: string): Observable<any> {
    const url = `${serverUrl}/purchase?userId=${userId}&workoutProgramId=${programId}`;
		return this.http.post<any>(url, null, this.userService.getUpdatedHttpOptions());
  }

  createWorkoutProgram$(program: ICreateProgram): Observable<IWorkoutProgram> {
    return this.http.post<IWorkoutProgram>(`${serverUrl}/create`, program, this.userService.getUpdatedHttpOptions());
  }

  deleteWorkoutProgramById$(programId: string): Observable<any> {
    return this.http.delete<any>(`${serverUrl}/delete/${programId}`, this.userService.getUpdatedHttpOptions());
  }

  editWorkoutProgramById$(programId: string, program: IEditProgram): Observable<IWorkoutProgram> {
     return this.http.patch<IWorkoutProgram>(`${serverUrl}/edit/${programId}`, program, this.userService.getUpdatedHttpOptions());
  }
}
