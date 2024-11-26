import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminClubListComponent } from './admin-club-list.component';

describe('AdminClubListComponent', () => {
  let component: AdminClubListComponent;
  let fixture: ComponentFixture<AdminClubListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdminClubListComponent]
    });
    fixture = TestBed.createComponent(AdminClubListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
