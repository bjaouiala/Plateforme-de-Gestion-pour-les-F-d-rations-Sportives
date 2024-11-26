import {EventResponse} from "./eventResponse";

export interface EventPageResponse{
  content?: Array<EventResponse>
  first?: boolean;
  last?: boolean;
  number?: number;
  size?: number;
  totalElement?: number;
  totalPages?: number;

}
