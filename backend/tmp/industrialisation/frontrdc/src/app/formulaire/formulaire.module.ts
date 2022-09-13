import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FormulaireRoutingModule } from './formulaire-routing.module';
import { MaterialModule } from '../shared/material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FlexLayoutModule } from '@angular/flex-layout';
import { HttpClientModule } from '@angular/common/http';
import { SharedcomponentModule } from '../sharedcomponent/sharedcomponent.module';
import { MainContentFormComponent } from './components/main-content-form/main-content-form.component';
import { FormulaireComponent } from './formulaire.component';
import { AjouterFormComponent } from './components/ajouter-form/ajouter-form.component';
import { DndModule } from 'ngx-drag-drop';
import { SweetAlert2Module } from '@toverux/ngx-sweetalert2';
import { ViewFormComponent } from './components/view-form/view-form.component';
import { EditFormComponent } from './components/edit-form/edit-form.component';
import { ModeliserFormComponent } from './components/modeliser-form/modeliser-form.component';

@NgModule({
  declarations: [FormulaireComponent, MainContentFormComponent, AjouterFormComponent, ViewFormComponent, EditFormComponent, ModeliserFormComponent],
  imports: [
    CommonModule,
    FormulaireRoutingModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    FlexLayoutModule,
    HttpClientModule,
    SharedcomponentModule,
    SweetAlert2Module.forRoot(),
    DndModule
  ]
})
export class FormulaireModule { }
