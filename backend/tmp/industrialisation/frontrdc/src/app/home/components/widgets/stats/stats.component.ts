import { Component, OnInit } from '@angular/core';
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
  constructor(private userService:UserService) { }

  ngOnInit() {
    this.breakpoint = (window.innerWidth <= 400) ? 1 : 5;
    this.nbrcomm();
    this.nbrconnect();
    this.nbrintegrateur();
    this.nbrapplication();
    this.nbrmodule();
    this.nbrFichiers();
    this.nbrFormulaires();
    this.nbrWorkflows();
  }

  nbrmodule(){
    this.userService.nbrModule().subscribe(res=>{
      this.nbrModule = res.data;
    })
  }
  nbrapplication(){
    this.userService.nbrApplication().subscribe(res=>{
      this.nbrAppli = res.data;
    })
  }
  nbrintegrateur(){
    this.userService.nbrIntegrateur().subscribe(res=>{
      this.nbrIntegrateur = res.data;
    })
  }
  nbrconnect(){
    this.userService.nbrUserConnect().subscribe(res=>{
      this.nbrConnect = res.data;
    })
  }
  nbrcomm(){
    this.userService.nbrCommerciaux().subscribe(res=>{
      this.nbrCommerciaux = res.data;
    })
  }
  nbrFichiers(){
    this.userService.nbrFichier().subscribe(res=>{
      this.nbrFichier = res.data;
    })
  }
  nbrFormulaires(){
    this.userService.nbrFormulaire().subscribe(res=>{
      this.nbrFormulaire = res.data;
    })
  }
  nbrWorkflows(){
    this.userService.nbrWorkflow().subscribe(res=>{
      this.nbrWorkflow = res.data;
    })
  }

 onResize(event) {
    this.breakpoint = (event.target.innerWidth <= 400) ? 1 : 5;
  }
}
