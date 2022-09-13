import { Component, OnInit } from '@angular/core';
import * as Highcharts from 'highcharts';
import { Options } from "highcharts";
import { Chart } from 'angular-highcharts';
@Component({
  selector: 'app-stat1',
  templateUrl: './stat1.component.html',
  styleUrls: ['./stat1.component.scss']
})
export class Stat1Component implements OnInit {
  chart:any;
  constructor() { }

  ngOnInit() {
    //this.plotSimpleBarChart();
    this.drawing();
  }


  drawing(){
    this.chart = new Chart({
      chart: {
        type: 'column'
      },
      title: {
        text: 'Bar Chart'
      },
      xAxis: {
        categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May'],
        title: {
            text: null
        }
    },
    colors:[
      '#6495ED',
      '#FFA500'
     ],
      credits: {
        enabled: false
      },
      series:[
        {
          type:undefined, 
          name: 'Legend 1',
          data: [107, 31, 635, 203, 40]
      }, {
          type:undefined,
          name: 'Legend 2',
          data: [133, 156, 947, 408, 37]
      }
      ]
      });

}

}


