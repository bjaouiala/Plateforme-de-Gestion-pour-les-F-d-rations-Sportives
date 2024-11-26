import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BaseServiceService {
  private _rootUrl:string="http://localhost:8080/api/v1"

  get rootUrl(): string {
    return this._rootUrl;
  }

  set rootUrl(value: string) {
    this._rootUrl = value;
  }
}
