import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ConfigurationMainContentComponent } from './components/configuration-main-content/configuration-main-content.component';
import { ActionComponent } from './components/param-menu/action/action.component';
import { SminscriptionComponent } from './components/sminscription/sminscription.component';
import { SmlangueComponent } from './components/smlangue/smlangue.component';
import { SmmenuComponent } from './components/smmenu/smmenu.component';
import { SmnotificationComponent } from './components/smnotification/smnotification.component';
import { SmpaiementComponent } from './components/smpaiement/smpaiement.component';
import { ConfigurationAppComponent } from './configuration-app.component';

const routes: Routes = [
  {
    path: '', component: ConfigurationAppComponent,
    children: [
      { path: '', component: ConfigurationMainContentComponent },
      { path: 'sminscription', component:SminscriptionComponent },
      { path: 'smlangue', component:SmlangueComponent },
      { path: 'smpaiement', component:SmpaiementComponent },
      { path: 'smmenu', component:SmmenuComponent },
      { path: 'smnotification', component:SmnotificationComponent },  
      { path: 'action', component: ActionComponent },


    ],

  },

];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ConfigurationRoutingModule { }
