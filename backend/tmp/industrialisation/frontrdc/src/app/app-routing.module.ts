import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
//import { HomeComponent } from './home/home.component';
import { GuardGuard } from './utilisateur/services/guard.guard';
<<<<<<< HEAD
import { ExecutionComponent } from './workflow/execution/execution.component';
=======
>>>>>>> c4464812ca7610543937fb67f9bfb81adda56ab6
const routes: Routes = [
  { path: 'login',loadChildren: './login/login.module#LoginModule' },
  { path: 'home',canActivate: [GuardGuard], loadChildren: './home/home.module#HomeModule' },
  { path: 'application',canActivate: [GuardGuard], loadChildren: './application/application.module#ApplicationModule' },
  { path: 'utilisateur',canActivate: [GuardGuard], loadChildren: './utilisateur/utilisateur.module#UtilisateurModule' },
  { path: 'workflow', canActivate: [GuardGuard], loadChildren: './workflow/workflow.module#WorkflowModule' },
  { path: 'parametrage', canActivate: [GuardGuard], loadChildren: './parametrage/parametrage.module#ParametrageModule' },
  { path: 'formulaire', canActivate: [GuardGuard], loadChildren: './formulaire/formulaire.module#FormulaireModule' },
  { path: 'fichier', canActivate: [GuardGuard], loadChildren: './fichier/fichier.module#FichierModule' },
<<<<<<< HEAD
   { path: 'workflow/execution', component: ExecutionComponent },
{ path: 'sd', canActivate: [GuardGuard], loadChildren: './sd/sd.module#SdModule' },
{ path: 'modification', canActivate: [GuardGuard], loadChildren: './modification/modification.module#ModificationModule' },
=======
{ path: 'inscription', canActivate: [GuardGuard], loadChildren: './inscription/inscription.module#InscriptionModule' },
{ path: 'commande', canActivate: [GuardGuard], loadChildren: './commande/commande.module#CommandeModule' },
>>>>>>> c4464812ca7610543937fb67f9bfb81adda56ab6

  { path: '**', redirectTo: '/login', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [GuardGuard]
})
export class AppRoutingModule { }
