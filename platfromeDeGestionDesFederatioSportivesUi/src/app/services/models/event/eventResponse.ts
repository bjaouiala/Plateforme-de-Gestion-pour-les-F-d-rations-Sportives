import {Status} from "./eventRequest";

export interface EventResponse{
  id?:number
  name:string
  description:string
  startDate:Date
  endDate:Date
  location:string
  status:Status
  createdBy:string
  eventPicture?:string
}
