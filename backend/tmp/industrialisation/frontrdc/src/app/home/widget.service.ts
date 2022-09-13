import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Profil } from './models/profil';

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

}