import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MainContentComponent } from './components/main-content/main-content.component';
import { ApplicationAppComponent } from './application-app.component';
import { AjoutAppComponent } from './components/ajout-app/ajout-app.component';
import { FonctionnaliteComponent } from 'src/app/application/fonctionnalite/fonctionnalite.component';
import { from } from 'rxjs';
import { EditAppComponent } from './components/detail-app/edit-app.component';
import { EtapeCreationAppComponent } from './components/edit-app/etape-creation-app.component';

const routes: Routes = [
  {
    path: '', component: ApplicationAppComponent,
    children: [
      { path: 'fonctionnalite/:id', component: FonctionnaliteComponent },
      { path: 'detail/:id', component: EditAppComponent },
      { path: 'edit/:id', component: EtapeCreationAppComponent },
      { path: 'creation', component: AjoutAppComponent },
      { path: '', component: MainContentComponent },


    ]
  },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ApplicationRoutingModule { }
