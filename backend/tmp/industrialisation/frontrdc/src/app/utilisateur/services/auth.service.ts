import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { JwtHelperService } from '@auth0/angular-jwt';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  public jwtHelper: JwtHelperService = new JwtHelperService();

  constructor(private http: HttpClient) { }
  api = environment.apii;
  public isAuthenticated() {
    const token = localStorage.getItem('token');
    // Check whether the token is expired and return
    // true or false
    return !this.jwtHelper.isTokenExpired(token);
  }
  login(username: string, password: string) {


  const body = { username: username, password: password };
  const bearer = btoa(username + ':' + password);  
  const headers = new HttpHeaders({ 'Authorization': 'Basic ' + bearer });
   return this.http.post<any>(this.api + 'auth', body);
  }
  VerifierConnexion(username:string){
    return this.http.get<any>(this.api + 'users/verifySession/' + username);
  }

  getMenus() { 
    return this.http.get<any>(this.api + 'menu');
  }

  getMenusProfil(p) { 
    //alert("-------------getMenusProfil--------------"+JSON.stringify(p));
    return this.http.get<any>(this.api + 'menu/profil/'+p);
  }

  deconnecter(id: string) {    
    return this.http.get<any>(this.api + 'disconnect/logout/' + id);

  }

}

