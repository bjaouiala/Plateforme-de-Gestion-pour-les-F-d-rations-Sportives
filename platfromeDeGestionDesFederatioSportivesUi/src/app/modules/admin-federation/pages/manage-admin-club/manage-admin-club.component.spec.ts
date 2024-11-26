import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageAdminClubComponent } from './manage-admin-club.component';

describe('ManageAdminClubComponent', () => {
  let component: ManageAdminClubComponent;
  let fixture: ComponentFixture<ManageAdminClubComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ManageAdminClubComponent]
    });
    fixture = TestBed.createComponent(ManageAdminClubComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
