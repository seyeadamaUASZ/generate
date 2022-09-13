import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { WorkflowAppComponent } from './workflow-app.component';
import { MainContentWorkflowComponent } from './main-content-workflow/main-content-workflow.component';

const routes: Routes = [
  {
    path: '', component: WorkflowAppComponent,
    children: [
      /*{ path: ':id', component: DetailUtilisComponent},
      { path: '', component: MainContentUtilComponent }
     {path: 'secteurs/execution/:id', component: ExecutionComponent },
     {path: 'secteurs', component: SecteursComponent },
     {path: 'execution/:id', component: ExecutionComponent }, 
     {path: '', component: MainContentWorkflowComponent},*/
      // { path: ':id', component: DetailUtilisComponent},
     // { path: '', component: MainContentUtilComponent }
     
     //{path: 'execution', component: ExecutionComponent },
     {path: '', component: MainContentWorkflowComponent},
    ]
  },
  //{ path: '**', redirectTo: '' }
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class WorkflowRoutingModule { }
