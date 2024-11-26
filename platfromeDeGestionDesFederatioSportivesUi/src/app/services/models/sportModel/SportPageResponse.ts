import {SportResponse} from "./sportResponse";

export interface SportPageResponse {
  content?:Array<SportResponse>
  number?:number
  size?:number
  totalElement?:number
  totalPages?:number
  first?:number
  last?:number
}
