import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { PasswordRecover } from '../../utilisateur/models/passwordRecover';
 

@Injectable({
  providedIn: 'root'
})
export class ResetpwdService {
 api = environment.apii;
  constructor(private http: HttpClient) { }


 sendToken(utiEmail: PasswordRecover) {
    return this.http.post<any>(this.api + 'users/recover', utiEmail);
  }
  resetPassword(resetpwd: PasswordRecover) {
    return this.http.post<any>(this.api + 'users/resetpwd', resetpwd);
  }
 verifToken(token) {
    return this.http.get(this.api + 'users/veriftoken/'+token);
  }

}