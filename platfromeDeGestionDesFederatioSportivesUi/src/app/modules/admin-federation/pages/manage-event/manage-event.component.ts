import {Component, OnInit} from '@angular/core';
import {EventService} from "../../../../services/Event/event.service";
import {EventRequest} from "../../../../services/models/event/eventRequest";
import {ToastrService} from "ngx-toastr";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-manage-event',
  templateUrl: './manage-event.component.html',
  styleUrls: ['./manage-event.component.css']
})
export class ManageEventComponent implements OnInit{
  eventRequest:EventRequest={}
  selectedPicture: string|null=null;
  eventFile:File | null = null
  constructor(private eventService:EventService
              ,private toastService:ToastrService
              ,private router:Router,
              private activateRoute:ActivatedRoute) {
  }
  ngOnInit(): void {
    const eventId = this.activateRoute.snapshot.params["id"]
    if (eventId){
      this.eventService.getEventById(eventId).subscribe({
        next : event =>{
          this.eventRequest = event
          if (event.eventPicture){
            this.selectedPicture= 'data:image/jpg;base64,'+event.eventPicture
          }
        }
      })

    }
  }

  saveEvent(){
    const eventId = this.activateRoute.snapshot.params["id"]
    if (eventId){
      this.eventService.updateEvent(this.eventRequest,this.eventFile,eventId).subscribe({
        next : res => {
          this.toastService.success("event updated successfully")
          this.router.navigate(['/admin/all-Event'])
        } ,error : err =>{
          this.toastService.error("something went wrong")
        }
      })

    }else {
      this.eventService.addEvent(this.eventRequest,this.eventFile).subscribe({
        next: res =>{
          this.toastService.success("event added successfully")
          this.router.navigate(['/admin/all-Event'])
        },
        error : err =>{
          this.toastService.error("something went wrong")
        }
      })

    }

  }

  onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement
    if (input.files && input.files.length > 0){
      this.eventFile = input.files[0]
      const reader = new FileReader()
      reader.onload= () => {
        this.selectedPicture = reader.result as string
      }
      reader.readAsDataURL(this.eventFile)
    }



  }
}
