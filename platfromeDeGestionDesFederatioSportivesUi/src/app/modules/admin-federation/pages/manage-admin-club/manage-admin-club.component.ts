import {Component, OnInit} from '@angular/core';
import {ToastrService} from 'ngx-toastr';
import {ClubAdminService} from 'src/app/services/adminClub/club-admin.service';
import {TokenService} from 'src/app/services/authentication/token.service';
import {RegistrationRequest, Role} from 'src/app/services/models/AuthenticationModel/RegistrationrRequest';
import {ActivatedRoute, Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {passwordValidator} from "../../CustomValidation";
import {ClubServiceService} from "../../../../services/club/club-service.service";
import {ClubResponse} from "../../../../services/models/clubModel/ClubResponse";
import {Status} from "../../../../services/models/event/eventRequest";

@Component({
  selector: 'app-manage-admin-club',
  templateUrl: './manage-admin-club.component.html',
  styleUrls: ['./manage-admin-club.component.css']
})
export class ManageAdminClubComponent implements OnInit{
  adminClubForm!:FormGroup
  adminId:number=0
selectedPicture: string | null = null;
picture: File | null =null
registrationRequest:RegistrationRequest={}
  clubs:Array<ClubResponse>=[]
errorMsgs:Array<string>=[]
  adminRequest:RegistrationRequest={
    firstname: '',
    lastname: '',
    email: '',
    password: '',
    address: '',
    role:Role.ADMIN_CLUB
  }
  constructor(private adminClubService:ClubAdminService,private toastService:ToastrService
    ,private clubService:ClubServiceService,private activateRoute:ActivatedRoute
    ,private router:Router){
  this.adminClubForm = new FormGroup({
    firstname: new FormControl("",Validators.required),
    lastname: new FormControl("",Validators.required),
    email: new FormControl("",[Validators.required,Validators.email]),
    password: new FormControl("",[Validators.required,passwordValidator]),
    address: new FormControl("",Validators.required),
    club:new FormControl('', Validators.required)
  })
  }



onFileSelected(file: Event) {
  const input = file.target as HTMLInputElement
  if(input.files && input.files.length>0){
    this.picture = input.files[0]
    const reader = new FileReader()
    reader.onload = ()=>{
      this.selectedPicture = reader.result as string
    }
    reader.readAsDataURL(this.picture)
  }
}

saveAdmin(){
  if (this.adminClubForm.invalid){
    this.adminRequest = this.adminClubForm.value
    this.adminRequest.role = Role.ADMIN_CLUB;
  }
  if (this.adminId){
    this.adminClubService.updateUser(this.adminRequest,this.picture,this.adminId).subscribe({
      next: res => {
        this.router.navigate(['/admin/admin-club'])
        this.toastService.success("user updated successfully","Done")
      },
      error: err=>{
        this.errorMsgs=[]
        if(err.error.validationErrors){
          this.errorMsgs = err.error.validationErrors
          this.errorMsgs.forEach(element=>{
            this.toastService.error(element,"Oups")
          })
        }else{
          if (!this.picture){
            this.toastService.error("please select a picture","Oups!!")
          }else {
            this.toastService.error("something went wrong","Oups")
          }

        }
      }
    })
  }else {

    this.adminClubService.addAdminClub(this.adminRequest,this.picture).subscribe({
      next: response =>{
        this.toastService.success("User added successfully","DONE")
        this.router.navigate(['/admin/admin-club'])
      },
      error: err=>{
        this.errorMsgs=[]
        if(err.error.validationErrors){
          this.errorMsgs = err.error.validationErrors
          this.errorMsgs.forEach(element=>{
            this.toastService.error(element,"Oups")
          })
        }else{
          if (!this.picture){
            this.toastService.error("please select a picture","Oups!!")
          }else {
            this.toastService.error("something went wrong","Oups")
          }

        }

      }
    })
  }

}

  ngOnInit(): void {
   this.adminId= this.activateRoute.snapshot.params["id"]
    if (this.adminId){
      this.adminClubService.getAdminById(this.adminId).subscribe({
        next: response =>{
          this.adminRequest={
            firstname : response.firstname as string,
            lastname: response.lastname as string,
            email: response.email as string,
            password : response.password as string,
            address: response.address as string,
          }
          this.adminClubForm.patchValue({
            firstname : response.firstname as string,
            lastname: response.lastname as string,
            email: response.email as string,
            password : response.password as string,
            address: response.address as string,
          })
          if (response.userPicture){
            this.selectedPicture = 'data:image/jpg;base64,'+response.userPicture
          }
        }
      })
    }

    this.clubService.getAllClubsBySportId().subscribe({
      next: response => {
        this.clubs = response
      },
      error : err =>{
        this.toastService.error("something went wrong")
      }
    })


  }

}
