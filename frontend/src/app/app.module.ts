import { BrowserModule } from '@angular/platform-browser';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule, HttpClient, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule, FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from './shared/material.module';
import { SharedcomponentModule } from './sharedcomponent/sharedcomponent.module';
import { LoginComponent } from './login/login.component';
//import { HomeComponent } from './home/home.component';
import { TranslateModule, TranslateLoader } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import {MatDividerModule} from '@angular/material/divider';
import {MatExpansionModule} from '@angular/material/expansion';
import { BnNgIdleService } from 'bn-ng-idle'; // import bn-ng-idle service
import { Interceptor } from './interceptor';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { FlexLayoutModule } from '@angular/flex-layout';
import { AvatarModule } from 'ngx-avatar';
import { DndModule } from 'ngx-drag-drop';
import { SweetAlert2Module } from '@toverux/ngx-sweetalert2';
import { GuardGuard } from './utilisateur/services/guard.guard';
import { ImageCropperModule } from 'ngx-image-cropper';
import { TraductionService } from './configuration/services/traduction.service';
import { environment } from 'src/environments/environment';
// import { DynamicFormFieldComponent } from './sharedcomponent/dynamicform/components/dynamic-form-field/dynamic-form-field.component';
import { SafeResourceUrl,  } from '@angular/platform-browser';
//import { GenerateurotpComponent } from './generateurotp/generateurotp.component';
//import { AddsecteurComponent } from './procedures/addsecteur/addsecteur.component';

import { registerLocaleData } from '@angular/common';
import localeFr from '@angular/common/locales/fr';
registerLocaleData(localeFr, 'fr');
import { LOCALE_ID } from '@angular/core';
import { IdentificationUserComponent } from './reconnaissanceVisage/identification-user/identification-user.component';
import { EnrolementUserComponent } from './reconnaissanceVisage/enrolement-user/enrolement-user.component';
import { SafeUrlPipePipe } from './safe-url-pipe.pipe';
import { FilterDatasourcePipe } from './filter-datasource.pipe';
//import { ChatbotComponent } from './chatbot/chatbot.component';

@NgModule({
  declarations: [
    AppComponent,
    SafeUrlPipePipe,
    FilterDatasourcePipe,
    //ChatbotComponent,

  /*  ChoixFoncComponent,
    EditFoncComponent,
    DetailsFoncComponent,
    MonCompteComponent,
    EditCompteComponent,
    EditLogoCompteComponent,*/
    //AddsecteurComponent
    // DynamicFormFieldComponent
    // QrcodeComponent,
    // QrcodeAppComponent,
   // LoginComponent,
   // HomeComponent
  ],

  imports: [
    ImageCropperModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    MaterialModule,
    ReactiveFormsModule,
    MatDividerModule,
    SharedcomponentModule,
    MatExpansionModule,
    MatProgressSpinnerModule,
    MatSnackBarModule,
    FlexLayoutModule,
    AvatarModule,
    SweetAlert2Module.forRoot(),
    DndModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: httpTranslateLoader,
        // useClass: TraductionService,
        deps: [HttpClient],
      }
    })

  ],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ],
  exports: [SharedcomponentModule,
    AvatarModule,

  ],
  providers: [
    BnNgIdleService,GuardGuard,SafeUrlPipePipe,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: Interceptor,
      multi: true
    },{ provide: LOCALE_ID, useValue: "fr-FR" }

    ],
  bootstrap: [AppComponent]
})
export class AppModule { }
export function httpTranslateLoader(http: HttpClient) {
  const api = environment.apii;
  // return new TranslateHttpLoader(http, "./assets/i18n/",".json");
  return new TranslateHttpLoader(http, `${api}traduction/i18n/`,"");

}

