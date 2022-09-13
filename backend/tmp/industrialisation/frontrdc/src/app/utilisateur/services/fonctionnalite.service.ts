import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Fonctionnalite } from 'src/app/utilisateur/models/fonctionnalite';

import { AppFonc } from '../models/appFonc';

@Injectable({
  providedIn: 'root'
})
export class FonctionnaliteService {
  api = environment.apii;
  constructor(private http: HttpClient) { }


  //liste des fonctionnalités par module!!
  listFonctionnalite(id) {
    return this.http.get<any>(this.api + 'fonctionnaliteModule/' + id);
  }

  listFonctionnaliteAppFonc(idApp, idFon) {
    return this.http.get<any>(this.api + 'listeAppFonc/'+ idApp+'/'+idFon);
  }

  IsActiveOuNon(idApp, idFon){

    return this.http.get<any>(this.api + 'testeSiActiveOuNon/' + idApp+'/'+idFon);
  }

  //creation d'une fonctionnalite
  creatFonctioonalite(fonctionnalite: Fonctionnalite) {
    return this.http.post<any>(this.api + 'fonctionnalite', fonctionnalite);

  }

  creatAppFonc(appfonc:AppFonc) {
    return this.http.post<any>(this.api + 'ajoutAppFonc', appfonc);

  }
  //update fonctionnalité
  updateFonctionnalite(fonctionnalite: Fonctionnalite) {
    return this.http.post<any>(this.api + 'updatefonctionnalite', fonctionnalite);

  }

  //suppression d'une fonctionnalite
  deleteFonctionnalite(fonctionnalite: Fonctionnalite) {
    return this.http.post<any>(this.api + 'fonctionnalite/delete', fonctionnalite);
  }

  activerAppFon(idApp, idFon) {
    return this.http.get<any>(this.api + 'ativeAppFonc/' + idApp+'/'+idFon);
  }

  //service desactiver une fonctionnalité!
  desactiverAppFon(idApp, idFon) {
    return this.http.get<any>(this.api + 'desactiveAppFonc/' + idApp+'/'+idFon);
  }

}
