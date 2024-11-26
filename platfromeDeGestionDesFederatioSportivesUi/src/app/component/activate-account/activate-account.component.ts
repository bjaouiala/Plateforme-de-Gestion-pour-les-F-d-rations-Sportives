import { Component } from '@angular/core';
import {AuthServiceService} from "../../services/authentication/auth-service.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-activate-account',
  templateUrl: './activate-account.component.html',
  styleUrls: ['./activate-account.component.css']
})
export class ActivateAccountComponent {
  isSubmitted:boolean=false
  isOkay: boolean = true;
  message: string='';
  constructor(private authService:AuthServiceService,private router:Router) {
  }


  redirectToLogin() {
    this.router.navigate(['login'])
  }

  onCodeCompleted(token: string) {
    this.ActivateAccount(token)

  }

  private ActivateAccount(token: string) {
    this.authService.activateAccount({token:token}).subscribe({
      next :() =>{
        this.message = "Account has been confirmed"
        this.isSubmitted =true
    },
      error: (err)=>{
        this.message = "Token has been expired or invalid please try again"
        this.isSubmitted=true
        this.isOkay = false
      }
    })
  }
}
