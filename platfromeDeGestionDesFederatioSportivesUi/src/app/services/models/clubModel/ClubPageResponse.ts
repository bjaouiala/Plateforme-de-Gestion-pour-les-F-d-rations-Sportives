import {SportResponse} from "../sportModel/sportResponse";
import {ClubResponse} from "./ClubResponse";

export interface ClubPageResponse{
  content?:Array<ClubResponse>
  number?:number
  size?:number
  totalElement?:number
  totalPages?:number
  first?:number
  last?:number
}
