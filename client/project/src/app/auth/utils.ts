import { AbstractControl, ValidationErrors } from "@angular/forms";

export function emailValidator(control: AbstractControl): ValidationErrors | null {
    const value = control.value;
    // console.log('Validating email - ' + value);

    if (!value) {
        return null;
    }

    if (!/^[a-zA-Z0-9. _%+-]+\@[a-zA-Z0-9. -]+\.[a-zA-Z]{2,}$/.test(value)) {
        // pesho@abv.bg, ivan_ivanov@gmail.com
        return {
            email: true,
        };
    }

    return null;
}

export function passwordValidator(control: AbstractControl): ValidationErrors | null {
    const value = control.value;
    // console.log('Validating password - ' + value);

    if (!value) {
        return null;
    }

    if (!/(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}/.test(value)) {
        // Strongpass123
        return {
            password: true,
        };
    }

    return null;
}