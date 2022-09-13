import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CapteurSService {
  api = environment.apii;
  apiCapteur="https://arduino.openumeric.com/api/";
  constructor(private http:HttpClient) { }

  allCapteurs(){
    return this.http.get<any>(this.api+'capteur/list')
  }

  addCapteur(data){
    return this.http.post<any>(this.api+'capteur/add',data)
  }

  deleteCapteur(id){
    return this.http.get<any>(this.api+'capteur/deletecapteur/'+id)
  }

  modifierCapteur(id,data){
    return this.http.post<any>(this.api+'capteurs/update/'+id,data)
  }

  donneesRecupereesCapteurs(){
   return  this.http.get(this.apiCapteur+'sensors');
  }
}
