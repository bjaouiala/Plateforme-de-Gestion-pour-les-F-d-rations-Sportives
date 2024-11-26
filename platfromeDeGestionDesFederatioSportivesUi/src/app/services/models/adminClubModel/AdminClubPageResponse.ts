import { AdminResponse } from "./AdminResponse"

export interface AdminClubPageResponse{
    content?:Array<AdminResponse>
    number?:number
    size?:number
    totalElement?:number
    totalPages?:number
    first?:number
    last?:number
}