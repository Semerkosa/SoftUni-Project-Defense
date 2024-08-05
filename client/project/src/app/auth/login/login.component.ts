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
			email: email,
			password: password
		}

		console.log("login body", body);


		this.userService.login$(body).subscribe({
			next: response => {
				console.log("Login response", response);

				localStorage.setItem('id', response.user.id);
				localStorage.setItem('token', response.accessToken);
				localStorage.setItem('isAdmin', response.user.isAdmin);

				if (response.user.lastName) {
					localStorage.setItem('fullName', `${response.user.firstName} ${response.user.lastName}`);
				} else {
					localStorage.setItem('fullName', response.user.firstName);
				}

				this.userService.updateLoginStatus(true);

				this.router.navigate(['/workout-programs']);
			},
			complete: () => {
				console.log('Login stream completed')
			},
			error: (err) => {
				console.log(err);
				this.errorMessage = err.error + "!";
				this.loginFormGroup.setValue({
					email: email,
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
