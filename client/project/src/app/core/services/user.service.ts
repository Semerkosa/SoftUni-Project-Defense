import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ICoach, ICreateUserDto, ILoginUserDto, IUser, IWorkoutProgram } from '../interfaces';

const serverUrl = `${environment.apiUrl}/user`;

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
		return !!localStorage.getItem("id"); // to retrieve boolean
	}

	isAdmin(): boolean {
		return localStorage.getItem("isAdmin") == "true" ? true : false;
	}

	getUserFullName(): string {
		const fullName = localStorage.getItem("fullName");
		// console.log("userService called for name ", localStorage.getItem("fullName"));

		return fullName ? fullName : "";
	}

	getJwtToken(): string {
		const token = localStorage.getItem("token");
		console.log("JWT Token ", token);

		return token ? token : "";
	}

	getUserId(): string {
		const userId = localStorage.getItem("id");
		console.log("userService called for user id ", userId);

		return userId ? userId : "";
	}

	getUserById$(id: string): Observable<IUser> {
		return this.http.get<IUser>(`${serverUrl}/${id}`);
	}

	editWorkoutProgramsForGivenUser$(userId: string, programs: IWorkoutProgram[]): Observable<IUser> {
		const body = {
			"purchasedWorkoutPrograms": programs
		}

		return this.http.patch<IUser>(`${serverUrl}/${userId}`, body, this.getUpdatedHttpOptions());
	}

	editCoachForGivenUser$(userId: string, coach?: ICoach): Observable<ICoach> {
		const body = {
			"coach": coach? coach : {} // Will unset the coach if it wasn't passed as a parameter
		}

		return this.http.patch<ICoach>(`${serverUrl}/${userId}`, body, this.getUpdatedHttpOptions());
	}

	login$(user: ILoginUserDto): Observable<any> {
		const url = `${serverUrl}/login`;
		return this.http.post<ILoginUserDto>(url, user, this.getUpdatedHttpOptions());
	}

	register$(user: ICreateUserDto): Observable<any> {
		const url = `${serverUrl}/register`;
		return this.http.post<ICreateUserDto>(url, user, this.getUpdatedHttpOptions());
	}

	logout(): void {
		localStorage.removeItem("id");
		localStorage.removeItem("fullName");
		localStorage.removeItem("token");
		localStorage.removeItem("isAdmin");

		this.updateLoginStatus(false);
	}

	getUpdatedHttpOptions() {
		const token = this.getJwtToken();

		let httpOptions = {
			headers: new HttpHeaders({
				"Content-Type": "application/json"
			})
		};

		if (token) {
			httpOptions.headers = httpOptions.headers.set("Authorization", `Bearer ${token}`);
		}

		console.log("HTTP options: ", httpOptions);

		return httpOptions;		
	}
}
