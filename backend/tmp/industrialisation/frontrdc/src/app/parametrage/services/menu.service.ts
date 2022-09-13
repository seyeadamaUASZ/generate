import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';

@Injectable({
  providedIn: 'root'
})
export class MenuService {

  api = environment.apii;
  private actionSource = new BehaviorSubject<any>("");
  currentActions = this.actionSource.asObservable();
    constructor(private http: HttpClient) { }

    changeactions(actions:any[]){
      this.actionSource.next(actions);
    }
    listeMenu() {
        return this.http.get<any>(this.api + 'menu/list');
    }

    infoMenu(id: any) {
        return this.http.get<any>(this.api + 'menu', id);
    }    

    updateMenu(menu: any) {
      return this.http.post<any>(this.api + 'menu/update', menu);
    }

    saveMenu(menu: any) {
      return this.http.post<any>(this.api + 'menu/create', menu);
    }
}
