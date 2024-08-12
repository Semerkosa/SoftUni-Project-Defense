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
    return this.http.get<IWorkoutProgram>(`${serverUrl}/${id}`)
  }

  editUsersForGivenWorkoutProgram$(workoutProgramId: string, userIds: string[]): Observable<IWorkoutProgram> {
		const body = {
			"customers": userIds ? userIds : []
		}
    
		return this.http.patch<IWorkoutProgram>(`${serverUrl}/${workoutProgramId}`, body, this.userService.getUpdatedHttpOptions());
	}

  createWorkoutProgram$(program: ICreateProgram): Observable<IWorkoutProgram> {
    return this.http.post<IWorkoutProgram>(`${serverUrl}`, program, this.userService.getUpdatedHttpOptions());
  }

  deleteWorkoutProgramById$(programId: string): Observable<any> {
    return this.http.delete<any>(`${serverUrl}/${programId}`);
  }

  editWorkoutProgramById$(programId: string, program: IEditProgram): Observable<IWorkoutProgram> {
    return this.http.patch<IWorkoutProgram>(`${serverUrl}/${programId}`, program, this.userService.getUpdatedHttpOptions());
  }
}
