import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddWorkoutProgramComponent } from './add-workout-program.component';

describe('AddWorkoutProgramComponent', () => {
  let component: AddWorkoutProgramComponent;
  let fixture: ComponentFixture<AddWorkoutProgramComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddWorkoutProgramComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddWorkoutProgramComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
