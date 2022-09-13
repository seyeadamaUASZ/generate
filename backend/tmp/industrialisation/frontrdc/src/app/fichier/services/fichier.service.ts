import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Fichier } from '../models/fichier';
import { Rapport } from '../models/rapport';
import { Champs } from '../models/champs';
import { FieldBase } from '../../sharedcomponent/dynamicform/models/field-base';
import { Menu } from 'src/app/utilisateur/models/menu';

@Injectable({
  providedIn: 'root'
})
export class FichierService {

  constructor(private http: HttpClient) { }
  api = environment.apii;
  listFichier() {
    return this.http.get<any>(this.api + 'fichier');
  }

  deleteFichier(fichier: Fichier) {
    return this.http.post<any>(this.api + 'fichier/delete', fichier);

  }
  listFichierByApp(id) {
    return this.http.get<any>(this.api + 'fichierByAppId/' + id);
}

  addRapport(fichierjrxml: any, rapport:Rapport) {
    const formData = new FormData();
    formData.append('jrxmlfile', fichierjrxml);
    formData.append('rapport', JSON.stringify(rapport));
    return this.http.post<any>(this.api + 'rapport', formData);
  }

  editRapport(rapport:Rapport) {
    const formData = new FormData();
    formData.append('rapport', JSON.stringify(rapport));
    return this.http.post<any>(this.api + 'updaterapport', formData);
  }

  editRapportFile(fichierjrxml: any, rapport:Rapport) {
    const formData = new FormData();
    formData.append('jrxmlfile', fichierjrxml);
    formData.append('rapport', JSON.stringify(rapport));
    return this.http.post<any>(this.api + 'updaterapportfile', formData);
  }

  listeRapportNotgenerer() {
    return this.http.get<any>(this.api+ 'notgeneraterapport');
  }

  //Champs pour un formulaire
  addChamps(champs:any) {
    return this.http.post<any>(this.api+ "champsrapport", champs);
  }

  validerRapport(rapport:Rapport) {
    return this.http.post<any>(this.api + 'validerrapport', rapport);
  }

  fieldChampsByRapport(idRapport){
    return this.http.get<any>(this.api + 'champsbyrapport/' +idRapport);
  }
  
  listeRapportGenerer() {
    return this.http.get<any>(this.api + 'generaterapport');
  }

  listeRapportParMenu(menu: Menu) {
    return this.http.post<any>(this.api + 'rapportparmenu', menu);
  }

  modeliserRapport(rapport: Rapport) {
    return this.http.post<any>(this.api + 'modeliserrapport', rapport);
  }

  supprimerChamps(champsId:any) {
    return this.http.post<any>(this.api + 'champsrapport/delete', champsId);
  }

  supprimerChampsByRapport(idRapport) {
    return this.http.get<any>(this.api + 'deletebyrapport/' +idRapport);
  }

  supprimerRapport(idRapport) {
    return this.http.post<any>(this.api + 'supprimerrapport', idRapport);
  }

  convertChampsToField(ch:Champs):FieldBase<string>{
      return new FieldBase<string>({
        key: ch.crtNom,
        label: ch.crtLabel,
        type: ch.crtType,
        required: ch.crtObligatoire                             
     });
  }

  genererRapportPdf(fichier: any,data:any) {
    let formData: FormData = new FormData();
    formData.append('fichier', fichier);
    formData.append('data', data);
    const httpOptions = {
      responseType: 'arraybuffer' as 'json'
      // 'responseType'  : 'blob' as 'json'        //This also worked
    };
    return this.http.post<any>(`${this.api}fichier/pdf`, formData, httpOptions);
  }

  genererRapportExcel(fichier: any,data:any) {
    let formData: FormData = new FormData();
    formData.append('fichier', fichier);
    formData.append('data', data);
    const httpOptions = {
      responseType: 'arraybuffer' as 'json'
      // 'responseType'  : 'blob' as 'json'        //This also worked
    };
    return this.http.post<any>(`${this.api}fichier/excel`, formData, httpOptions);
  }

  listeMenuRapports (rapport: any) {
    return this.http.post<any>(this.api + 'menurapport/menurapportparrapport', rapport);
  }

  allocateMenuRapports(rapport:Rapport, removed:any[], added:any[]) {    
    let formData = new FormData();
    formData.append("rapport", JSON.stringify(rapport));
    formData.append("removed", JSON.stringify(removed));
    formData.append("added", JSON.stringify(added));
    return this.http.post<any>(this.api + 'menurapport/allocatemenurapport', formData);
  }

  getMenusProfil(p) { 
    return this.http.get<any>(this.api + 'menu/profil/'+p);
  }

  createSousMenu (sousMenu: any) {
    return this.http.post<any>(this.api + 'menu/createsousmenurapport', sousMenu);
  }
    
  ListeSousMenuRapport () {
    return this.http.get<any>(this.api + 'menu/sousmenurapport');
  }

  getMenuByPath(menu: any) {
    return this.http.post<any>(this.api+'menu/path', menu);
  }
}
