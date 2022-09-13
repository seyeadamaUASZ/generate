import { Component, OnInit } from '@angular/core';
import { ApplicationService } from 'src/app/application/services/application.service';
import { UserService } from 'src/app/utilisateur/services/user.service';
@Component({
  selector: 'app-stats',
  templateUrl: './stats.component.html',
  styleUrls: ['./stats.component.scss']
})
export class StatsComponent implements OnInit {
  breakpoint:any;
  nbrCommerciaux:any;
  nbrAdmin:any;
  nbrAppli:any;
  nbrModule:any;
  nbrIntegrateur:any;
  nbrConnect:any;
  nbrFichier:any;
  nbrFormulaire:any;
  nbrWorkflow:any;
  //@Input() profil:any;
  constructor(private appService:ApplicationService,private userService:UserService) { }

  ngOnInit() {
    // this.breakpoint = (window.innerWidth <= 400) ? 1 : 5;
    this.nbrconnect();
    this.nbrapplication();
    this.nbrmodule();
    this.nbrFichiers();
    this.nbrFormulaires();
    this.nbrWorkflows();
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
 
  nbrFichiers(){
    this.appService.nbrFichier().subscribe(res=>{
      this.nbrFichier = res.data;
    })
  }
  nbrFormulaires(){
    this.appService.nbrFormulaire().subscribe(res=>{
      this.nbrFormulaire = res.data;
    })
  }
  nbrWorkflows(){
    this.appService.nbrWorkflow().subscribe(res=>{
      this.nbrWorkflow = res.data;
    })
  }

 onResize(event) {
    this.breakpoint = (event.target.innerWidth <= 400) ? 1 : 5;
  }
}
