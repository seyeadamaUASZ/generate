import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
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
import { ChoixFoncComponent } from './application/fonctionnalite/choix-fonc/choix-fonc.component';
import { EditFoncComponent } from './application/fonctionnalite/edit-fonc/edit-fonc.component';
import { ImageCropperModule } from 'ngx-image-cropper';
import { NgxQRCodeModule } from 'ngx-qrcode2';
import { DetailsFoncComponent } from 'src/app/application/fonctionnalite/detail-fonc/details-fonc.component';
<<<<<<< HEAD
import { SdComponent } from './sd/sd.component';
import { AddSdComponent } from './sd/components/add-sd/add-sd.component';
import { ViewSdComponent } from './sd/components/view-sd/view-sd.component';
import { EditSdComponent } from './sd/components/edit-sd/edit-sd.component';
import { ListSdComponent } from './sd/components/list-sd/list-sd.component';
import { ModificationComponent } from './modification/modification.component';
import { AddModificationComponent } from './modification/components/add-modification/add-modification.component';
import { ViewModificationComponent } from './modification/components/view-modification/view-modification.component';
import { EditModificationComponent } from './modification/components/edit-modification/edit-modification.component';
import { ListModificationComponent } from './modification/components/list-modification/list-modification.component';
=======
import { InscriptionComponent } from './inscription/inscription.component';
import { AddInscriptionComponent } from './inscription/components/add-inscription/add-inscription.component';
import { ViewInscriptionComponent } from './inscription/components/view-inscription/view-inscription.component';
import { EditInscriptionComponent } from './inscription/components/edit-inscription/edit-inscription.component';
import { ListInscriptionComponent } from './inscription/components/list-inscription/list-inscription.component';
import { CommandeComponent } from './commande/commande.component';
import { AddCommandeComponent } from './commande/components/add-commande/add-commande.component';
import { ViewCommandeComponent } from './commande/components/view-commande/view-commande.component';
import { EditCommandeComponent } from './commande/components/edit-commande/edit-commande.component';
import { ListCommandeComponent } from './commande/components/list-commande/list-commande.component';
>>>>>>> c4464812ca7610543937fb67f9bfb81adda56ab6


// import { ChartsModule } from 'ng2-charts';

@NgModule({
  declarations: [
    AppComponent,
    ChoixFoncComponent,
    EditFoncComponent,

    DetailsFoncComponent,

<<<<<<< HEAD
    SdComponent,

    AddSdComponent,

    ViewSdComponent,

    EditSdComponent,

    ListSdComponent,

    ModificationComponent,

    AddModificationComponent,

    ViewModificationComponent,

    EditModificationComponent,

    ListModificationComponent,
=======
    InscriptionComponent,

    AddInscriptionComponent,

    ViewInscriptionComponent,

    EditInscriptionComponent,

    ListInscriptionComponent,

    CommandeComponent,

    AddCommandeComponent,

    ViewCommandeComponent,

    EditCommandeComponent,

    ListCommandeComponent,
>>>>>>> c4464812ca7610543937fb67f9bfb81adda56ab6

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
    NgxQRCodeModule,
    SweetAlert2Module.forRoot(),
    DndModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: httpTranslateLoader,
        deps: [HttpClient]
      }
    })

  ],
  exports: [SharedcomponentModule,
    AvatarModule
  ],
  providers: [
    BnNgIdleService,GuardGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: Interceptor,
      multi: true
    }
    ],
  bootstrap: [AppComponent]
})
export class AppModule { }
export function httpTranslateLoader(http: HttpClient) {
  return new TranslateHttpLoader(http, "./assets/i18n/",".json");
}

