import {Component, Input, OnInit} from '@angular/core';
import {ClubResponse} from "../../../../services/models/clubModel/ClubResponse";
import {ClubServiceService} from "../../../../services/club/club-service.service";
import {ClubPageResponse} from "../../../../services/models/clubModel/ClubPageResponse";
import {Router} from "@angular/router";

@Component({
  selector: 'app-club-list',
  templateUrl: './club-list.component.html',
  styleUrls: ['./club-list.component.css']
})
export class ClubListComponent implements OnInit{
  page:number=0
  size: number=3
  clubs:ClubPageResponse={}
  constructor(private clubService:ClubServiceService, private router:Router) {
  }


  ngOnInit(): void {
    this.findAllClubs()
  }

  findAllClubs(){
    this.clubService.findClubs({page:this.page,size:this.size})
      .subscribe({
        next: clubs =>{
          this.clubs = clubs
        }
      })
  }


  goToFirstPage() {
      this.page = 0
    this.findAllClubs()
  }

  previous() {
    this.page--
    this.findAllClubs()
  }

  goToPage(pageIndex: number) {
    this.page=pageIndex
    this.findAllClubs()
  }

  goToNextPage() {
    this.page++

  }

  goToLastPage() {
    this.page = this.clubs.totalPages as number  -1
  }
  get isLastPage(){
    return this.page == this.clubs.totalPages as number -1
  }


  onEdit(club:ClubResponse) {
    this.router.navigate(['/admin/manage-club',club.id])
  }
}
