import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProceduresComponent } from './components/procedures.component';
import { MaterialModule } from '../shared/material.module';
import { SharedcomponentModule } from '../sharedcomponent/sharedcomponent.module';
import { FlexLayoutModule } from '@angular/flex-layout';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ProdeduresRoutingModule } from './procedures-routing';
import { ProceduresAppComponent } from './procedures-app-component';
import { ExecutionComponent } from './execution/execution.component';
import { SecteursComponent } from './secteurs/secteurs.component';
import { AddsecteurComponent } from './addsecteur/addsecteur.component'; 
import { TaskComponent } from './task/task.component';
import { ProcessComponent } from './process/process.component';
@NgModule({
  declarations: [ProceduresAppComponent, ProceduresComponent, ExecutionComponent, SecteursComponent, AddsecteurComponent, TaskComponent, ProcessComponent, ],
  imports: [
    CommonModule,
    ProdeduresRoutingModule,
    MaterialModule, 
    FormsModule,
    ReactiveFormsModule,
    FlexLayoutModule,
    HttpClientModule,
    SharedcomponentModule
  ]
})
export class ProceduresModule { }
