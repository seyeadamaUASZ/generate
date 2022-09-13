import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ParametrageRoutingModule } from './parametrage-routing.module';
import { MainContentComponent } from './components/main-content/main-content.component';
// import { DetailParamComponent } from './components/detail-para/detail-para.component';
import { EditCriterePwdComponent } from './components/edit-critere-pwd/edit-critere-pwd.component';
import { MatExpansionModule } from '@angular/material';
import { ParametrageAppComponent } from './parametrage-app.component';
import { MaterialModule } from '../shared/material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FlexLayoutModule } from '@angular/flex-layout';
import { HttpClientModule } from '@angular/common/http';
import { SharedcomponentModule } from '../sharedcomponent/sharedcomponent.module';
import { HomeDesignComponent } from '../parametrage/components/home-design/home-design.component';
import { DndModule } from 'ngx-drag-drop';
//import { HomeComponent } from '../home/components/home.component';
import { ChartsModule } from 'ng2-charts';
import { DragDropModule } from '@angular/cdk/drag-drop';
import { CdkTreeModule } from '@angular/cdk/tree';
import { AjoutSecteurComponent } from './components/ajout-secteur/ajout-secteur.component';
import { EditSecteurComponent } from './components/edit-secteur/edit-secteur.component';
import { AjoutQrcodeComponent } from './ajout-qrcode/ajout-qrcode.component';
import { EditQrcodeComponent } from './edit-qrcode/edit-qrcode.component';
import {NotificationComponent} from './components/notification/notification.component';
import { AjoutEvenementComponent } from './components/notification/ajout-evenement/ajout-evenement.component';
import { AjoutNotificationComponent } from './components/notification/ajout-notification/ajout-notification.component';
import { MenuComponent } from './components/param-menu/menu/menu.component';
import { ViewMenuComponent } from './components/param-menu/view-menu/view-menu.component';
import { EditMenuComponent } from './components/param-menu/edit-menu/edit-menu.component';
import { AddMenuComponent } from './components/param-menu/add-menu/add-menu.component';
import { ActionComponent } from './components/param-menu/action/action.component';
import { AddActionComponent } from './components/param-menu/add-action/add-action.component';
import { WidgetSecteurActiviteComponent } from './components/widget-secteur-activite/widget-secteur-activite.component';

@NgModule({
  declarations: [ParametrageAppComponent, MainContentComponent, EditCriterePwdComponent,HomeDesignComponent, AjoutSecteurComponent, EditSecteurComponent, AjoutQrcodeComponent,EditQrcodeComponent, NotificationComponent, AjoutEvenementComponent, AjoutNotificationComponent, MenuComponent, ViewMenuComponent, EditMenuComponent, AddMenuComponent, ActionComponent, AddActionComponent, WidgetSecteurActiviteComponent],
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
    CdkTreeModule
  ]
})
export class ParametrageModule { }
