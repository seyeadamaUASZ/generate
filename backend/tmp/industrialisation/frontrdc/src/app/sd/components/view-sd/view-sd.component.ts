import { CommandeService } from '../../service/commande.service';
import { Commande } from '../../model/commande';
import { Component, OnInit,Inject } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MatSnackBar,MAT_DIALOG_DATA } from '@angular/material';
@Component({
  selector: 'app-view-commande',
  templateUrl: './view-commande.component.html',
  styleUrls: ['./view-commande.component.scss']
})
export class ViewCommandeComponent implements OnInit {
result:any;
  constructor(private commandeService: CommandeService,
    private router: Router, private formbuild: FormBuilder, 
    private _snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<ViewCommandeComponent>,@Inject(MAT_DIALOG_DATA) public donnee: any) { }

  ngOnInit() {
this.detail();
  }
detail() {   
this.result = this.donnee;  }
  closeDialog() {
    this.dialogRef.close();
  }
}