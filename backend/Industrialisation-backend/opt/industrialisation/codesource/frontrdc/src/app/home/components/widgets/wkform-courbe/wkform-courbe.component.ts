import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-wkform-courbe',
  templateUrl: './wkform-courbe.component.html',
  styleUrls: ['./wkform-courbe.component.scss']
})
export class WkformCourbeComponent implements OnInit {
  chartOptions = {
    responsive: true
  };
   chartData1 = [
    { data: [0, 6, 2, 3,6, 2, 7,3, 6, 2,1,4], label: this.translate.instant('dashboard.fichier')  },
    { data: [0, 14, 10, 4, 5, 10, 3,3, 6, 6,2,9], label:  'Workflows' },
    { data: [0, 26, 12, 21, 30, 11, 25, 10, 34, 15,24,19], label: 'Formulaires' }
  ];
  legend = true;
  chartLabels1 = ['January', 'February', 'Mars', 'April','Mai','Juin','Juillet','Aout','Septembre','Octobre','Novembre','Décembre'];
  constructor(private translate:TranslateService) { }

  ngOnInit() {
  }

  onChartClick($event){

  }

}
