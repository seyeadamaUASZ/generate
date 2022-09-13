import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { WorkflowRoutingModule } from './workflow-routing.module';
import { MaterialModule } from '../shared/material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FlexLayoutModule } from '@angular/flex-layout';
import { HttpClientModule } from '@angular/common/http';
import { SharedcomponentModule } from '../sharedcomponent/sharedcomponent.module';
import { WorkflowAppComponent } from './workflow-app.component';
import { MainContentWorkflowComponent } from './main-content-workflow/main-content-workflow.component';
import { AddworkflowComponent} from 'src/app/workflow/addworkflow/addworkflow.component';
import { from } from 'rxjs';
import { ViewworkflowComponent } from './viewworkflow/viewworkflow.component';
import { EditworkflowComponent } from './editworkflow/editworkflow.component';

import { BpmComponent } from './bpm/bpm.component';



@NgModule({
  declarations: [WorkflowAppComponent, MainContentWorkflowComponent, AddworkflowComponent, ViewworkflowComponent, EditworkflowComponent,BpmComponent],
  imports: [
    CommonModule,
    WorkflowRoutingModule,
    WorkflowRoutingModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    FlexLayoutModule,
    HttpClientModule,
    SharedcomponentModule
  ]
})
export class WorkflowModule { }
