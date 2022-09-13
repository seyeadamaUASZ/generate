import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DocumentAppComponent } from './documents/document-app.component';
import { AppPublishedComponent } from './login/components/app-published/app-published.component';
import { LandingComponent } from './login/components/landing/landing.component';
import { AuthGuard } from './login/services/auth/auth.guard';
import { PaiementRedirectComponent } from './paiement/components/paiement-redirect/paiement-redirect.component';
/*import { ExecutionComponent } from './procedures/execution/execution.component';
import { SecteursComponent } from './procedures/secteurs/secteurs.component';
import { SecteursComponent } from './procedures/secteurs/secteurs.component';

import { LandingComponent } from './login/landing/landing.component';

//import { SecteursComponent } from './procedures/secteurs/secteurs.component'; */
import { ListeFormulaireComponent } from './workflow/component/liste-formulaire/liste-formulaire.component';

const routes: Routes = [
  { path: 'login',loadChildren: './login/login.module#LoginModule' },
  { path: 'landing',loadChildren: './login/login.module#LoginModule',component:LandingComponent },
  { path: 'applicationpubliees',loadChildren: './login/login.module#LoginModule',component:AppPublishedComponent },
  { path: 'home',canActivate: [AuthGuard], loadChildren: './home/home.module#HomeModule' },
  { path: 'application',canActivate: [AuthGuard], loadChildren: './application/application.module#ApplicationModule' },
  { path: 'utilisateur',canActivate: [AuthGuard], loadChildren: './utilisateur/utilisateur.module#UtilisateurModule' },
  { path: 'workflow', canActivate: [AuthGuard], loadChildren: './workflow/workflow.module#WorkflowModule' },
  { path: 'parametrage', canActivate: [AuthGuard], loadChildren: './parametrage/parametrage.module#ParametrageModule' },
  { path: 'formulaire', canActivate: [AuthGuard], loadChildren: './formulaire/formulaire.module#FormulaireModule' },
  { path: 'fichier', canActivate: [AuthGuard], loadChildren: './fichier/fichier.module#FichierModule' },
  { path: 'procedures', canActivate: [AuthGuard], loadChildren: './procedures/procedures.module#ProceduresModule' },
  { path: 'exception',loadChildren: './exception/exception.module#ExceptionModule' },
  { path: 'paiement/redirect',loadChildren: './paiement/paiement.module#PaiementModule', component: PaiementRedirectComponent },
  { path: 'paiement', canActivate: [AuthGuard], loadChildren: './paiement/paiement.module#PaiementModule' },
  { path: 'qrcode', canActivate: [AuthGuard], loadChildren: './qrcode/qrcode.module#QrcodeModule' },
  { path: 'document',canActivate: [AuthGuard], loadChildren: './documents/document.module#DocumentModule' },
  { path: 'configuration', canActivate: [AuthGuard], loadChildren: './configuration/configuration.module#ConfigurationModule' },
  { path: 'inscription', loadChildren: './inscription/inscription.module#InscriptionModule'},
  { path: 'generateurotp', loadChildren: './generateurotp/generateurotp.module#GenerateurotpModule'},
  { path: 'formulairev2', canActivate: [AuthGuard], loadChildren: './formulaire-v2/formulaire-v2.module#FormulaireV2Module' },
  {path:'gestioncapteur',canActivate:[AuthGuard],loadChildren:'./gestioncapteur/gestioncapteur.module#GestioncapteurModule'},

  { path: '**', redirectTo: '/landing', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [AuthGuard]
})
export class AppRoutingModule { }
