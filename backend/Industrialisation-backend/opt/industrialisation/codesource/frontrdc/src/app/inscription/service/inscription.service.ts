import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders} from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Inscription } from '../model/inscription';@Injectable({
  providedIn: 'root'
})
export class InscriptionService {
api = environment.apii;
  constructor(private http:HttpClient) {  }
  
  createInscription(data){
       return this.http.post(this.api+"inscription/create",data)
  }
  getInscriptionAll(){
        return this.http.get(this.api+"inscription/list")
  } 
  deleteInscription(data){
    return this.http.post(this.api+"inscription/delete",data)
}
 
  addInscription(file:any, inscription:Inscription){
    const formData = new FormData();
    formData.append('inscription', file);
    formData.append('inscription', JSON.stringify(inscription));
    return this.http.post<any>(this.api + 'inscription/createfile', formData);
  }
 download(id) {
    let headers = new HttpHeaders();
    //headers = headers.append('Accept', 'image/png');
    return this.http.get(this.api + 'inscription/downloadFile/'+id,
    {
      headers: headers,
      observe: 'response',
      responseType:'arraybuffer'});
  }}
