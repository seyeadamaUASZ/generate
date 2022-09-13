import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { ApplicationService } from 'src/app/application/services/application.service';
import { WidgetService } from 'src/app/home/services/widget.service';
import { UserService } from 'src/app/utilisateur/services/user.service';
import { VisualiserboxComponent } from '../visualiserbox/visualiserbox.component';

@Component({
  selector: 'app-stats-costumer',
  templateUrl: './stats-costumer.component.html',
  styleUrls: ['./stats-costumer.component.scss']
})
export class StatsCostumerComponent implements OnInit {
  breakpoint:any;
  nbrCommerciaux:any;
  nbrAdmin:any;
  nbrAppli:any;
  nbrModule:any;
  nbrIntegrateur:any;
  nbrConnect:any;
  nbrUser:any;
  stats:any;

  profilId:any = localStorage.getItem('profil');
  profil:any;

parambox:any

  //@Input() profil:any;
  constructor(private userService:UserService,private widgetService:WidgetService,private appService:ApplicationService,private dialog: MatDialog) { }

  ngOnInit() {
    this.getProfile(this.profilId);
    this.breakpoint = (window.innerWidth <= 400) ? 1 : 5;
    this.nbrconnect();
    this.nbrapplication();
    this.nbrmodule();
    this.nbrUtilisateur();
    this.getState();
      
  }
  getState(){
    this.widgetService.listeStatProfil().subscribe(res=>{
      this.stats = res.data;
      // console.log(this.stats);
      
    })
  }

  nbrmodule(){
    this.appService.nbrModule().subscribe(res=>{
      this.nbrModule = res.data;
    })
  }
  nbrapplication(){
    this.appService.nbrApplication().subscribe(res=>{
      this.nbrAppli = res.data;
    })
  }
 
  nbrconnect(){
    this.userService.nbrUserConnect().subscribe(res=>{
      this.nbrConnect = res.data;
    })
  }
 
  nbrUtilisateur(){
    this.userService.nbrUtilisateur().subscribe(res=>{
      this.nbrUser = res.data;
    })
  }

 onResize(event) {
    this.breakpoint = (event.target.innerWidth <= 400) ? 1 : 5;
  }

  getProfile(id){
    this.widgetService.checkProfil(id)
    .subscribe(resp=>{
      this.profil=resp;
    },err=>{
      console.log(err);
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

