import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { ClubAdminService } from 'src/app/services/adminClub/club-admin.service';
import { AdminClubPageResponse } from 'src/app/services/models/adminClubModel/AdminClubPageResponse';
import {Router} from "@angular/router";
import {AdminResponse} from "../../../../services/models/adminClubModel/AdminResponse";

@Component({
  selector: 'app-admin-club-list',
  templateUrl: './admin-club-list.component.html',
  styleUrls: ['./admin-club-list.component.css']
})
export class AdminClubListComponent implements OnInit{


  adminClubPageResponse:AdminClubPageResponse={}
  page:number=0
  size:number=2
  isLocked:boolean=false

  constructor(private adminClubService:ClubAdminService,private toastService:ToastrService,private router:Router){}


  ngOnInit(): void {
    this.findAllAdminClub()
  }

  findAllAdminClub(){
    this.adminClubService.findAllClubAdmin({page:this.page,size:this.size}).subscribe({
      next: response =>{
        this.adminClubPageResponse = response
      },
      error: err =>{
        this.toastService.error("something went wrong","Oups !!")
      }
    })
  }

  isLast() {
    return this.page == this.adminClubPageResponse.totalPages as number -1
    }


    next() {
    this.page++
    this.findAllAdminClub()
    }


    goToPage(currentPage: number) {
    this.page = currentPage
    this.findAllAdminClub()
    }


    previous() {
    this.page--
    this.findAllAdminClub()
    }


    goToFirstPage() {
    this.page = 0
    this.findAllAdminClub()
    }

    goToLastPage() {
      this.page = this.adminClubPageResponse.totalPages as number - 1
      this.findAllAdminClub()
      }



      changeStatus(status: boolean|undefined,id: number|undefined) {

        this.adminClubService.changeAccountStatus({id:id,status:!status}).subscribe({
          next: res =>{
            this.findAllAdminClub()
          },
          error: err=>{
            this.toastService.error('something went wrong',"Oups !!")
          }

        })
        }


  redirectToUpdateAdmin(user: AdminResponse) {
    this.router.navigate(['/admin/manage-admin-club',user.id])
  }
}
