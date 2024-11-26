import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClubCardComponent } from './club-card.component';

describe('ClubCardComponent', () => {
  let component: ClubCardComponent;
  let fixture: ComponentFixture<ClubCardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClubCardComponent]
    });
    fixture = TestBed.createComponent(ClubCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
