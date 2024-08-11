import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/core/services/user.service';
import { emailValidator, passwordValidator } from '../utils';

@Component({
	selector: 'app-login',
	templateUrl: './login.component.html',
	styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
	errorMessage = '';

	constructor(private formBuilder: FormBuilder, private userService: UserService, private router: Router) { }

	ngOnInit(): void {
	}

	loginFormGroup: FormGroup = this.formBuilder.group({
		email: new FormControl(null, [Validators.required, emailValidator]),
		password: new FormControl(null, [
			Validators.required,
			passwordValidator,
		]),
	});

	onSubmit() {
		this.errorMessage = '';
		const { email, password } = this.loginFormGroup.value;

		const body = {
			email,
			password
		}

		console.log("login body", body);

		this.userService.login$(body).subscribe({
			next: response => {
				console.log("Login response", response);

				const authorities = response.authorities;
				console.log("Auth", authorities);

				const isAdmin = authorities.includes("ADMIN");
				console.log("Is Admin? - ", isAdmin);

				localStorage.setItem('id', response.id);
				localStorage.setItem('token', response.token);
				localStorage.setItem('isAdmin', isAdmin);
				localStorage.setItem('fullName', response.lastName ? `${response.firstName} ${response.lastName}` : `${response.firstName}`);
				
				this.userService.updateLoginStatus(true);

				this.router.navigate(['/workout-programs/all']);
			},
			complete: () => {
				console.log('Login stream completed')
			},
			error: (err) => {
				console.log(err);
				this.errorMessage = "Invalid email or password.";
				this.loginFormGroup.setValue({
					email,
					password: ""
				});
				this.loginFormGroup.markAsUntouched();
			}
		});
	}

	shouldShowErrorForControl(
		controlName: string,
		sourceGroup: FormGroup = this.loginFormGroup
	) {
		return (
			sourceGroup.controls[controlName].touched &&
			sourceGroup.controls[controlName].invalid
		);
	}
}
