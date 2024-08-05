import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkoutProgramsListComponent } from './workout-programs-list.component';

describe('WorkoutProgramsListComponent', () => {
  let component: WorkoutProgramsListComponent;
  let fixture: ComponentFixture<WorkoutProgramsListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WorkoutProgramsListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WorkoutProgramsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
