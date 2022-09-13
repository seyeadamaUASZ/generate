import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { MaterialModule } from '../shared/material.module';
import { ToolbarComponent } from './toolbar/toolbar.component';
import { SidenavComponent } from './sidenav/sidenav.component';
import { HttpClient } from '@angular/common/http';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { TranslateModule, TranslateLoader } from '@ngx-translate/core';
import { Observable } from 'rxjs/rx'
import { MatPaginatorI18nService } from './matPaginatorI18nService';
import { MatPaginatorIntl } from '@angular/material';
import { ConfirmDialogComponent } from './confirm-dialog/confirm-dialog.component';
import {MenuItemComponent} from './sidenav/menu-item/menu-item.component';
import { AvatarModule } from 'ngx-avatar';

@NgModule({
  declarations: [ToolbarComponent,SidenavComponent, MenuItemComponent, ConfirmDialogComponent],
  imports: [
    CommonModule, RouterModule, FormsModule, ReactiveFormsModule, MaterialModule, TranslateModule, AvatarModule
  ],
  exports: [
    ToolbarComponent,SidenavComponent,TranslateModule,
  ],
   providers: [   
    {
      provide: MatPaginatorIntl,
      useClass: MatPaginatorI18nService,
    }
    ],
})
export class SharedcomponentModule { }
export function httpTranslateLoader(http: HttpClient) {
  return new TranslateHttpLoader(http, "./assets/i18n/",".json");
}

