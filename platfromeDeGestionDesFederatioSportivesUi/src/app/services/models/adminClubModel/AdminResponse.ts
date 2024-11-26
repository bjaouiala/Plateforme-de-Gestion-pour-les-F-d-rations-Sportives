import { Role } from "../AuthenticationModel/RegistrationrRequest"

export interface AdminResponse{
    id?:number
    firstname?:string
    lastname ?:string
    email?:string
    password?:string
    address?:string
    sportId?:number
    role?: Role
    userPicture?:Array<string>
    accountLocked?:boolean
}