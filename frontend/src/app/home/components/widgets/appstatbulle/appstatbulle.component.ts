import { Component, OnInit } from '@angular/core';
import * as Highcharts from 'highcharts';
import { Options } from "highcharts";
import { WidgetService } from 'src/app/home/services/widget.service';
import HC_exporting from 'highcharts/modules/exporting';
import { Chart } from 'angular-highcharts';
import { ChartsModule } from 'ng2-charts';
import { ChartDataSets, ChartOptions, ChartType } from 'chart.js';
import jsPDF from 'jspdf';
import html2canvas from 'html2canvas';
import { VisualisergraphComponent } from '../visualisergraph/visualisergraph.component';
import { MatDialog } from '@angular/material';
import { VisualiserboxComponent } from '../visualiserbox/visualiserbox.component';
@Component({
  selector: 'app-appstatbulle',
  templateUrl: './appstatbulle.component.html',
  styleUrls: ['./appstatbulle.component.scss']
})
export class AppstatbulleComponent implements OnInit {
  profilId:any = localStorage.getItem('profil');
  profil:any;
  chartbulle1:any;
  chartbulle2:any;
exportbuble: any=[]
  bubbleChartColors: Array<any> = [
    { // grey
      backgroundColor: '#194dd1'
     /* pointBackgroundColor: 'rgba(33,150,243,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(33,150,243,0.8)'*/
    },
    { // dark grey
      backgroundColor: '#e0d90d'
    },
    { // grey
      backgroundColor: '#e0d90d' 
    },
    { // grey
      backgroundColor: '#5ae00d'
    }  
  ];

  public bubbleChartOptions: ChartOptions = {
    responsive: true,
    scales: {
      xAxes: [{
        ticks: {
          min: 0,
          max: 30,
        }
      }],
      yAxes: [{
        ticks: {
          min: 0,
          max: 30,
        }
      }]
    }
  };
   bubbleChartType: ChartType = 'bubble';
   bubbleChartLegend = true;

   bubbleChartData: ChartDataSets[] = [
    {
      data: [
        { x: 4, y: 21, r: 10 },
        { x: 17, y: 5, r: 15 },
        { x: 26, y: 18, r: 23 },
        { x: 7, y: 28, r: 8 },
        { x: 9, y: 13, r: 20 },
        { x: 5, y: 14, r: 9 },
        { x: 1, y: 22, r: 5 },
        { x: 9, y: 29, r: 25 },
        
      ],
      
      label: 'Series A',
    },
  ];

  constructor(private widgetS:WidgetService) { }

  ngOnInit() {
    this.getProfile(this.profilId);
    this.drawing();
  }

  drawing(){
  
 
  }

  getProfile(id){
    this.widgetS.checkProfil(id)
    .subscribe(resp=>{
      this.profil=resp;
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
      this.bubbleChartData  = res.data
     this.widgetS.exportAsExcelFile(this.bubbleChartData,'nombre utilisateurs')
    },err=>{
  
    })
  }
   
  
 
  
  
  }
  
  
     