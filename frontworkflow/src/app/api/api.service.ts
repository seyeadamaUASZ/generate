import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    'Authorization': 'Basic ' + btoa('wbadmin:wbadmin')
  })
};
 const url='http://10.3.20.62:8084/';
@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http:HttpClient) { }

  getContainer(){
     //return this.http.get('http://10.3.20.62:8080/business-central/rest/spaces',httpOptions);
     return this.http.get(url+'getspace');
  }
  /*genererProjet(containerId){
     return this.http.get(url+'component/'+containerId);
  }*/
  genererProjet(packageName,containerId){
    return this.http.get(url+'component/'+packageName+'/'+containerId);
 }
}
