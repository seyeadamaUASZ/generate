import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { InscriptionComponent } from './inscription.component';
import { ListInscriptionComponent } from './components/list-inscription/list-inscription.component';

const routes: Routes = [
  {
    path: '', component: InscriptionComponent,
    children: [
      { path: '', component: ListInscriptionComponent },
    ],

  },
  { path: '**', redirectTo: '' }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class InscriptionRoutingModule { }