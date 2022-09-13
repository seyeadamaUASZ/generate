import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class OuverturecompteService {
  api = environment.apii;
  constructor(private http: HttpClient) { }

  createOuverturecompte(data ,cni:any,certificationresidence:any){
  	   const formData = new FormData();
  	   data.typeservice.join();

  	   formData.append('ouverturecompte',JSON.stringify(data));
  	   
formData.append('cni',cni)
formData.append('certificationresidence',certificationresidence)
       return this.http.post(this.api+"ouverturecompte/create",formData);
  }
  getOuverturecompteAll(owner){
        return this.http.get(this.api+"ouverturecompte/list/"+owner)
  }
  getByIdLink(idlink){
    return this.http.get(this.api+"ouverturecompte/"+idlink);
  }
  deleteOuverturecompte(data){
    return this.http.post(this.api+"ouverturecompte/delete",data)
}
  getTask(poowner){
    return this.http.get(this.api+"ouverturecompte/task/"+poowner)
 }
 getStatus(taskId){
  return this.http.get(this.api+"transition/statusAfterExecution/"+taskId)
 }
updateTaskOuverturecompte(id,status){
  return this.http.get(this.api+"ouverturecompte/status/"+id+"/"+status)
}

getAllTask(){
  return this.http.get(this.api+"task/list")
}
getTaskTraite(poowner,profil){
  return this.http.get(this.api+"ouverturecompte/task/traite/"+poowner+"/"+profil);
}

}
