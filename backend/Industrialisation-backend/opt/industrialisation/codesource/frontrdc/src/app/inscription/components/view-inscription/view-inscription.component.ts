import { InscriptionService } from '../../service/inscription.service';
import { Inscription } from '../../model/inscription';
import { Component, OnInit,Inject } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MatSnackBar,MAT_DIALOG_DATA } from '@angular/material';
@Component({
  selector: 'app-view-inscription',
  templateUrl: './view-inscription.component.html',
  styleUrls: ['./view-inscription.component.scss']
})
export class ViewInscriptionComponent implements OnInit {
result:any;
  constructor(private inscriptionService: InscriptionService,
    private router: Router, private formbuild: FormBuilder, 
    private _snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<ViewInscriptionComponent>,@Inject(MAT_DIALOG_DATA) public donnee: any) { }

  ngOnInit() {
this.detail();
  }
detail() {   
this.result = this.donnee;  }
  closeDialog() {
    this.dialogRef.close();
  }
}