import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ICoach, ICreateUserDto, ILoginUserDto, IUser, IWorkoutProgram } from '../interfaces';

const serverUrl = `${environment.apiUrl}/users`

@Injectable({
	providedIn: 'root'
})
export class UserService {
	private isAuthenticatedSubject = new BehaviorSubject<boolean>(this.isLoggedIn());
	public isAuthenticated$: Observable<boolean> = this.isAuthenticatedSubject.asObservable();

	constructor(private http: HttpClient) { }

	updateLoginStatus(status: boolean) {
		this.isAuthenticatedSubject.next(status);
	}

	isLoggedIn(): boolean {
		return !!localStorage.getItem("token"); // to retrieve boolean
	}

	isAdmin(): boolean {
		return localStorage.getItem("isAdmin") == "true" ? true : false;
	}

	getUserFullName(): string {
		const fullName = localStorage.getItem("fullName");
		console.log("userService called for name ", localStorage.getItem("fullName"));

		return fullName ? fullName : "";
	}

	getUserId(): string {
		const userId = localStorage.getItem("id");
		console.log("userService called for user id ", userId);

		return userId ? userId : "";
	}

	getUserById$(id: number): Observable<IUser> {
		return this.http.get<IUser>(`${serverUrl}/${id}`);
	}

	editWorkoutProgramsForGivenUser$(userId: number, programs: IWorkoutProgram[]): Observable<IUser> {
		const body = {
			"purchasedWorkoutPrograms": programs
		}

		return this.http.patch<IUser>(`${serverUrl}/${userId}`, body, environment.httpOptions);
	}

	editCoachForGivenUser$(userId: number, coach?: ICoach): Observable<ICoach> {
		const body = {
			"coach": coach? coach : {} // Will unset the coach if it wasn't passed as a parameter
		}

		return this.http.patch<ICoach>(`${serverUrl}/${userId}`, body, environment.httpOptions);
	}

	login$(user: ILoginUserDto): Observable<any> {
		const url = `${environment.apiUrl}/login`;
		return this.http.post<ILoginUserDto>(url, user, environment.httpOptions);
	}

	register$(user: ICreateUserDto): Observable<any> {
		const url = `${environment.apiUrl}/register`;
		return this.http.post<ICreateUserDto>(url, user, environment.httpOptions);
	}

	logout(): void {
		localStorage.removeItem("id");
		localStorage.removeItem("fullName");
		localStorage.removeItem("token");
		localStorage.removeItem("isAdmin");

		this.updateLoginStatus(false);
	}
}
