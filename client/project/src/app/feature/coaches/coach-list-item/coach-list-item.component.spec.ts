import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CoachListItemComponent } from './coach-list-item.component';

describe('CoachListItemComponent', () => {
  let component: CoachListItemComponent;
  let fixture: ComponentFixture<CoachListItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CoachListItemComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CoachListItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
