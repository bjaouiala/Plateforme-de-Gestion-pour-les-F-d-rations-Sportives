import {Component, OnInit} from '@angular/core';
import {ClubRequest} from "../../../../services/models/clubModel/ClubRequest";
import {ActivatedRoute, Router} from "@angular/router";
import {ClubServiceService} from "../../../../services/club/club-service.service";
import {ToastrService} from "ngx-toastr";
import {Form, FormControl, FormGroup, NgForm, Validators} from "@angular/forms";
import {passwordValidator} from "../../CustomValidation";
@Component({
  selector: 'app-manage-club',
  templateUrl: './manage-club.component.html',
  styleUrls: ['./manage-club.component.css']
})
export class ManageClubComponent implements OnInit{
  errorMsg: Array<string>=[];
  selectedPicture: string | null=null;
    clubPicture:File | null = null
  club:ClubRequest={}
  clubId:number=0
  clubForm!:FormGroup
  constructor(private router:Router,private clubService:ClubServiceService,private activateRouter:ActivatedRoute,private toastService:ToastrService) {
    this.clubForm = new FormGroup({
      name: new FormControl("",Validators.required),
      email: new FormControl("",[Validators.required,Validators.email]),
      address: new FormControl("",Validators.required),
      phoneNumber: new  FormControl("",Validators.required),
      description: new FormControl("",Validators.required)
    })
  }


  onFileSelected(file: Event) {
    const input = file.target as HTMLInputElement
     if (input.files && input.files.length > 0){
       this.clubPicture = input.files[0]
       const reader = new FileReader()
       reader.onload = () =>{
         this.selectedPicture = reader.result as string
       }
       reader.readAsDataURL(this.clubPicture)
     }
  }

  saveClub() {

    if (this.clubForm.invalid){
      this.toastService.error('Please fill all required fields');
      return;
    }
    this.club = this.clubForm.value;

      if (this.clubId){
        this.clubService.updateClub(this.clubId,this.club,this.clubPicture).subscribe({
          next : res =>{
            this.toastService.success("club updated successfully","Done")
            this.router.navigate(['admin'])
          },
          error: err => {
            if (!this.clubPicture){
              this.toastService.error("please select a picture")
            }else {
              this.toastService.error("something went wrong","Oups!!")
            }
          }
        })
      }else {
        this.clubService.saveClub(this.club,this.clubPicture).subscribe({
          next : res => {
            this.router.navigate(['admin'])
          },
          error : err => {
            if (!this.clubPicture){
              this.toastService.error("please select a picture","oups !!")
            }else {
              this.toastService.error("something went wrong","Oups!!")
            }
          }
        })
      }

    }


  ngOnInit(): void {
    this.clubId = this.activateRouter.snapshot.params['id']
    if (this.clubId){
      this.clubService.findClubsById(this.clubId).subscribe({
        next : club =>{
          this.club = {
            name : club.name as string,
            address : club.address as string,
            phoneNumber : club.phoneNumber as string,
            email : club.email as string,
            description : club.description
          }
          this.clubForm.patchValue({
            name: this.club.name,
            address: this.club.address,
            phoneNumber: this.club.phoneNumber,
            email: this.club.email,
            description: this.club.description
          });
          if (club.clubPicture){
            this.selectedPicture = 'data:image/jpg;base64,'+club.clubPicture
          }
        }
      })
    }
  }
}
