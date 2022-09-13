import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { HttpRequestHelper } from 'src/app/shared/helpers/httprequest.helper';
import { User } from 'src/app/utilisateur/models/user';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Inscription } from 'src/app/inscription/model/inscription';

@Injectable({
  providedIn: 'root'
})
export class ServiceVisageService {
//apiReconnaissance = environment.apiReconnaissance;
apiReconnaissance1 = environment.apiReconnaissance1
  constructor(private http: HttpClient,private reqHelper: HttpRequestHelper) { }
  enrolUser(utilisateur:User){
    const {utiPrenom, utiNom, utiUsername, utiTelephone, utiEmail, utiAdresse} = utilisateur;
     const formData: FormData = new FormData();
     formData.append('utiPrenom',utiPrenom);
     formData.append('utiNom', utiNom);
     formData.append('utiUsername', utiUsername);
     formData.append('utiTelephone', utiTelephone);
     formData.append('utiEmail', utiEmail);
     formData.append('utiAdresse', utiAdresse);
    return this.http.post<any>(this.apiReconnaissance1+ 'utilisateur/enroleUser',formData);
  }

  inscriptionVisage(inscription:Inscription){
    const {insPrenom, insNom, insUsername, insTelephone, insEmail} = inscription;
     const formData: FormData = new FormData();
     formData.append('insPrenom',insPrenom);
     formData.append('insNom', insNom);
     formData.append('insUsername', insUsername);
     formData.append('insTelephone', insTelephone);
     formData.append('insEmail', insEmail);
    return this.http.post<any>(this.apiReconnaissance1+ 'inscription/capture',formData);
  }
  
  loginByVisage(){
    return this.http.get<any>(this.apiReconnaissance1+ 'utilisateur/loginVisage');
  }
}
