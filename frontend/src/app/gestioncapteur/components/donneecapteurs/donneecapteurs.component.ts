import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatPaginator, MatSort, MatTableDataSource } from '@angular/material';
import { Donnees } from '../../models/donnee.model';
import { CapteurSService } from '../../services/capteur-s.service';
import {  merge, Observable } from 'rxjs';
import { Chart } from 'angular-highcharts';
@Component({
  selector: 'app-donneecapteurs',
  templateUrl: './donneecapteurs.component.html',
  styleUrls: ['./donneecapteurs.component.scss']
})
export class DonneecapteursComponent implements OnInit {
  displayedColumns: string[] = ['temperature','humidity','created_at','updated_at'];
  capteurs:any;
  loading: boolean;
  code:any;
  donee:any;
  donnees:any;
  temperatures:any=[];
  humidites:any=[];
  chart10:any;
  form: FormGroup = new FormGroup({
    temperature: new FormControl(false),
    humidity:new FormControl(false),
    created_at:new FormControl(false),
    updated_at:new FormControl(false),
  });
  constructor(private capS:CapteurSService) { }

  ngOnInit() {
    this.donneeCapteurRecuperee();
  }

  applyFilterModule(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  columnDefinitions = [
    {def: 'temperature', label: 'temperature', hide: false },
    {def: 'humidity',label:'humidity',hide:false},
    {def:'created_at',label:'created_at',hide:false},
    {def:'updated_at',label:'updated_at',hide:false}
  ]

  temperature = this.form.get('temperature');
  humidity=this.form.get('humidity');
  created_at=this.form.get('created_at');
  updated_at=this.form.get('updated_at');
  
  ngAfterViewInit() {
    //let o1: Observable<boolean> = this.title.valueChanges;
    let o1: Observable<boolean> = this.temperature.valueChanges;
    let o2:Observable<boolean> = this.humidity.valueChanges;
    let o3:Observable<boolean>=this.created_at.valueChanges;
    let o4:Observable<boolean>=this.updated_at.valueChanges;
    merge(o1, o2, o3,o4).subscribe(v => {
      //this.columnDefinitions[0].hide = this.title.value;
      this.columnDefinitions[0].hide = this.temperature.value;
      this.columnDefinitions[1].hide = this.humidity.value;
      this.columnDefinitions[2].hide= this.created_at.value;
      this.columnDefinitions[3].hide=this.updated_at.value;
      console.log(this.columnDefinitions);
    });

  }

  getDisplayedColumns(): string[] {
    return this.columnDefinitions.filter(cd => !cd.hide).map(cd => cd.def);
  }
  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;
	@ViewChild(MatSort) sort: MatSort;
  cbValues;
  dataSource: MatTableDataSource<Donnees>;

  donneeCapteurRecuperee(){
    this.capS.donneesRecupereesCapteurs()
    .subscribe(data=>{
      this.donnees=data;
       this.donee=this.donnees[0].title;
      console.log(this.donnees);
      this.dataSource = new MatTableDataSource<Donnees>(this.donnees);
      this.dataSource.paginator = this.paginator;
			this.dataSource.sort = this.sort;
      for (var i=0;i<this.donnees.length;i++){
         this.temperatures.push(this.donnees[i].temperature)
         this.humidites.push(this.donnees[i].humidity);
      }
      this.chart10=new Chart({
        chart: {
          type: 'spline'
        },
        title: {
          text: 'Variations '
        },
       colors:[
         '#6495ED',
         '#FFA500'
       ],
        xAxis: {
          categories: this.temperatures
        },
        yAxis: {
          title: {
            text: 'Humidités'
        }
        },
        series:[{
         name: 'Températures',
         type:undefined,
          data: this.humidites
        }]
      })
    
    },err=>{
      console.log(err);
    })
  }


}
