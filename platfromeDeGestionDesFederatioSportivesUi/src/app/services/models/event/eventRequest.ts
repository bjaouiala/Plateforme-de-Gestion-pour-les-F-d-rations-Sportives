export interface EventRequest{
  id?:number
  name?:string
  description?:string
  startDate?:Date
  endDate?:Date
  location?:string
  status?:Status

}
export enum Status{
  Pending="PENDING",
  CANCELED="CANCELED",
  FINISHED="FINISHED"

}
