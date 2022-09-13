import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeAppComponent } from './home-app.component';
import { HomeComponent } from './components/home.component';
import { StatsComponent } from './components/widgets/stats/stats.component';
import { DetailsComponent } from './components/widgets/details/details.component';

const routes: Routes = [
  {
    path: '', component: HomeAppComponent,
    children: [     
      { path: '', component: HomeComponent },
      {path: 'details/:parambox', component: DetailsComponent }, 
      {path: 'details', component: DetailsComponent }, 

    ]
  },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule { }
