import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { MatDialog, MatSnackBar, MatDialogRef } from '@angular/material';
import { FormBuilder, Validators } from '@angular/forms';
import { TranslateService } from '@ngx-translate/core';
import { NotificationService } from 'src/app/shared/services/notification.service';

import { PaiementService } from '../../service/paiement.service';
import { MainContentPaiementComponent } from '../main-content-paiement/main-content-paiement.component';

@Component({
  selector: 'app-infos-payeur',
  templateUrl: './infos-payeur.component.html',
  styleUrls: ['./infos-payeur.component.scss']
})
export class InfosPayeurComponent implements OnInit {
  url;
  //url:string="http://196.207.202.51:8080/opay/?opayin=";
  factureForm = this.formbuild.group({
    idFacture: ['', Validators.required],
    montantFacture: [''],
    nomPayeur: [''],
    prenomPayeur: [''],
    usernamePayeur: [localStorage.getItem('username')],

  });
  constructor(public router: Router,
    private route: ActivatedRoute,
    private formbuild: FormBuilder,
    private _snackBar: MatSnackBar,
    private translate: TranslateService,

    public dialogRef: MatDialog,
    private notification: NotificationService,
    private paiementService: PaiementService,
    private dialog: MatDialog) { }

  ngOnInit() {
  }

  get f() { return this.factureForm.controls; }


  onSubmit() {
    if (this.factureForm.valid) {
      this.paiementService.payer(this.factureForm.value).subscribe(data => {
        if (data.statut) {
          this.url = data.data;
          this.closeDialog();
          window.open(this.url,'_blank');
         // console.log(this.url);
         this.translate.get('paiement.initialisation').subscribe((res: string) => {
          this.notification.warn(res);
        });

        }
      })
    }
    else {
      this.translate.get('invalid-form').subscribe((res: string) => {
        this.notification.warn(res);
      });
    }

  }
  closeDialog() {
    this.dialogRef.closeAll();
  }

}
