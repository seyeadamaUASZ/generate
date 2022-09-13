import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Application } from 'src/app/utilisateur/models/application';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class ApplicationService {
  constructor(private http: HttpClient) { }
  api = environment.apii;

  updateEtapeCreationApp(data:any) {
    var formData3: FormData = new FormData();
    formData3.append('etape', data.etape);
    formData3.append('idApp', data.idApp);
    return this.http.post<any>(this.api + 'application/updateEtape', formData3);
  }
  getDetailApp(id) {
    return this.http.get<any>(this.api + 'application/' + id);
  }
  // getFichierLibre() {
  //   return this.http.get<any>(this.api + 'fichierByApp');
  // }
  getFichierLibre() {
    return this.http.get<any>(this.api + 'generateRapportLibre');
  }

  getFichierLibreOuSpecifique(id) {
    return this.http.get<any>(this.api + 'fichierByAppOuLibre/'+id);
  }
  getFormulaireLibre() {
    return this.http.get<any>(this.api + 'formulaire/formulaireByApp');

  }
  getFormulaireLibreOuSpecifique(id) {
    return this.http.get<any>(this.api + 'formulaire/formulaireByAppOuLibre/'+id);

  }
  getWorkflowLibre() {
    return this.http.get<any>(this.api + 'WorkflowByApp');

  }
  getWorkflowLibreOuSpecifique(id) {
    return this.http.get<any>(this.api + 'WorkflowByAppOuLibre/'+id);

  }
  LierWorkflowEtApp(data: any) {
    var formData1: FormData = new FormData();
    formData1.append('idWorkflow', data.idWorkflow);
    formData1.append('idApp', data.idApp);
    return this.http.post<any>(this.api + 'workflowLierApp', formData1);

  }
  EnleverliaisonWorkflowEtApp(idWorkflow) {
    return this.http.get<any>(this.api + 'workflowLierApp/enlever/'+ idWorkflow);

  }

  LierFormulaireEtApp(data: any) {
    var formData2: FormData = new FormData();
    formData2.append('idFormulaire', data.idFormulaire);
    formData2.append('idApp', data.idApp);
    return this.http.post<any>(this.api + 'formulaire/formulaireLierApp', formData2);

  }
  EnleverliaisonFormulaireEtApp(idFormulaire) {
    return this.http.get<any>(this.api + 'formulaire/formulaireLierApp/enlever/'+ idFormulaire);

  }

  LierFichierEtApp(data: any) {
    var formData3: FormData = new FormData();
    formData3.append('idFichier', data.idFichier);
    formData3.append('idApp', data.idApp);
    return this.http.post<any>(this.api + 'fichierLierApp', formData3);
  }

  EnleverliaisonFichierEtApp(idFichier) {
    return this.http.get<any>(this.api + 'fichierLierApp/enlever/'+ idFichier);

  }
  EnleverliaisonTableLiaison(id) {
    return this.http.get<any>(this.api + 'enleverLiaisonApp/enlever/'+ id);

  }



  zipper(id) {
    return this.http.get(this.api + 'zipper/'+id);
  }
  downloadFile(id): any{
		return this.http.get(this.api + 'zipper/'+id, {responseType: 'blob'});
   }
  /* downloadFile(): Observable<any>{
     return this.hh.get(this.api+'download', {responseType: ResponseContentType.Blob});
   }*/






}
