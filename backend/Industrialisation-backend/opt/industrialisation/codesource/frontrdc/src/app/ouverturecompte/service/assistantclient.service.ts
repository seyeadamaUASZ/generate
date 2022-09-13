import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AssistantclientService {
  api = environment.apii;
  constructor(private http: HttpClient) { }

  createAssistantclient(data ){
  	   const formData = new FormData();
  	   
  	   formData.append('assistantclient',JSON.stringify(data));
  	   
       return this.http.post(this.api+"assistantclient/create",formData);
  }
  getAssistantclientAll(owner){
        return this.http.get(this.api+"assistantclient/list/"+owner)
  }
  getByIdLink(idlink){
    return this.http.get(this.api+"assistantclient/"+idlink);
  }
  deleteAssistantclient(data){
    return this.http.post(this.api+"assistantclient/delete",data)
}
  getTask(poowner){
    return this.http.get(this.api+"assistantclient/task/"+poowner)
 }
 getStatus(taskId){
  return this.http.get(this.api+"transition/statusAfterExecution/"+taskId)
 }
updateTaskAssistantclient(id,status){
  return this.http.get(this.api+"assistantclient/status/"+id+"/"+status)
}

getAllTask(){
  return this.http.get(this.api+"task/list")
}
getTaskTraite(poowner,profil){
  return this.http.get(this.api+"assistantclient/task/traite/"+poowner+"/"+profil);
}

}
