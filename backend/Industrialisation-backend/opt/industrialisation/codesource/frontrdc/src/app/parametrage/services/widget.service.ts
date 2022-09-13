import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Profil } from '../models/profil';

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

    allWidgetByProfil(profil: any) {
        return this.http.post<any>(this.api + 'listwidgetprofil',profil);
    }
    allWidgetbyprofilNoAttr(profil: any) {
        return this.http.post<any>(this.api + 'listwidgetprofilnoattr',profil);
    }

    attribuerWidget(p:Profil, removed:any[], added:any[], updated:any[]) {    
        //let formData = new FormData();
        //formData.append("removed", removed);
        //formData.append("added", added);
        let body = { 
          "profil":p,
          "removed": removed,
          "added": added
        }
        let profil = {
          "proId":p.proId
        }
        let formData = new FormData();
        formData.append("profil", JSON.stringify(profil));
        formData.append("removed", JSON.stringify(removed));
        formData.append("added", JSON.stringify(added));
        formData.append("updated", JSON.stringify(updated));
        return this.http.post<any>(this.api + 'attribuer/allocatewidget', formData);
      }
}