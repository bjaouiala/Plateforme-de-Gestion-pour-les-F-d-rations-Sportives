  import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminFederationRoutingModule } from './admin-federation-routing.module';
import { MainComponent } from './pages/main/main.component';
import { SideBarComponent } from './components/side-bar/side-bar.component';
import { ClubCardComponent } from './components/club-card/club-card.component';
import { ClubListComponent } from './pages/club-list/club-list.component';
import { RatingComponent } from './components/rating/rating.component';
import { ManageClubComponent } from './pages/manage-club/manage-club.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { AdminClubListComponent } from './pages/admin-club-list/admin-club-list.component';
import { ManageAdminClubComponent } from './pages/manage-admin-club/manage-admin-club.component';
  import {ManageEventComponent} from "./pages/manage-event/manage-event.component";
  import {EventListComponent} from "./pages/event-list/event-list.component";



@NgModule({
  declarations: [
    MainComponent,
    SideBarComponent,
    ClubCardComponent,
    ClubListComponent,
    RatingComponent,
    ManageClubComponent,
    AdminClubListComponent,
    ManageAdminClubComponent,
    ManageEventComponent,
    EventListComponent

  ],
  imports: [
    CommonModule,
    AdminFederationRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class AdminFederationModule { }
