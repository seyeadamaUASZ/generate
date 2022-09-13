import { NgModule,CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { ChartsModule } from 'ng2-charts';
import { HomeRoutingModule } from './home-routing.module';
import { SharedcomponentModule } from '../sharedcomponent/sharedcomponent.module';
import { HomeAppComponent } from './home-app.component';
import { HomeComponent } from './components/home.component';
import { ApplicationRoutingModule } from '../application/application-routing.module';
import { MatExpansionModule, MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { MaterialModule } from '../shared/material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FlexLayoutModule } from '@angular/flex-layout';
import { HttpClientModule } from '@angular/common/http';
import { UsersBarreComponent } from './components/widgets/users-barre/users-barre.component';
import { WkformCourbeComponent } from './components/widgets/wkform-courbe/wkform-courbe.component';
import { StatsComponent } from './components/widgets/stats/stats.component';
import { StatsCostumerComponent } from './components/widgets/stats-costumer/stats-costumer.component';
import { MonCompteComponent } from '../utilisateur/components/mon-compte/mon-compte.component';
import { EditLogoCompteComponent } from '../utilisateur/components/edit-logo-compte/edit-logo-compte.component';
import { StatbatonComponent } from './components/widgets/statbaton/statbaton.component';
import { AppstatcirculaireComponent } from './components/widgets/appstatcirculaire/appstatcirculaire.component';
import { AppstatareaComponent } from './components/widgets/appstatarea/appstatarea.component';
import { Stat1Component } from './components/widgets/stat1/stat1.component';
import {ChartModule,HIGHCHARTS_MODULES} from 'angular-highcharts';
import { StatfolderComponent } from './components/widgets/statfolder/statfolder.component';
import { Statfolder1Component } from './components/widgets/statfolder1/statfolder1.component';
import * as more from 'highcharts/highcharts-more.src';
import * as exporting from 'highcharts/modules/exporting.src';
import * as exportData from 'highcharts/modules/export-data.src';
import { AppstathorizontalComponent } from './components/widgets/appstathorizontal/appstathorizontal.component';
import { AppstathoriempileComponent } from './components/widgets/appstathoriempile/appstathoriempile.component';
import { AppcolonneempComponent } from './components/widgets/appcolonneemp/appcolonneemp.component';
import { App3dComponent } from './components/widgets/app3d/app3d.component';
import { AppstatbulleComponent } from './components/widgets/appstatbulle/appstatbulle.component';
import * as HighchartSankey from "highcharts/modules/sankey.src";
import * as HighchartsWheel from "highcharts/modules/dependency-wheel.src";
import * as highcharts3D from 'highcharts/highcharts-3d.src'; 
import { VisualisergraphComponent } from './components/widgets/visualisergraph/visualisergraph.component'; 
import { VisualiserboxComponent } from './components/widgets/visualiserbox/visualiserbox.component';
import { DetailsComponent } from './components/widgets/details/details.component';
@NgModule({
  declarations: [HomeAppComponent, HomeComponent, UsersBarreComponent, WkformCourbeComponent, StatsComponent, StatsCostumerComponent, StatbatonComponent, AppstatcirculaireComponent,AppstatareaComponent, Stat1Component, StatfolderComponent, Statfolder1Component, AppstathorizontalComponent, AppstathoriempileComponent, AppcolonneempComponent, App3dComponent, AppstatbulleComponent, VisualisergraphComponent, VisualiserboxComponent, DetailsComponent],
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
    SharedcomponentModule,
    ChartModule
  ],schemas: [ CUSTOM_ELEMENTS_SCHEMA ],
  providers: [DatePipe,
    { provide: MAT_DIALOG_DATA, useValue: {} },
    { provide: MatDialogRef, useValue: {} },
    { provide: HIGHCHARTS_MODULES, useFactory: () => [ more,HighchartSankey,HighchartsWheel,highcharts3D,exporting,exportData ] } // add as factory to your providers
  ],
})
export class HomeModule { }
