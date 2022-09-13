import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DonneecapteursComponent } from './components/donneecapteurs/donneecapteurs.component';
import { MainContentComponent } from './components/main-content/main-content.component';
import { GestioncapteurpAppComponent } from './gestioncapteur-app.component';

const routes: Routes = [
  {
     path: '', component: GestioncapteurpAppComponent,
     children: [
       { path: '', component: MainContentComponent},
       {path:'donneesCapturess',component:DonneecapteursComponent}
     

     ],

   },
   { path: '**', redirectTo: '' }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GestioncapteurRoutingModule { }
