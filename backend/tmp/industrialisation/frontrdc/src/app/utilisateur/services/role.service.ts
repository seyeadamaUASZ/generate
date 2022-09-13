import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Profile } from '../models/profile';
import { environment } from 'src/environments/environment';
import { Action } from '../models/action';


@Injectable({
  providedIn: 'root'
})
export class RoleService {
 api = environment.apii;
 public profilId:number;
  constructor(private http: HttpClient) { }

  listprofils() {
    return this.http.get<any>(this.api + 'profil/list');
  }

  updateProfil(profil: Profile) {
    return this.http.post<any>(this.api + 'profil/update', profil);
  }
  createProfil(profile: Profile) {
    return this.http.post<any>(this.api + 'profil/create', profile);
  }

  deleteProfile(profil: Profile) {
    return this.http.post<any>(this.api + 'profil/delete', profil);
  }
  //action
  listAction() {
    return this.http.get<any>(this.api + 'action/list');
  }

  updateAction(action: Action) {
    return this.http.post<any>(this.api + 'action/update', action);
  }
  createAction(action: Action) {
    return this.http.post<any>(this.api + 'action/create', action);
  }

  deleteAction(action: Action) {
    return this.http.post<any>(this.api + 'action/delete', action);
  }
}
