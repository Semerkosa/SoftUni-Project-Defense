import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { ICreateProgram, IWorkoutProgram } from 'src/app/core/interfaces';
import { WorkoutProgramService } from 'src/app/core/services/workout-program.service';

@Component({
  selector: 'app-add-workout-program',
  templateUrl: './add-workout-program.component.html',
  styleUrls: ['./add-workout-program.component.scss'],
})
export class AddWorkoutProgramComponent implements OnInit {
  errorMessage = '';

  @Output() onProgramCreate = new EventEmitter<IWorkoutProgram>();

  constructor(
    private formBuilder: FormBuilder,
    private workoutProgramService: WorkoutProgramService
  ) {}

  ngOnInit(): void {}

  createProgramFormGroup: FormGroup = this.formBuilder.group({
    name: new FormControl(null, Validators.required),
    price: new FormControl(null, [Validators.required, Validators.min(1)]),
    description: new FormControl(null, Validators.required),
    details: new FormControl(null, Validators.required),
  });

  createProgram() {
    console.log('Create form', this.createProgramFormGroup.value);

    const { name, price, description, details } =
      this.createProgramFormGroup.value;
    
    if (name?.trim().length === 0 || description?.trim().length === 0 || details?.trim().length === 0) {
      this.errorMessage = "Fill in all the fields!";
      return;
    }

    const program: ICreateProgram = {
      name: name,
      price: price,
      description: description,
      details: details,
    };

    this.workoutProgramService.createWorkoutProgram$(program).subscribe({
      next: (createdProgram) => {
        console.log('Created program', createdProgram);

        this.createProgramFormGroup.setValue({
          name: '',
          price: 0,
          description: '',
          details: '',
        });

        this.createProgramFormGroup.markAsUntouched();

        alert('New workout program created successfully!');
        this.onProgramCreate.emit(createdProgram);
      },
      error: (err) => {
        console.log(err);
        this.errorMessage = err.error;
      },
    });
  }

  shouldShowErrorForControl(
    controlName: string,
    sourceGroup: FormGroup = this.createProgramFormGroup
  ) {
    return (
      sourceGroup.controls[controlName].touched &&
      sourceGroup.controls[controlName].invalid
    );
  }
}
