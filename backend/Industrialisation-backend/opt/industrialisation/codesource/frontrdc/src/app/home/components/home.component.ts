import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { UserService } from 'src/app/utilisateur/services/user.service';
import { ParametreService } from 'src/app/utilisateur/services/parametre.service';
import { WidgetService } from '../services/widget.service';
import { ApplicationService } from 'src/app/application/services/application.service';

@Component({
  selector: 'home-content',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  chartOptions = {
    responsive: true
  };
  MeschartLine = [];
  MeschartBar = [];
  Meschart = [];

  a;
  b;
  c;
  l;
  count;
  breakpoint: any;
  nbrCommerciaux: any;
  nbrAdmin: any;
  nbrAppli: any;
  nbrModule: any;
  nbrIntegrateur: any;
  nbrConnect: any;
  @Input() profil: any;
  widgets: any
  isRow: true;
  Langues;
  Themes;
  n;
  chartData1 = [
    { data: [0, 6, 2, 3, 6, 2, 7, 3, 6, 2, 1, 4], label: this.translate.instant('dashboard.connectes') },
    { data: [0, 14, 10, 4, 5, 10, 3, 3, 6, 6, 2, 9], label: 'Workflows' },
    { data: [0, 26, 12, 21, 30, 11, 25, 10, 34, 15, 24, 19], label: 'Formulaires' }
  ];
  chartLabels1 = ['January', 'February', 'Mars', 'April', 'Mai', 'Juin', 'Juillet', 'Aout', 'Septembre', 'Octobre', 'Novembre', 'Décembre'];
  chartData2 = [
    { data: [3, 16, 2, 13, 6, 8, 7, 13, 16, 12, 11, 14], label: 'Utilisateurs' },
    { data: [2, 14, 10, 4, 5, 10, 3, 3, 6, 6, 2, 9], label: 'Connectés' },
  ];
  chartLabels2 = ['January', 'February', 'Mars', 'April', 'Mai', 'Juin', 'Juillet', 'Aout', 'Septembre', 'Octobre', 'Novembre', 'Décembre'];
  constructor(private router: Router, private appService: ApplicationService,private userService: UserService,
    public paramService: ParametreService,
    private widgetService: WidgetService,
    private usersService: UserService,
    private translate: TranslateService) {
  }
  onResize(event) {
    this.breakpoint = (event.target.innerWidth <= 400) ? 1 : 5;
  }

  ngOnInit() {
    this.breakpoint = (window.innerWidth <= 400) ? 1 : 5;
    this.nbrconnect();
    this.nbrapplication();
    this.nbrmodule();

    this.profil = localStorage.getItem('profil');
    this.widgetService.allWidgetByProfilId(this.profil).subscribe(data => {
      this.widgets = data.data;
      this.lesChart();
      //this.n=this.widgets.wdgLongueur;  
    });
  }
  lesChart() {
    //let c = 0;
    for (let index = 0; index < this.widgets.length; index++) {
      // if (this.widgets[index].wdgCode == 'lne_app_wkf_fml' ||
      //   this.widgets[index].wdgCode == 'bar_uti_con') {
      //   this.Meschart[index] = this.widgets[index];
      //   this.count++;
      //   console.log("---------"+c); 
      // }

      if (this.widgets[index].wdgCode == 'lne_app_wkf_fml') {
        this.MeschartLine[index] = this.widgets[index];
        this.l = true;
      } else if (this.widgets[index].wdgCode == 'bar_uti_con') {
        this.MeschartBar[index] = this.widgets[index];
        this.b = true;

      }

    }
    // if (this.count=1){
    //   this.a=true;
    // }
    // else if (this.count=2){
    //   this.b=true;
    // }
    console.log(this.MeschartBar);
    console.log(this.MeschartLine);


  }

  nbrmodule() {
    this.appService.nbrModule().subscribe(res => {
      this.nbrModule = res.data;
    })
  }
  nbrapplication() {
    this.appService.nbrApplication().subscribe(res => {
      this.nbrAppli = res.data;
    })
  }
 
  nbrconnect() {
    this.userService.nbrUserConnect().subscribe(res => {
      this.nbrConnect = res.data;
    })
  }
 
  listLangue() {
    this.paramService.getLangue().subscribe(data => {
      this.Langues = data.data;
    });
  }


  listTheme() {
    this.paramService.getTheme().subscribe(data => {
      this.Themes = data.data;
    });
  }


  onChartClick($event) {

  }

}
