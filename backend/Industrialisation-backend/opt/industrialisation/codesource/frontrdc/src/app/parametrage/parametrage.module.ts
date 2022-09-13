import { NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { ParametrageRoutingModule } from './parametrage-routing.module';
import { MainContentComponent } from './components/main-content/main-content.component';
import { EditCriterePwdComponent } from './components/edit-critere-pwd/edit-critere-pwd.component';
import { MatExpansionModule, MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { ParametrageAppComponent } from './parametrage-app.component';
import { MaterialModule } from '../shared/material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FlexLayoutModule } from '@angular/flex-layout';
import { HttpClientModule } from '@angular/common/http';
import { SharedcomponentModule } from '../sharedcomponent/sharedcomponent.module';
import { HomeDesignComponent } from '../parametrage/components/home-design/home-design.component';

import { DndModule } from 'ngx-drag-drop';
import { ChartsModule } from 'ng2-charts';
import { DragDropModule } from '@angular/cdk/drag-drop';
import { CdkTreeModule } from '@angular/cdk/tree';

import { WidgetSecteurActiviteComponent } from './components/widget-secteur-activite/widget-secteur-activite.component';



@NgModule({
  declarations: [
    ParametrageAppComponent,
    MainContentComponent,
    EditCriterePwdComponent,
    HomeDesignComponent,    
    WidgetSecteurActiviteComponent,
   
  ],
  imports: [
    ParametrageRoutingModule,
    MatExpansionModule,
    CommonModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    FlexLayoutModule,
    HttpClientModule,
    SharedcomponentModule,
    DndModule,
    ChartsModule,
    DragDropModule,
    CdkTreeModule,
    
  ],
 
  providers: [DatePipe,
    { provide: MAT_DIALOG_DATA, useValue: {} },
    { provide: MatDialogRef, useValue: {} }],
})
export class ParametrageModule { }
