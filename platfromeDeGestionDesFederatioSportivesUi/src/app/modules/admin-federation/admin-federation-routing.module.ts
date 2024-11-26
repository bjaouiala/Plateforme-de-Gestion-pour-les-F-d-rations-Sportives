import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MainComponent} from "./pages/main/main.component";
import {ClubListComponent} from "./pages/club-list/club-list.component";
import {ManageClubComponent} from "./pages/manage-club/manage-club.component";
import { AdminClubListComponent } from './pages/admin-club-list/admin-club-list.component';
import { ManageAdminClubComponent } from './pages/manage-admin-club/manage-admin-club.component';
import {ManageEventComponent} from "./pages/manage-event/manage-event.component";
import {EventListComponent} from "./pages/event-list/event-list.component";

const routes: Routes = [
  {
    path: '',
    component:MainComponent,
    children: [{
      path :'',
      component: ClubListComponent
    },{
      path: 'manage-club',
      component : ManageClubComponent
    },{
      path: 'manage-club/:id',
      component: ManageClubComponent
    },{
      path: 'admin-club',
      component:AdminClubListComponent
    },{
      path:'manage-admin-club',
      component: ManageAdminClubComponent
    },{
      path:'manage-admin-club/:id',
      component:ManageAdminClubComponent
    },{
      path:'manage-event',
      component: ManageEventComponent
    },{
      path:"all-Event",
      component:EventListComponent
    },{
      path:'manage-event/:id',
      component: ManageEventComponent
    }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminFederationRoutingModule { }
