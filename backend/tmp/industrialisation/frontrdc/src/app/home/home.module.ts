import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ChartsModule } from 'ng2-charts';
import { HomeRoutingModule } from './home-routing.module';
import { SharedcomponentModule } from '../sharedcomponent/sharedcomponent.module';
import { HomeAppComponent } from './home-app.component';
import { HomeComponent } from './components/home.component';
import { ApplicationRoutingModule } from '../application/application-routing.module';
import { MatExpansionModule } from '@angular/material';
import { MaterialModule } from '../shared/material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FlexLayoutModule } from '@angular/flex-layout';
import { HttpClientModule } from '@angular/common/http';
import { UsersBarreComponent } from './components/widgets/users-barre/users-barre.component';
import { WkformCourbeComponent } from './components/widgets/wkform-courbe/wkform-courbe.component';
import { StatsComponent } from './components/widgets/stats/stats.component';
import { StatsCostumerComponent } from './components/widgets/stats-costumer/stats-costumer.component';
@NgModule({
  declarations: [HomeAppComponent, HomeComponent, UsersBarreComponent, WkformCourbeComponent, StatsComponent, StatsCostumerComponent],
  imports: [
    CommonModule,
    ChartsModule,
    HomeRoutingModule,
    SharedcomponentModule,
    ApplicationRoutingModule,
    MatExpansionModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    FlexLayoutModule,
    HttpClientModule,
    SharedcomponentModule
  ]
})
export class HomeModule { }
