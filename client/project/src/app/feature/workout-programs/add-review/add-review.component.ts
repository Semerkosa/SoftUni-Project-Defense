import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { IWorkoutProgram } from 'src/app/core/interfaces';
import { ReviewService } from 'src/app/core/services/review.service';
import { UserService } from 'src/app/core/services/user.service';

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
              private reviewService: ReviewService,
              private userService: UserService
  ) { }

  ngOnInit(): void {
  }

  addReviewFormGroup: FormGroup = this.formBuilder.group({
    review: new FormControl(null, Validators.required)
  });

  addReview() {
    console.log('Review form', this.addReviewFormGroup.value);

    const { review } = this.addReviewFormGroup.value;

    if (review?.trim().length === 0) {
      this.errorMessage = "You cannot post an empty review!";
      return;
    }

    const userId = this.userService.getUserId();

    this.reviewService.addReviewForWorkoutProgram$(userId, review, this.program.id).subscribe({
      next: response => {
        console.log('Post review response: ', response);
        this.onReviewPost.emit(review);
      },
      error: (err) => {
        console.log(err);

        if (err.status == 502) {
          this.errorMessage = "You need to buy the product first.";
        } else {
          this.errorMessage = "Couldn't save the review.";
        }

        this.addReviewFormGroup.setValue({
          review: '',
        });

        this.addReviewFormGroup.markAsUntouched();
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
