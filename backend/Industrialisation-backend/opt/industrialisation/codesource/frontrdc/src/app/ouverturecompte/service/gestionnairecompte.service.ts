import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class GestionnairecompteService {
  api = environment.apii;
  constructor(private http: HttpClient) { }

  createGestionnairecompte(data ){
  	   const formData = new FormData();
  	   
  	   formData.append('gestionnairecompte',JSON.stringify(data));
  	   
       return this.http.post(this.api+"gestionnairecompte/create",formData);
  }
  getGestionnairecompteAll(owner){
        return this.http.get(this.api+"gestionnairecompte/list/"+owner)
  }
  getByIdLink(idlink){
    return this.http.get(this.api+"gestionnairecompte/"+idlink);
  }
  deleteGestionnairecompte(data){
    return this.http.post(this.api+"gestionnairecompte/delete",data)
}
  getTask(poowner){
    return this.http.get(this.api+"gestionnairecompte/task/"+poowner)
 }
 getStatus(taskId){
  return this.http.get(this.api+"transition/statusAfterExecution/"+taskId)
 }
updateTaskGestionnairecompte(id,status){
  return this.http.get(this.api+"gestionnairecompte/status/"+id+"/"+status)
}

getAllTask(){
  return this.http.get(this.api+"task/list")
}
getTaskTraite(poowner,profil){
  return this.http.get(this.api+"gestionnairecompte/task/traite/"+poowner+"/"+profil);
}

}
