import { Injectable } from '@angular/core';
import {JwtHelperService} from "@auth0/angular-jwt";

@Injectable({
  providedIn: 'root'
})
export class TokenService {
  get token(){
    return localStorage.getItem('token') as string
  }
  set token(token:string){
    localStorage.setItem("token",token)
  }

  constructor() { }
  private isValid(){
    const token = this.token
    if (!token){
      return false
    }
    const  jwtHelper = new JwtHelperService()
    const iSExpired = jwtHelper.isTokenExpired(token)
    if (iSExpired){
      localStorage.clear()
      return false
    }
    return true

  }

  isExpired() {
    return !this.isValid();
  }
}
