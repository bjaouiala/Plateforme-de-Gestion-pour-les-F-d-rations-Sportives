import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BaseServiceService} from "../base-service.service";
import {PaginationParams} from "../PaginationParams";
import {EventRequest} from "../models/event/eventRequest";
import {EventPageResponse} from "../models/event/eventPageResponse";
import {EventResponse} from "../models/event/eventResponse";

@Injectable({
  providedIn: 'root'
})
export class EventService {

  private readonly eventUrl:string="/events"
  private readonly allEventUrl:string="/events/all"
  constructor(private http:HttpClient,private rootUrl:BaseServiceService) { }

  addEvent(event:EventRequest,file:File | null){
    const formData = new FormData()
    formData.append("eventRequest",new Blob([JSON.stringify(event)],{type:'application/json'}))

    if (file != null){
      formData.append("file",file)
    }
    return this.http.post<number>(`${this.rootUrl.rootUrl}${this.eventUrl}`,formData)
  }
  updateEvent(event:EventRequest,file:File | null,id:number){
    const formData = new FormData()
    formData.append("eventRequest",new Blob([JSON.stringify(event)],{type:'application/json'}))

    if (file != null){
      formData.append("file",file)
    }
    return this.http.patch<number>(`${this.rootUrl.rootUrl}${this.eventUrl}/${id}`,formData)
  }

  getEventByStatus(pageParams:PaginationParams,status:string){
    return this.http.get<EventPageResponse>(`${this.rootUrl.rootUrl}${this.eventUrl}`,{params:{...pageParams,status}})
  }
  getAllEvent(pageParams:PaginationParams){
    return this.http.get<EventPageResponse>(`${this.rootUrl.rootUrl}${this.allEventUrl}`,{params:{...pageParams}})
  }
  getEventById(id:number){
    return this.http.get<EventResponse>(`${this.rootUrl.rootUrl}${this.eventUrl}/${id}`)
  }
}
