import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { IWorkoutProgram } from 'src/app/core/interfaces';
import { ReviewService } from 'src/app/core/services/review.service';

@Component({
  selector: 'app-add-review',
  templateUrl: './add-review.component.html',
  styleUrls: ['./add-review.component.scss']
})
export class AddReviewComponent implements OnInit {
  errorMessage = '';

  @Input() program: IWorkoutProgram;
  @Output() onReviewPost = new EventEmitter<string>();

  constructor(private formBuilder: FormBuilder,
              private reviewService: ReviewService
  ) { }

  ngOnInit(): void {
  }

  addReviewFormGroup: FormGroup = this.formBuilder.group({
    review: new FormControl(null, Validators.required)
  });

  addReview() {
    console.log('Review form', this.addReviewFormGroup.value);

    const { review } = this.addReviewFormGroup.value;

    this.reviewService.addReviewForWorkoutProgram$(review, this.program.id).subscribe({
      next: response => {
        console.log('Post review response: ', response);
        this.onReviewPost.emit(review);
      },
      error: (err) => {
        console.log(err);
        this.errorMessage = "Couldn't save the review.";
      },
    });
  }

  shouldShowErrorForControl(
    controlName: string,
    sourceGroup: FormGroup = this.addReviewFormGroup
  ) {
    return (
      sourceGroup.controls[controlName].touched &&
      sourceGroup.controls[controlName].invalid
    );
  }

}
