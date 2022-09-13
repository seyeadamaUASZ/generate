import { Component, OnInit } from '@angular/core';
import * as Highcharts from 'highcharts';
import { Options } from "highcharts";
import { WidgetService } from 'src/app/home/services/widget.service';
import HC_exporting from 'highcharts/modules/exporting';
import { Chart } from 'angular-highcharts';
import { ChartsModule } from 'ng2-charts';
import { ChartOptions } from 'chart.js';
import jsPDF from 'jspdf';
import html2canvas from 'html2canvas';
import { VisualisergraphComponent } from '../visualisergraph/visualisergraph.component';
import { MatDialog } from '@angular/material';
import { VisualiserboxComponent } from '../visualiserbox/visualiserbox.component';

@Component({
  selector: 'app-appstathorizontal',
  templateUrl: './appstathorizontal.component.html',
  styleUrls: ['./appstathorizontal.component.scss']
})
export class AppstathorizontalComponent implements OnInit {
    profilId:any = localStorage.getItem('profil');
  profil:any;
  chartOptions11:{};
  Highcharts1222=Highcharts; 
  chart2:any;
  chart3:any;
  chart5:any;
  chart8:any;

  nbrIndusUti:any={}
  nbrIndusUtiExport:any 
  nbrIndusUtiAnnee:any={}
  nbrIndusUtiAnnee1: any[]
  showMainContent: Boolean = true;
  
  barChartColors: any [] =[
    {
        backgroundColor:'#ad1a1a',
        //borderColor: "rgba(10,150,132,1)",
       // borderWidth: 5
      } ]
      
  barChartOptionsAnnee = {
    scaleShowVerticalLines: false,
    responsive: true
  };
    barChartLabelsAnnee = [];
    barChartTypeAnnee = 'horizontalBar';
    barChartLegendAnnee = true;
    barChartDataAnnee = [];

  barChartOptionsMonth = {
    scaleShowVerticalLines: false,
    responsive: true
  };
    barChartLabelsMonth = [];
    barChartTypeMonth = 'horizontalBar';
    barChartLegendMonth = true;
    barChartDataMonth = [];
public barChartOptions: ChartOptions = {
    responsive: true,
  };
  constructor(private widgetS:WidgetService,private dialog: MatDialog) { }

  ngOnInit() {
    this.getProfile(this.profilId);
    //this.plotSimpleBarChart();
    //console.log(this.chartOptions1);
    this.drawing();
     
    
}



  openPDF():void {
  let DATA = document.getElementById('htmlDataBaton');
    
  html2canvas(DATA).then(canvas => {
      
      let fileWidth = 208;
      let fileHeight = canvas.height * fileWidth / canvas.width;
      
      const FILEURI = canvas.toDataURL('image/png')
      let PDF = new jsPDF('p', 'mm', 'a4');
      let position = 0;
      PDF.addImage(FILEURI, 'PNG', 0, position, fileWidth, fileHeight)
      
      PDF.save('dashbord-data.pdf');
  });     
}
ShowHideButton() {
  this.showMainContent = this.showMainContent ? false : true;
}
drawing(){
  var rescount = []
  var result=[]
    this.widgetS.nbrIndusUtiParDate().subscribe(res => {
      this.nbrIndusUti = res.data;
        
      //console.log("++++++++++++++++nbrIndusUti+++++++++++++++++"+JSON.stringify(this.nbrIndusUti));
     
    var count = [];
      var mois=[]
    JSON.parse(JSON.stringify(this.nbrIndusUti), function (key, value) {
    if (typeof(value) != "object") {
       // values.push({[key]:value});
       count.push(value); //if you need a value array
       mois.push([key])
    } 
  }); 
     //console.log(count)
     /* for (var i=0; i < 100; i+=10) {
        result.push(i);
    }
    console.log("++++++++++++++++nbrIndusUti+++++++++++++++++"+result);*/ 
    
    this.barChartLabelsMonth = ['Janvier', 'Février', 'Mars', 'Avril', 'Mai','Juin','Juillet','Août','Septembre','Octobre','Novembre','Décembre'];
    this.barChartTypeMonth = 'horizontalBar';
    this.barChartLegendMonth = true; 
    this.barChartDataMonth = [
      {data: count, label: 'Nombre de utilisateurs créés'}, 
    ];
    
    
    })
        
    this.widgetS.nbrCourbeIndusUtiParAnnee().subscribe(res => {
      this.nbrIndusUtiAnnee = res.data;
        
       //console.log("++++++++++++++++nbrIndusUtiAnnee+++++++++++++++++"+JSON.stringify(this.nbrIndusUtiAnnee));
       var count = []
       var annee = []
       for (var i = 0; i < this.nbrIndusUtiAnnee.length; i++) {   
        count.push(this.nbrIndusUtiAnnee[i]["CountOfNewUsers"]); //if you need a value array
        annee.push(this.nbrIndusUtiAnnee[i]["annee"]) 
      }
      //console.log("++++++++++++++++count+++++++++++++++++"+count)
      //console.log("++++++++++++++++annee+++++++++++++++++"+annee)
      this.barChartLabelsAnnee = annee;
    this.barChartTypeAnnee = 'horizontalBar';
    this.barChartLegendAnnee = true; 
    this.barChartDataAnnee = [
      {data: count, label: 'Nombre de utilisateurs créés'}, 
    ];
  
    })
  

     

}
 

plotSimpleBarChart() {
//var chart = Highcharts.chart("highcharts", this.chartOptions1);
// var chart = Highcharts.chart("highcharts11", this.chartOptions);
// var chart = Highcharts.chart("highcharts1112", this.chartOptions2);
}

getProfile(id){
  this.widgetS.checkProfil(id)
  .subscribe(resp=>{
    this.profil=resp;
    //alert("le profil : "+JSON.stringify(this.profil))
  },err=>{
    console.log(err);
  })
}

downloadCanvas(event) {
  // get the `<a>` element from click event
  var anchor = event.target;
  console.log('anchor '+ anchor)
  // get the canvas, I'm getting it by tag name, you can do by id
  // and set the href of the anchor to the canvas dataUrl
  //anchor.href = document.getElementsByTagName('canvas')[1].toDataURL();
  // set the anchors 'download' attibute (name of the file to be downloaded)
  //console.log('anchor '+ anchor.href)
  //anchor.download = "test.png";
  //saveAs(anchor.href, 'utilisateurs.png');

  let DATA = document.getElementById('htmlDataBaton'); 
  html2canvas(DATA).then(canvas => {
      
      let fileWidth = 208;
      let fileHeight = canvas.height * fileWidth / canvas.width;
      
      const FILEURI = canvas.toDataURL('image/png')
      saveAs(FILEURI,'utilisateurs.png')
  }); 
}

 PrintImage(event) {
  var anchor = event.target;
  //console.log('anchor '+ anchor)
  let DATA = document.getElementById('htmlDataBaton'); 
  html2canvas(DATA).then(canvas => {
      
      let fileWidth = 208;
      let fileHeight = canvas.height * fileWidth / canvas.width;
      
      const FILEURI = canvas.toDataURL('image/png')
      //saveAs(FILEURI,'utilisateurs.png')
      var windowContent = '<!DOCTYPE html>';
      windowContent += '<html>';
      windowContent += '<head><title>Print canvas</title></head>';
      windowContent += '<body>';
      windowContent += '<img src="' + FILEURI + '">';
      windowContent += '</body>';
      windowContent += '</html>'; 
      const printWin = window.open('', '', 'width=' + screen.availWidth + ',height=' + screen.availHeight);
      printWin.document.open();
      printWin.document.write(windowContent); 
    
    printWin.document.addEventListener('load', function() {
        printWin.focus();
        printWin.print();
        printWin.document.close();
        printWin.close();            
    }, true);
  }); 

   //anchor.href = document.getElementsByTagName('canvas')[1].toDataURL();; //attempt to save base64 string to server using this var  
    

}

saveAs(uri, filename) {
  var link = document.createElement('a');
  if (typeof link.download === 'string') {
    link.href = uri;
    link.download = filename;

    //Firefox requires the link to be in the body
    document.body.appendChild(link);

    //simulate click
    link.click();

    //remove the link when done
    document.body.removeChild(link);
  } else {
    window.open(uri);
  }
}


openExcel(){
  this.widgetS.UtiParDateExport().subscribe(res => {  
    //this.nbrIndusUti = res.data;   
    this.nbrIndusUtiExport  = res.data
   // alert(JSON.stringify(this.nbrIndusUtiExport))
   this.widgetS.exportAsExcelFile(this.nbrIndusUtiExport,'nombre utilisateurs')
  },err=>{

  })
}
 

visualiserBox(parambox): void {
  const dialog = this.dialog.open(VisualiserboxComponent, {
    disableClose: true,
    width: '900px',
    data: parambox
  }).afterClosed().subscribe(result => { 
    this.getProfile(this.profilId);
  });

}


}


  


