import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/utilisateur/services/user.service';

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

  //@Input() profil:any;
  constructor(private userService:UserService) { }

  ngOnInit() {
    this.breakpoint = (window.innerWidth <= 400) ? 1 : 5;
    this.nbrcomm();
    this.nbrconnect();
    this.nbrintegrateur();
    this.nbrapplication();
    this.nbrmodule();
    this.nbrAdmine();
    this.nbrUtilisateur();

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
  nbrAdmine(){
    this.userService.nbrAdministrateur().subscribe(res=>{
      this.nbrAdmin = res.data;
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
}

