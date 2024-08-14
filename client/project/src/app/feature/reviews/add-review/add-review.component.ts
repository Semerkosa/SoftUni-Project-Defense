import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-review',
  templateUrl: './add-review.component.html',
  styleUrls: ['./add-review.component.scss']
})
export class AddReviewComponent implements OnInit {
  errorMessage = '';

  constructor(private formBuilder: FormBuilder) { }

  ngOnInit(): void {
  }

  addReviewFormGroup: FormGroup = this.formBuilder.group({
    review: new FormControl(null, Validators.required)
  });

  addReview() {
    console.log("Should add a review");
    
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
