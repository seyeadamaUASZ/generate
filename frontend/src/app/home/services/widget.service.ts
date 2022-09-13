import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Profil } from '../models/profil';
import * as FileSaver from 'file-saver';
import * as XLSX from 'xlsx';

const EXCEL_TYPE = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8';
const EXCEL_EXTENSION = '.xlsx';
@Injectable({
    providedIn: 'root'
})

export class WidgetService {
    api = environment.apii;
    constructor(private http: HttpClient) { }

    listeWidget() {
        return this.http.get<any>(this.api + 'listwidget');
    }

    infoWidget(id: any) {
        return this.http.get<any>(this.api + 'widget', id);
    }

    allWidgetByProfilId(id: any) {
        return this.http.get<any>(this.api + 'listwidgetbyprofilid/'+id);
    }

    listeStatProfil() {
        return this.http.get<any>(this.api + 'widget/getStatProfil');
    }

    listTemplateAttributByWidget(id:any){
        return this.http.get<any>(this.api + 'attribuertemplwidget/widgettemplatesAttr/'+id);
    }

    checkProfil(id:any){
        return this.http.get<any>(this.api + 'profil/'+id);
    }

    checkWidgetTemplateAttr(id:any){
        return this.http.get<any>(this.api + 'attribuertemplwidget/widgettemplatesAttrbyProfile/'+id);
    }

    nbrIndusUtiParDate() {
        return this.http.get<any>(this.api + 'utilisateur/nbrindusutipardate');
      }
       UtiParDateExport() {
        return this.http.get<any>(this.api + 'utilisateur/utipardateexport');
      }
      

      nbrIndusUtiParProfil() {
        return this.http.get<any>(this.api + 'utilisateur/nbrindusutiparprofil');
      }
      
      nbrCourbeIndusUtiParProfil() {
        return this.http.get<any>(this.api + 'utilisateur/nbrcourbeindusutiparprofil');
      }

      nbrCourbeIndusUtiParAnnee() {
        return this.http.get<any>(this.api + 'utilisateur/nbrindusutiparannee');
      }
      nbrIndusUtiParProfilParAnnee() {
        return this.http.get<any>(this.api + 'utilisateur/nbrindusutiparprofilparannee');
      }
      

      public exportAsExcelFile(json: any[], excelFileName: string): void {
        const myworksheet: XLSX.WorkSheet = XLSX.utils.json_to_sheet(json);
        const myworkbook: XLSX.WorkBook = { Sheets: { 'data': myworksheet }, SheetNames: ['data'] };
        const excelBuffer: any = XLSX.write(myworkbook, { bookType: 'xlsx', type: 'array' });
        this.saveAsExcelFile(excelBuffer, excelFileName);
      }
      
      private saveAsExcelFile(buffer: any, fileName: string): void {
        const data: Blob = new Blob([buffer], {
          type: EXCEL_TYPE
        });
        FileSaver.saveAs(data, fileName + 'Etablissement'+ EXCEL_EXTENSION);
      }
      
      
}
