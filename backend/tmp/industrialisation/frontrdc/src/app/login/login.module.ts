import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LoginRoutingModule } from './login-routing.module';
import { AvatarModule } from 'ngx-avatar';
import { SharedcomponentModule } from '../sharedcomponent/sharedcomponent.module';
import { HttpClientModule } from '@angular/common/http';
import { FlexLayoutModule } from '@angular/flex-layout';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { MaterialModule } from '../shared/material.module';
import { LoginComponent } from './login.component';
import { PremiereConnectComponent } from './premiere-connect/premiere-connect.component';
import { ForgetPwdComponent } from './forget-pwd/forget-pwd.component';
import { NewPwdComponent } from './new-pwd/new-pwd.component';

import { AppLoginComponent } from './login-app.component';

@NgModule({
  declarations: [AppLoginComponent,LoginComponent,PremiereConnectComponent,ForgetPwdComponent,NewPwdComponent],
  imports: [
    CommonModule,
    LoginRoutingModule,
    CommonModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    FlexLayoutModule,
    HttpClientModule,
    SharedcomponentModule,
    AvatarModule

  ]
})
export class LoginModule { }
