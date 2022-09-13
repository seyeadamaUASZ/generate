import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { User } from '../models/user';
import { Menu } from '../models/menu';
import { environment } from 'src/environments/environment';
import { Profile } from '../models/profile';
import { Application } from '../models/application';
import { Module } from '../models/module';
import { Formulaire } from '../models/formulaire';
import { Champs } from '../models/champs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  api = environment.apii;
  constructor(private http: HttpClient) { }
  getUsers() {
    return this.http.get<any>(this.api + 'utilisateur/list');
  }
  registreUser(user: User) {
    return this.http.post<any>(this.api + 'utilisateur/create/', user);

  }


  chargerFile(formData: FormData) {
    return this.http.post<any>(this.api + 'utilisateur/upload/', formData);
  }

  listprofil() {
    return this.http.get<any>(this.api + 'profil/list');
  }

  listItem() {
    return this.http.get<any>(this.api + 'menu');
  }
  createItem(menu: Menu) {
    return this.http.post<any>(this.api + 'menu/create/', menu);
  }

  createUser(user: User) {
    return this.http.post<any>(this.api + 'utilisateur/create', user);
  }

  updateUser(user: User) {
    return this.http.post<any>(this.api + 'utilisateur/update', user);
  }
  updateProfil(profil: Profile) {
    return this.http.post<any>(this.api + 'profil/update', profil);
  }
  createProfil(profile: Profile) {
    return this.http.post<any>(this.api + 'profil/create', profile);

  }
  recovermdp(data) {
    return this.http.post<any>(this.api + 'utilisateur/recover', data);
  }
  detailUser(username: string) {
    let formdata: FormData = new FormData();
    formdata.append('username', username);
    // const body = {username: username};
    return this.http.post<any>(this.api + 'utilisateur/detail', formdata);
  }

  deleteUser(user: User) {
    return this.http.post<any>(this.api + 'utilisateur/delete', user);
  }
  deleteProfile(profil: Profile) {
    return this.http.post<any>(this.api + 'profil/delete', profil);
  }

  reinitUser(user: User) {
    return this.http.post<any>(this.api + 'utilisateur/reinitialisation', user);
  }
  listeUser() {
    return this.http.get<any>(this.api + 'utilisateur/list');
    //  return this.http.get<any>(this.api_base + 'assets/users.json');

  }

  changepwd(data: any) {
    // tslint:disable-next-line:prefer-const
    let formData: FormData = new FormData();
    formData.append('username', data.username);
    formData.append('oldpwd', data.oldPwd);
    formData.append('newpwd', data.newPwd);
    return this.http.post<any>(this.api + 'utilisateur/changerpwd', formData);
  }



  //service activer compte utilisateur
  activer(id) {
    return this.http.get<any>(this.api + 'utilisateur/actived/' + id);
  }
  //service desactiver compte utilisateur
  desactiver(id) {
    return this.http.get<any>(this.api + 'utilisateur/desactived/' + id);
  } nbrIntegrateur() {
    return this.http.get<any>(this.api + 'utilisateur/nombreIntegrateur');
  }
  nbrAdministrateur() {
    return this.http.get<any>(this.api + 'utilisateur/nombreAdministrateur');
  }
  nbrCommerciaux() {
    return this.http.get<any>(this.api + 'utilisateur/nombreCommerciaux');
  }
  nbrUtilisateur() {
    return this.http.get<any>(this.api + 'utilisateur/nombreUser');
  }
  nbrUserConnect() {
    return this.http.get<any>(this.api + 'utilisateur/nombreUserConnect');
  }
  nbrApplication() {
    return this.http.get<any>(this.api + 'utilisateur/nombreApplication');
  }
  nbrModule() {
    return this.http.get<any>(this.api + 'utilisateur/nombreModule');
  }
  nbrFichier() {
    return this.http.get<any>(this.api + 'fichier/nombreFichier');
  }
  nbrFormulaire() {
    return this.http.get<any>(this.api + 'formulaire/nombreFormulaire');
  }
  nbrWorkflow() {
    return this.http.get<any>(this.api + 'workflow/nombreWorkflow');
  }
  //application
  addApplication(application: Application) {
    return this.http.post<any>(this.api + 'application/create', application);
  }
  listeApplication() {
    return this.http.get<any>(this.api + 'application/list');
  }
  deleteApplication(application: Application) {
    return this.http.post<any>(this.api + 'application/delete', application);
  }
  //module
  addModule(module: Module) {
    return this.http.post<any>(this.api + 'module', module);
  }
  listeModule() {
    return this.http.get<any>(this.api + 'module');
  }
  // listeModuleByApp(idApp) {
  //   return this.http.get<any>(this.api + 'module/'+idApp);
  // }
  listeModuleByApp(id) {
    return this.http.get<any>(this.api + 'get/moduleByAppId/' + id);
  }
  //Formulaire
  addFormulaire(formulaire: Formulaire) {
    return this.http.post<any>(this.api + 'formulaire/create', formulaire);
  }
  listeFormulaire() {
    return this.http.get<any>(this.api + 'formulaire/list');
  }
  listFormulaireByApp(id) {
    return this.http.get<any>(this.api + 'formulaire/formulaireByAppId/' + id);
  }
  listeFormulaireNotgenerer() {
    return this.http.get<any>(this.api + 'formulaire/notgenerateform');
  }
  listeFormulairegenerer() {
    return this.http.get<any>(this.api + 'formulaire/generateform');
  }
  validerFormulaire(formulaire: Formulaire) {
    return this.http.post<any>(this.api + 'formulaire/validerfrm', formulaire);
  }
  modeliserFormulaire(formulaire: Formulaire) {
    return this.http.post<any>(this.api + 'formulaire/modeliserfrm', formulaire);
  }
  deleteFormulaire(formulaire: Formulaire) {
    return this.http.post<any>(this.api + 'formulaire/delete', formulaire);
  }
  //Champs pour un formulaire
  addChamps(champs: Champs) {
    return this.http.post<any>(this.api + 'champs/create', champs);
  }
  supprimerByForm(idFormulaire) {
    return this.http.get<any>(this.api + 'champs/delete/' + idFormulaire);
  }
  listeChamps() {
    return this.http.get<any>(this.api + 'champs/list');
  }
  deleteChamps(champs) {
    return this.http.post<any>(this.api + 'champs/delete',champs);
  }
  genererFormulaire(nomprojet: any, idFormulaire: any) {
    return this.http.get<any>(this.api + 'composant/' + nomprojet + '/' + idFormulaire);
  }
  supprimerFormulaire(nomprojet: any, idFormulaire: any) {
    return this.http.get<any>(this.api + 'supprimer/' + nomprojet + '/' + idFormulaire);
  }
  champsByForm(idFormulaire) {
    return this.http.get<any>(this.api + 'champs/champsByForm/' + idFormulaire);
  }
  fieldChampsByForm(idFormulaire) {
    return this.http.get<any>(this.api + 'champs/fieldByForm/' + idFormulaire);
  }
  fieldValueChamps(idChamps) {
    return this.http.get<any>(this.api + 'value/' + idChamps);
  }
  buttonChampsByForm(idFormulaire) {
    return this.http.get<any>(this.api + 'buttonByForm' + idFormulaire);
  }

  // value pour les champs radio et select
  listeValue() {
    return this.http.get<any>(this.api + 'value/list');
  }
  addValue(data) {
    return this.http.post<any>(this.api + 'value/create', data);
  }
  valueByChamps(id) {
    return this.http.get<any>(this.api + 'valueByChamps/' + id);
  }
  suppressionValueByIdCh(id){
    return this.http.get<any>(this.api + 'value/deletebyidch/' + id);
  }
  getParamUser(username) {
    return this.http.get<any>(this.api + 'utilisateur/get/' + username);
  }
  updateLangueUser(user: User) {
    return this.http.post<any>(this.api + 'utilisateur/updateLangue', user);
  }
  updateThemeUser(user: User) {
    return this.http.post<any>(this.api + 'utilisateur/updateTheme', user);
  }
  getIcone() {
    return this.http.get<any>(this.api + 'icone/list');
  }
 getTable(){
  return this.http.get<any>(this.api + 'gettable');
 }
 getColonne(table){
  return this.http.get<any>(this.api + 'getcolonne/'+table);
 }


}
