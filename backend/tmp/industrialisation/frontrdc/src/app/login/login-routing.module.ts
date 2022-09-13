import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login.component';
import { PremiereConnectComponent } from './premiere-connect/premiere-connect.component';
import { ForgetPwdComponent } from './forget-pwd/forget-pwd.component';
import { NewPwdComponent } from './new-pwd/new-pwd.component';
import { AppLoginComponent } from './login-app.component';

const routes: Routes = [
  {
    path: '', component: LoginComponent
  },
  { path: 'premiereConnect', component: PremiereConnectComponent },
  { path: 'forgetPwd', component: ForgetPwdComponent },
   { path: 'newPwd/:reset', component: NewPwdComponent },

];



@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LoginRoutingModule { }
