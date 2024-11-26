export interface ClubResponse{
  id?: number
  name?: string;
  address?: string;
  phoneNumber?: string;
  email?: string
  clubPicture?: Array<string>
  description?: string
  rate?:number
}
