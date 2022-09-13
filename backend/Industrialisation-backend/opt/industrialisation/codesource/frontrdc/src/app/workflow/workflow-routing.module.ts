import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { WorkflowAppComponent } from './workflow-app.component';
import { MainContentWorkflowComponent } from './component/main-content-workflow/main-content-workflow.component';
import { ListeFormulaireComponent } from './component/liste-formulaire/liste-formulaire.component';
import { GestionTaskComponent } from './component/gestion-task/gestion-task.component';

const routes: Routes = [
  {
    path: '', component: WorkflowAppComponent,
    children: [
      {path: 'liste-formulaire/:idwrkf/:containerid', component: ListeFormulaireComponent },
      {path: 'liste-formulaire', component: ListeFormulaireComponent },
      {path: 'gestion-task/:idwrkf/:containerid/:processid', component: GestionTaskComponent },
      {path: 'gestion-task', component: GestionTaskComponent },
     {path: '', component: MainContentWorkflowComponent},
    ]
  },
 { path: '**', redirectTo: '' }
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class WorkflowRoutingModule { }
