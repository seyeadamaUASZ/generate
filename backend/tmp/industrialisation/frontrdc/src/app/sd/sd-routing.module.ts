import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SdComponent } from './sd.component';
import { ListSdComponent } from './components/list-sd/list-sd.component';

const routes: Routes = [
  {
    path: '', component: SdComponent,
    children: [
      { path: '', component: ListSdComponent },
    ],

  },
  { path: '**', redirectTo: '' }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SdRoutingModule { }