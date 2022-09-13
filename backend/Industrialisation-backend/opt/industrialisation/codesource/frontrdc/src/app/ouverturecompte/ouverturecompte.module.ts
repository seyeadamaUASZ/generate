import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MaterialModule } from '../shared/material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { SharedcomponentModule } from '../sharedcomponent/sharedcomponent.module';
import { SweetAlert2Module } from '@toverux/ngx-sweetalert2';
import { DndModule } from 'ngx-drag-drop';
import { FlexLayoutModule } from '@angular/flex-layout';
import { OuverturecompteComponent } from './ouverturecompte.component';
import { OuverturecompteRoutingModule } from './ouverturecompte-routing.module';
import { MainContentComponent } from './components/main-content/main-content.component';

import { AddOuverturecompteComponent } from './components/add-ouverturecompte/add-ouverturecompte.component';
import { ViewOuverturecompteComponent } from './components/view-ouverturecompte/view-ouverturecompte.component';
import { AddAssistantclientComponent } from './components/add-assistantclient/add-assistantclient.component';
import { AddGestionnairecompteComponent } from './components/add-gestionnairecompte/add-gestionnairecompte.component';


@NgModule({ 
declarations: [
	OuverturecompteComponent,
	MainContentComponent,
	AddOuverturecompteComponent,
ViewOuverturecompteComponent
,AddAssistantclientComponent
,AddGestionnairecompteComponent

  ],
 imports: [
    CommonModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    FlexLayoutModule,
    HttpClientModule,
    SharedcomponentModule,
    OuverturecompteRoutingModule,
    SweetAlert2Module.forRoot(),
    DndModule
  ],schemas: [ CUSTOM_ELEMENTS_SCHEMA ]
})
export class OuverturecompteModule { }
