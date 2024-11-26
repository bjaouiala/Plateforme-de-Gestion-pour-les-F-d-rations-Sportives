import {Component, EventEmitter, Input, Output} from '@angular/core';
import {ClubResponse} from "../../../../services/models/clubModel/ClubResponse";

@Component({
  selector: 'app-club-card',
  templateUrl: './club-card.component.html',
  styleUrls: ['./club-card.component.css']
})
export class ClubCardComponent {

  private _clubPicture:string|undefined
  @Input()
  club: ClubResponse={}
  @Output()
  private details: EventEmitter<ClubResponse> = new EventEmitter<ClubResponse>()
  @Output()
  private edit: EventEmitter<ClubResponse> = new EventEmitter<ClubResponse>()
  get clubPicture(): string | undefined {
    if (this.club.clubPicture){
      return 'data:image/jpg;base64, '+this.club.clubPicture
    }
    return this._clubPicture;
  }
  onShowDetails() {
    this.details.emit()

  }

  onEdit() {
    this.edit.emit(this.club)
  }
}
