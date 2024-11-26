export interface RegistrationRequest{
  firstname?:string
  lastname?:string
  email?:string
  password?:string
  address?:string
  sportId?:number

  role?:Role
}

export enum Role{
  ADMIN="ADMIN",
  ADMIN_FEDERATION="ADMIN_FEDERATION",
  ADMIN_CLUB="ADMIN_CLUB",
  PARTENAIRE="PARTENAIRE",
  ATHLETE="ATHLETE",
  LICENCIE="LICENCIE",
  SPECTATEUR="SPECTATEUR"
}
