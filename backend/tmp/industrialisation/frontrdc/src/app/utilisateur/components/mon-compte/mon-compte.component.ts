import { Component, OnInit } from '@angular/core';
import { MonCompteService } from './services/mon-compte.service';
import { User } from '../../models/user';
import { MatDialog } from '@angular/material';
import { EditUtilisComponent } from '../edit-utilis/edit-utilis.component';
import { EditCompteComponent } from '../edit-compte/edit-compte.component';
import { DatePipe } from '@angular/common';
import { EditLogoCompteComponent } from '../edit-logo-compte/edit-logo-compte.component';
//import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-mon-compte',
  templateUrl: './mon-compte.component.html',
  styleUrls: ['./mon-compte.component.scss']
})
export class MonCompteComponent implements OnInit {

  profil:any;
  prerogatives:any;
  datePipeString;
  logo: any;

  compte: User;
  constructor(private compteService: MonCompteService, private datePipe: DatePipe,
  private dialogRef: MatDialog) { }

  ngOnInit() {
    this.infosDuCompte();
  }
  infosDuCompte() {
    this.compteService.infoCompte().subscribe(data => {
      this.compte = data;
      this.logo = "data:image/png;base64," + this.compte.utiLogo;
      this.datePipeString = this.datePipe.transform(this.compte.utiDateCreation, 'dd-MM-yyyy à hh:mm');
    })
  }
	openDialogUpdate(username) {
		//console.log(username);
		const dialog1 = this.dialogRef.open(EditCompteComponent, {
      disableClose: true,
			width: '700px',
			data: username
		}).afterClosed().subscribe(result => {
			//location.reload();
      this.infosDuCompte();
    });

  }

	openDialogUpdateLogo(username) {
		const dialog1 = this.dialogRef.open(EditLogoCompteComponent, {
      disableClose: true,

      width: '1200px',
      height: '700px',
			data: username
		}).afterClosed().subscribe(result => {
      this.infosDuCompte();
    });

  }
  
}
