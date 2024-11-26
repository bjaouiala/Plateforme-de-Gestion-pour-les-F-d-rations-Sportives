import { Component } from '@angular/core';
import {animate, animation, state, style, transition, trigger} from "@angular/animations";

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.css'],
  animations:[trigger('submenuAnimation',[
    state('collapsed',style({
      height:'0',
      opacity: 0,
      overflow:'hidden',
      display:'none'
    })),
    state('expanded',style({
      height:'*',
      opacity: 1,
      display: 'block'
    })),
    transition('collapsed<=>expanded',animate('300ms ease-in-out'))
  ])]
})
export class SideBarComponent {
  isDashboardExpanded = false;
  isClubsExpanded = false;
  isLicensesExpanded = false;
  isEventsExpanded = false;

  toggleDashboard() {
    this.isDashboardExpanded = !this.isDashboardExpanded
  }

  toggleClub() {
    this.isClubsExpanded = !this.isClubsExpanded
  }

  toggleLicense() {
    this.isLicensesExpanded = !this.isLicensesExpanded
  }

  toggleEvent() {
    this.isEventsExpanded = !this.isEventsExpanded
  }

  logout() {
    localStorage.removeItem('token')
    window.location.reload()
  }
}
