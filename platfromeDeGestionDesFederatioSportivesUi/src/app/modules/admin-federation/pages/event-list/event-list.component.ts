import {Component, OnInit} from '@angular/core';
import {EventService} from "../../../../services/Event/event.service";
import {EventPageResponse} from "../../../../services/models/event/eventPageResponse";
import {EventResponse} from "../../../../services/models/event/eventResponse";
import {Status} from "../../../../services/models/event/eventRequest";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-event-list',
  templateUrl: './event-list.component.html',
  styleUrls: ['./event-list.component.css']
})
export class EventListComponent implements OnInit{
  eventPageResponse: EventPageResponse={}
  page:number=0
  size:number=8
  isDropdwon: boolean=false;
  constructor(private eventService:EventService, private toast:ToastrService) {
  }
  ngOnInit(): void {
    this.getAllEvent()
  }
  getAllEvent(){
    this.eventService.getAllEvent({page:this.page,size:this.size}).subscribe({
      next : response=> {
        this.eventPageResponse = response

        this.isDropdwon = false
      }
    })
  }


  isLast() {
    return this.page == this.eventPageResponse.totalPages as number -1
  }


  next() {
    this.page++
    this.getAllEvent()
  }


  goToPage(currentPage: number) {
    this.page = currentPage
    this.getAllEvent()
  }


  previous() {
    this.page--
    this.getAllEvent()
  }


  goToFirstPage() {
    this.page = 0
    this.getAllEvent()
  }

  goToLastPage() {
    this.page = this.eventPageResponse.totalPages as number - 1
    this.getAllEvent()
  }

  protected readonly Status = Status;

  getEventByStatus(status: Status) {
    this.eventService.getEventByStatus({page:this.page,size:this.size},status).subscribe({
      next: response =>{
        this.eventPageResponse = response
        this.isDropdwon = false
      },
      error : err => {
        this.toast.error("something went wrong")
      }
    })
  }
}
