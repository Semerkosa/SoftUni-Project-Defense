import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CoachReviewPageComponent } from './coach-review-page.component';

describe('CoachReviewPageComponent', () => {
  let component: CoachReviewPageComponent;
  let fixture: ComponentFixture<CoachReviewPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CoachReviewPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CoachReviewPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
