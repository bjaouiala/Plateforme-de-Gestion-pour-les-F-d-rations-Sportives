import { Role } from "../AuthenticationModel/RegistrationrRequest"

export interface AdminClubRequest{
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