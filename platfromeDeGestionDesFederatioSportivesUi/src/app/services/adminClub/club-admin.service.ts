import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PaginationParams } from '../PaginationParams';
import { AdminClubPageResponse } from '../models/adminClubModel/AdminClubPageResponse';
import { BaseServiceService } from '../base-service.service';
import { RegistrationRequest } from '../models/AuthenticationModel/RegistrationrRequest';
import {AdminResponse} from "../models/adminClubModel/AdminResponse";

@Injectable({
  providedIn: 'root'
})
export class ClubAdminService {
  private readonly AdminClubUrl:string = "/admin-club"

  constructor(private httpClient:HttpClient,private baseService:BaseServiceService) { }

  findAllClubAdmin(param:PaginationParams){
    return this.httpClient.get<AdminClubPageResponse>(`${this.baseService.rootUrl}${this.AdminClubUrl}`,{params:{...param}})
  }

  addAdminClub(request:RegistrationRequest,picture: File | null){
    const formData = new FormData()
    if(request!= null){
      formData.append("request",new Blob([JSON.stringify(request)],{type:'application/json'}))
    }
    if(picture!= null){
      formData.append("picture", picture)
    }
    return this.httpClient.post(`${this.baseService.rootUrl}${this.AdminClubUrl}`,formData)

  }

  changeAccountStatus(param: AccountLockedparam) {
    const httpParams = new HttpParams()
        .set('id', param.id?.toString() || '')
        .set('status', param.status?.toString() || '');

    return this.httpClient.patch(`${this.baseService.rootUrl}${this.AdminClubUrl}`, {}, { params: httpParams });
}

 updateUser(registrationRequest:RegistrationRequest,picture:File|null,id:number){
    const formData = new FormData()
   if (registrationRequest != null){
     formData.append("request",new Blob([JSON.stringify(registrationRequest)],{type:"application/json"}))
   }
   if (picture != null){
     formData.append("picture",picture)
   }
    return this.httpClient.patch<number>(`${this.baseService.rootUrl}${this.AdminClubUrl}/${id}`,formData)
 }

 getAdminById(id:number){
    return this.httpClient.get<AdminResponse>(`${this.baseService.rootUrl}${this.AdminClubUrl}/${id}`)
 }
}


export interface AccountLockedparam{
id?:number
status?:boolean
}
