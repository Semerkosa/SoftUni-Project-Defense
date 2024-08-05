import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkoutProgramsUserListComponent } from './workout-programs-user-list.component';

describe('WorkoutProgramsUserListComponent', () => {
  let component: WorkoutProgramsUserListComponent;
  let fixture: ComponentFixture<WorkoutProgramsUserListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WorkoutProgramsUserListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WorkoutProgramsUserListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
