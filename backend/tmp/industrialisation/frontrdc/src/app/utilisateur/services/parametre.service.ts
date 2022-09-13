import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { User } from '../models/user';
import { Parametre } from '../models/parametre';
import {Observable} from 'rxjs';
import { from } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ParametreService {
  api_base = environment.apii;
  constructor(private http: HttpClient) { }

  getDeps() {
    return this.http.get<any>(this.api_base + 'dep_fonc/departements');
  }


  getPays() {
    return this.http.get<any>(this.api_base + 'pays');
  }

  getFoncs() {
    return this.http.get<any>(this.api_base + 'dep_fonc/fonctions');
  }

  createDep(departement: any) {
    return this.http.post<any>(this.api_base + 'dep_fonc/departement/create', departement);
  }

  createPays(pays: any) {
    return this.http.post<any>(this.api_base + 'pays/create', pays);
  }
  createSecteur(secteur: any) {
    return this.http.post<any>(this.api_base + 'create/secteur', secteur);
  }
  updateSecteur(secteur: any) {
    return this.http.post<any>(this.api_base + 'update/secteur', secteur);
  }

  createFonc(fonction: any) {
    return this.http.post<any>(this.api_base + 'dep_fonc/fonction/create', fonction);
  }

  updateDep(departement: any) {
    return this.http.post<any>(this.api_base + 'dep_fonc/departement/update', departement);
  }
  updatePays(pays: any) {
    return this.http.post<any>(this.api_base + 'pays/update', pays);
  }
  updateFonc(fonction: any) {
    return this.http.post<any>(this.api_base + 'dep_fonc/fonction/update', fonction);
  }

  deletePays(pays: any) {
    return this.http.post<any>(this.api_base + 'pays/delete', pays);
  }

  deleteDep(departement: any) {
    return this.http.post<any>(this.api_base + 'dep_fonc/departement/delete', departement);
  }

  deleteFonc(fonction: any) {
    return this.http.post<any>(this.api_base + 'dep_fonc/fonction/delete', fonction);
  }
  getTheme() {
    return this.http.get<any>(this.api_base + 'theme/list');
  }
  getLangue() {
    return this.http.get<any>(this.api_base + 'langue');
  }
  updateLangueUser(user: User) {
    return this.http.post<any>(this.api_base + 'langue/update', user);
  }
  updateParametre(parametre: Parametre) {
    //alert("---------------updateParametre----------"+JSON.stringify(parametre));
    return this.http.post<any>(this.api_base + 'parametre/update', parametre);
  }
  getDefautParametre(){
    return this.http.get<any>(this.api_base + 'parametre');
  }

uplaodImage(formData:FormData){
  //alert(this.api_base+ 'upload');
return this.http.post<any>(this.api_base+ 'upload/', formData)
}
getImage(imageName){
 return this.http.get<any>(this.api_base +'get/' + imageName)
}
}
