import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BaseServiceService} from "../base-service.service";
import {SportPageResponse} from "../models/sportModel/SportPageResponse";
import {map} from "rxjs";
import {PaginationParams} from "../PaginationParams";



@Injectable({
  providedIn: 'root'
})
export class SportService {
  private readonly findAllSportUrl:string="/sports"

  constructor(private httpClient:HttpClient,private baseService:BaseServiceService) { }

  
    findAllSports(params:PaginationParams){
    return this.httpClient.get<SportPageResponse>(`${this.baseService.rootUrl}${this.findAllSportUrl}`,{params:{...params}})

  }
}




