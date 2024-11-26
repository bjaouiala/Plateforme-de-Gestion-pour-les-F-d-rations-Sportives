import { Component } from '@angular/core';
import {AuthenticationRequest} from "../../services/models/AuthenticationModel/AuthenticationRequest";
import {AuthServiceService} from "../../services/authentication/auth-service.service";
import {Router} from "@angular/router";
import {TokenService} from "../../services/authentication/token.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  authRequest:AuthenticationRequest={email:"",password:""}
  errorMessage:Array<string>=[]
  constructor(private authService:AuthServiceService,private router:Router,private tokenService : TokenService,
              private toastService:ToastrService) {
  }


  login() {
    this.authService.login(this.authRequest).subscribe({
      next:(res)=>{
        this.tokenService.token = res.token as string
        this.router.navigate(['admin'])
      },
      error: err => {
        if (err.error.validationErrors){
          this.toastService.info("username or password incorrect")
        }else if(err.error.error == "User not found"){
          this.toastService.info("please enter your credentials correctly")
        }
        else {
          this.toastService.error("something went wrong")
        }
      }
    })

  }

  register() {
    this.router.navigate(['/register'])
  }
}
