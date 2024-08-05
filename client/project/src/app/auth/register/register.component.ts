import { Component, OnInit } from '@angular/core';
import {
	FormBuilder,
	FormControl,
	FormGroup,
	Validators
} from '@angular/forms';
import { Router } from '@angular/router';
import { map, switchMap } from 'rxjs';
import { ICreateUserDto } from 'src/app/core/interfaces';
import { UserService } from 'src/app/core/services/user.service';
import { emailValidator, passwordValidator } from '../utils';

@Component({
	selector: 'app-register',
	templateUrl: './register.component.html',
	styleUrls: ['./register.component.scss'],
})
export class RegisterComponent implements OnInit {
	errorMessage = '';

	registerFormGroup: FormGroup = this.formBuilder.group({
		email: new FormControl(null, [Validators.required, emailValidator]),
		firstName: new FormControl(null, [
			Validators.required,
			Validators.minLength(2),
		]),
		lastName: new FormControl(),
		password: new FormControl(null, [
			Validators.required,
			passwordValidator,
		]),
	});

	constructor(
		private formBuilder: FormBuilder,
		private userService: UserService,
		private router: Router
	) { }

	ngOnInit(): void { }

	onSubmit(): void {
		console.log('Register form values:');
		console.log(this.registerFormGroup);
		const { email, firstName, lastName, password } =
			this.registerFormGroup.value;

		const body: ICreateUserDto = {
			email: email,
			firstName: firstName,
			lastName: lastName,
			password: password,
			purchasedWorkoutPrograms: [],
			coach: {},
			isAdmin: false
		};

		// this.userService.register$(body)
		// 	.pipe(
		// 		map(response => {
		// 			console.log('Registered user response:');
		// 			console.log(response);

		// 			localStorage.setItem('id', response.user.id);
		// 			localStorage.setItem('email', response.user.email);
		// 		}),
		// 		switchMap(() => {
		// 			return this.router.navigate([`/workout-programs`]);
		// 		})
		// 	)
		// 	.subscribe({
		// 		next(isNavigated) {
		// 			console.log(`Navigated successfully (${isNavigated})`);
		// 		},
		// 		error(msg) {
		// 			console.log("Error upon registration");

		// 			alert(msg.error);
		// 		}
		// 	}
		// 	);

		this.userService.register$(body).subscribe({
			next: response => {
				console.log('Registered user response:');
				console.log(response);

				localStorage.setItem('id', response.user.id);
				localStorage.setItem('token', response.accessToken);
				localStorage.setItem('isAdmin', response.user.isAdmin);

				if (response.user.lastName) {
					localStorage.setItem('fullName', `${response.user.firstName} ${response.user.lastName}`);
				} else {
					localStorage.setItem('fullName', response.user.firstName);
				}

				this.userService.updateLoginStatus(true);

				this.router.navigate([`/workout-programs`]);
			},
			complete: () => {
				console.log('Registration stream completed')
			},
			error: (httpError) => {
				console.log(httpError);
				this.errorMessage = httpError.error + "!";

				this.registerFormGroup.setValue({
					email: email,
					firstName: firstName,
					lastName: lastName,
					password: ""
				});

				this.registerFormGroup.markAsUntouched();
			}
		});
	}

	shouldShowErrorForControl(
		controlName: string,
		sourceGroup: FormGroup = this.registerFormGroup
	) {
		return (
			sourceGroup.controls[controlName].touched &&
			sourceGroup.controls[controlName].invalid
		);
	}
}
