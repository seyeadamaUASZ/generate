import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpRequestHelper } from 'src/app/shared/helpers/httprequest.helper';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ServicesService {
  api = environment.apii;
  constructor(private http: HttpClient, private reqHelper: HttpRequestHelper) { }

  getFingerZk() {
    return this.http.get<any>(this.api + 'zk/ourirlecapteur', { 'headers': this.reqHelper.getReqOptions("list_user") });
  }
  identFingerZk() {
    return this.http.get<any>(this.api + 'zk/actionidentificationFinger', { 'headers': this.reqHelper.getReqOptions("list_user") });
  }
  enrolFingerZk() {
    return this.http.get<any>(this.api + 'zk/enrollementuser', { 'headers': this.reqHelper.getReqOptions("list_user") });
  }

  identUser() {
    return this.http.get<any>(this.api + 'zk/identificationuser', { 'headers': this.reqHelper.getReqOptions("list_user") });
  }

  findByEmpreinte(id) {
    return this.http.get<any>(this.api + 'zk/findbyempriente/'+id, { 'headers': this.reqHelper.getReqOptions("list_user") });
  }
}
