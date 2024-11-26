import {Component, OnInit} from '@angular/core';
import {AuthServiceService} from "../../services/authentication/auth-service.service";
import {SportService} from "../../services/sport/sport.service";
import {Router} from "@angular/router";
import {SportPageResponse} from "../../services/models/sportModel/SportPageResponse";
import {RegistrationRequest, Role} from "../../services/models/AuthenticationModel/RegistrationrRequest";


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit{
  page:number=0
  size:number=1
  sport:SportPageResponse={}
  userPicture:File | null = null
  document:File | null = null
  errorMsg:Array<string>=[]
  registerRequest:RegistrationRequest={
    address: "",
    email: "",
    firstname: "",
    lastname: "",
    password: "",
    role: Role.SPECTATEUR,
    sportId: 0
  }

  constructor(private authService:AuthServiceService
              ,private sportService:SportService
              ,private router:Router) {
  }
  ngOnInit(): void {
   this.sportService.findAllSports({page:this.page,size:this.size})
      .subscribe({
        next : response => {
          this.sport = response
        }
      })
  }

  register() {
   if (this.registerRequest.role == Role.ATHLETE || this.registerRequest.role == Role.LICENCIE ){
     this.authService.registerAthlete(this.registerRequest,this.document,this.userPicture)
       .subscribe({
         next : res =>{
           this.router.navigate(['account-activation'])
         },error :(err)=>{
           console.log(this.registerRequest)
           if (err.error.validationErrors){
             this.errorMsg = []
             this.errorMsg = err.error.validationErrors
           }else {
             this.errorMsg = []
             this.errorMsg.push(err.error.errorDescription)
           }

     }
       })
   }else if (this.registerRequest.role == Role.SPECTATEUR){
     this.authService.registerSimpleUser(this.registerRequest,this.userPicture)
       .subscribe({
         next: res =>{
           console.log(res)
         }
         // ,error :(err)=>{
         //   if (err.error.validationErrors){
         //     this.errorMsg = []
         //     this.errorMsg = err.error.validationErrors
         //   }else {
         //     this.errorMsg = []
         //     this.errorMsg.push(err.error.errorDescription)
         //   }}
       })
   }

  }

  login() {
    this.router.navigate(['login'])
  }


  protected readonly Role = Role;

  onFileChange(file: Event, fileType: string) {
    const fileInput = file.target as HTMLInputElement
    if (fileInput.files && fileInput.files.length > 0){
      if (fileType == 'document'){
       this.document = fileInput.files[0]
      }else if (fileType == 'userPicture'){
        this.userPicture = fileInput.files[0]
      }
    }

  }

  protected readonly onselect = onselect;



}
