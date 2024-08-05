import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkoutProgramReviewPageComponent } from './workout-program-review-page.component';

describe('WorkoutProgramReviewPageComponent', () => {
  let component: WorkoutProgramReviewPageComponent;
  let fixture: ComponentFixture<WorkoutProgramReviewPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WorkoutProgramReviewPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WorkoutProgramReviewPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
