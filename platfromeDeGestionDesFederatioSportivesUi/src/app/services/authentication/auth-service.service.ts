import { Injectable } from '@angular/core';
import {HttpClient, HttpContext} from "@angular/common/http";
import {BaseServiceService} from "../base-service.service";
import {AuthenticationRequest} from "../models/AuthenticationModel/AuthenticationRequest";
import {AuthenticationResponse} from "../models/AuthenticationModel/AuthenticationResponse";
import {map} from "rxjs";
import {RegistrationRequest} from "../models/AuthenticationModel/RegistrationrRequest";

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {
private readonly loginUrl="/auth/login"
  private readonly registerUrl="/auth/register-athlete"
  private readonly registerSimpleUserUrl = "/auth/register-simple-user"
  private readonly activateAccountUrl ="/auth/activate-account"
  constructor(private http:HttpClient,private rootUrl:BaseServiceService) { }

  public login(authRequest:AuthenticationRequest,context?:HttpContext){
  return this.http.post<AuthenticationResponse>(`${this.rootUrl.rootUrl}${this.loginUrl}`,authRequest,
    {
      context:context,
      headers: {
        'content-type':'application/json'
      }
    })
  }

  public registerAthlete(registerRequest:RegistrationRequest,file:File|null,picture:File|null){
    const formData = new FormData();
    formData.append('request', new Blob([JSON.stringify(registerRequest)], { type: 'application/json' }))
    if (file){
      formData.append("file",file)
    }
    if (picture){
      formData.append('picture',picture)
    }
  return this.http.post(`${this.rootUrl.rootUrl}${this.registerUrl}`,formData)
  }

  public registerSimpleUser(registrationRequest:RegistrationRequest,picture:File|null){
  const formData = new FormData()
    formData.append("request",new Blob([JSON.stringify(registrationRequest)],{type:'application/json'}))
    if (picture){
      formData.append("picture",picture)
    }
    return this.http.post(`${this.rootUrl.rootUrl}${this.registerSimpleUserUrl}`,formData)
  }

  public activateAccount(param:ActivateAccount$Param){
  return this.http.get<void>(`${this.rootUrl.rootUrl}${this.activateAccountUrl}`,{params:{...param}})
  }
  public resentConfirmationCode(){

  }
}

export interface ActivateAccount$Param{
  token:string
}
