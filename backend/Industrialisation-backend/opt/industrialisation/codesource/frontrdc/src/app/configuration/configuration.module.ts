import { NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';

import { ConfigurationRoutingModule } from './configuration-routing.module';
import { ConfigurationMainContentComponent } from './components/configuration-main-content/configuration-main-content.component';
import { MaterialModule } from '../shared/material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatDialogRef, MatGridListModule, MAT_DIALOG_DATA } from '@angular/material';
import { HttpClientModule } from '@angular/common/http';
import { SharedcomponentModule } from '../sharedcomponent/sharedcomponent.module';
import { ConfigurationAppComponent } from './configuration-app.component';
import { EditLangueComponent } from './components/edit-langue/edit-langue.component';
import { LangueComponent } from './components/langue/langue.component';
import { PaiementConfigComponent } from './components/paiement-config/paiement-config.component';
import { EditTraductionComponent } from './components/edit-traduction/edit-traduction.component';
import { MenuComponent } from './components/param-menu/menu/menu.component';
import { ViewMenuComponent } from './components/param-menu/view-menu/view-menu.component';
import { EditMenuComponent } from './components/param-menu/edit-menu/edit-menu.component';
import { AddMenuComponent } from './components/param-menu/add-menu/add-menu.component';
import { ActionComponent } from './components/param-menu/action/action.component';
import { AddActionComponent } from './components/param-menu/add-action/add-action.component';
import { NotificationComponent } from './components/notification/notification.component';
import { AjoutEvenementComponent } from './components/notification/ajout-evenement/ajout-evenement.component';
import { AjoutNotificationComponent } from './components/notification/ajout-notification/ajout-notification.component';
import { EditNotificationComponent } from './components/notification/edit-notification/edit-notification.component';

@NgModule({
  declarations: [ConfigurationMainContentComponent, ConfigurationAppComponent, EditLangueComponent, LangueComponent,
    PaiementConfigComponent, EditTraductionComponent, MenuComponent,
    ViewMenuComponent,
    EditMenuComponent,
    AddMenuComponent,
    ActionComponent,
    AddActionComponent, NotificationComponent,
    AjoutEvenementComponent,
    AjoutNotificationComponent,
    EditNotificationComponent],

  imports: [
    CommonModule,
    ConfigurationRoutingModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    FlexLayoutModule,
    MatGridListModule,
    HttpClientModule,
    SharedcomponentModule,
  ],
  exports: [
    MenuComponent,
    ViewMenuComponent,
    EditMenuComponent,
    AddMenuComponent,
    ActionComponent,
    AddActionComponent
  ],
  providers: [DatePipe,
    { provide: MAT_DIALOG_DATA, useValue: {} },
    { provide: MatDialogRef, useValue: {} }],
})
export class ConfigurationModule { }
