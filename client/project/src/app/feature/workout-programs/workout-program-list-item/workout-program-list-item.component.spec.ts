import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkoutProgramListItemComponent } from './workout-program-list-item.component';

describe('WorkoutProgramListItemComponent', () => {
  let component: WorkoutProgramListItemComponent;
  let fixture: ComponentFixture<WorkoutProgramListItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WorkoutProgramListItemComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WorkoutProgramListItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
