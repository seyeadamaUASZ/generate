import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DatePipe } from '@angular/common';
import { ApplicationRoutingModule } from './application-routing.module';
import { MainContentComponent } from './components/main-content/main-content.component';
import { AjoutAppComponent } from './components/ajout-app/ajout-app.component';
import { MatExpansionModule, MatDialogModule, MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { ApplicationAppComponent } from './application-app.component';
import { MaterialModule } from '../shared/material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FlexLayoutModule } from '@angular/flex-layout';
import { HttpClientModule } from '@angular/common/http';
import { SharedcomponentModule } from '../sharedcomponent/sharedcomponent.module';
import { AjoutModuleComponent } from './components/ajout-module/ajout-module.component';
import { DetailModuleComponent } from './components/detail-module/detail-module.component';
import { EditModuleComponent } from './components/edit-module/edit-module.component';
import {MatStepperModule} from '@angular/material/stepper';
import { FonctionnaliteComponent } from './fonctionnalite/fonctionnalite.component';
import { EditAppComponent } from './components/detail-app/edit-app.component';
import { EtapeCreationAppComponent } from './components/edit-app/etape-creation-app.component';
import {MatListModule} from '@angular/material/list';
import { AjoutFoncComponent } from './fonctionnalite/ajout-fonc/ajout-fonc.component';


@NgModule({

  declarations: [ApplicationAppComponent, MainContentComponent,EditAppComponent, AjoutAppComponent,EtapeCreationAppComponent, AjoutModuleComponent, DetailModuleComponent, EditModuleComponent, EditAppComponent, FonctionnaliteComponent, AjoutFoncComponent],
  imports: [
    ApplicationRoutingModule,
    MatExpansionModule,
    CommonModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    FlexLayoutModule,
    HttpClientModule,
    SharedcomponentModule,
    MatStepperModule,
    MatListModule,
    MatDialogModule

  ],
  providers: [DatePipe,
    { provide: MAT_DIALOG_DATA, useValue: {} },
    { provide: MatDialogRef, useValue: {} }],
})
export class ApplicationModule { }
