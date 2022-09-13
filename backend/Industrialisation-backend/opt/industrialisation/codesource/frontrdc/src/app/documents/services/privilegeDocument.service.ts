import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Profil } from 'src/app/home/models/profil';
import { HttpRequestHelper } from 'src/app/shared/helpers/httprequest.helper';
import { environment } from 'src/environments/environment';
import { TypeDocuments } from '../model/TypeDocuments';

@Injectable({
  providedIn: 'root'
})
export class PrivilegeDocumentService {

  api = environment.apii;
  constructor(private http: HttpClient, private reqHelper: HttpRequestHelper) { }

listPriveleges(p:TypeDocuments) {
  return this.http.post<any>(this.api + 'privilegeDocument/privilegebyprofil', p);
}
listUtilisateurByIdTypD(id) {
  return this.http.get<any>(this.api + 'privilegeDocument/utilisateur/'+id);
}


allocatePriveleges(p:TypeDocuments, removed:any[], added:any[]) {
  //let formData = new FormData();
  //formData.append("removed", removed);
  //formData.append("added", added);
  let body = {
    "typeDocument":p,
    "removed": removed,
    "added": added
  }
  let typeDocuments = {
    "tydId":p.tydId
  }
  let formData = new FormData();
  formData.append("typeDocument", JSON.stringify(typeDocuments));
  formData.append("removed", JSON.stringify(removed));
  formData.append("added", JSON.stringify(added));
  return this.http.post<any>(this.api + 'privilegeDocument/allocateprivilege', formData, { 'headers': this.reqHelper.getReqOptions("privilege_document") });
}
}
