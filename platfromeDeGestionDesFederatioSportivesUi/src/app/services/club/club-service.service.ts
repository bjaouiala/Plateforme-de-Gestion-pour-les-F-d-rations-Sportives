import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BaseServiceService} from "../base-service.service";
import {ClubRequest} from "../models/clubModel/ClubRequest";
import {PaginationParams} from "../PaginationParams";
import {ClubResponse} from "../models/clubModel/ClubResponse";
import {ClubPageResponse} from "../models/clubModel/ClubPageResponse";

@Injectable({
  providedIn: 'root'
})
export class ClubServiceService {
  private readonly ClubUrl :string = "/clubs"
  private readonly ClubSportUrl :string = "/clubs/club"
  constructor(private http:HttpClient, private rootUrl: BaseServiceService) {
  }

  saveClub(request: ClubRequest, file:File | null){
    const formData = new FormData();
    if (request != null){
      formData.append("request",new Blob([JSON.stringify(request)],{type:'application/json'}))
    }
    if (file != null){
      formData.append('file',file)
    }
   return  this.http.post<number>(`${this.rootUrl.rootUrl}${this.ClubUrl}`,formData)
  }

  findClubs(param:PaginationParams){
   return  this.http.get<ClubPageResponse>(`${this.rootUrl.rootUrl}${this.ClubUrl}`,{params:{...param}})
  }

  findClubsById(id:number){
    return this.http.get<ClubResponse>(`${this.rootUrl.rootUrl}${this.ClubUrl}/${id}`)
  }

  updateClub(id:number,request:ClubRequest,file:File|null){
    const formData = new FormData()
    if (request != null){
      formData.append("request",new Blob([JSON.stringify(request)], {type: 'application/json'}))
    }
    if (file != null){
      formData.append("file",file)
    }
  return this.http.patch<number>(`${this.rootUrl.rootUrl}${this.ClubUrl}/${id}`,formData)

  }

  getAllClubsBySportId(){
    return this.http.get<Array<ClubResponse>>(`${this.rootUrl.rootUrl}${this.ClubSportUrl}`)
  }

}
