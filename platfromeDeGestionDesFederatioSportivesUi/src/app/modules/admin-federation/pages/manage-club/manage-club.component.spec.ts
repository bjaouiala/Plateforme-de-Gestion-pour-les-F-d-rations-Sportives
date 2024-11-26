import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageClubComponent } from './manage-club.component';

describe('ManageClubComponent', () => {
  let component: ManageClubComponent;
  let fixture: ComponentFixture<ManageClubComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ManageClubComponent]
    });
    fixture = TestBed.createComponent(ManageClubComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
