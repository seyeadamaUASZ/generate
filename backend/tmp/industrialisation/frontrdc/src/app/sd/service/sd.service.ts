import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { environment } from 'src/environments/environment';
@Injectable({
  providedIn: 'root'
})
export class CommandeService {
api = environment.apii;
  constructor(private http:HttpClient) {  }
  
  createCommande(data){
       return this.http.post(this.api+"commande/create",data)
  }
  getCommandeAll(){
        return this.http.get(this.api+"commande/list")
  } 
  deleteCommande(data){
    return this.http.post(this.api+"commande/delete",data)
}
}
