import { OuverturecompteService } from '../../service/ouverturecompte.service';
import { Ouverturecompte } from '../../model/ouverturecompte';
import { Component, OnInit, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MatSnackBar, MAT_DIALOG_DATA } from '@angular/material';
import * as fileSaver from 'file-saver';
import { TranslateService } from '@ngx-translate/core';
import { NotificationService } from 'src/app/shared/services/notification.service';


@Component({
  selector: 'app-view-ouverturecompte',
  templateUrl: './view-ouverturecompte.component.html',
  styleUrls: ['./view-ouverturecompte.component.scss']
})
export class ViewOuverturecompteComponent implements OnInit {
  result: any;
  constructor(private ouverturecompteService: OuverturecompteService, private dialog: MatDialog,
    private router: Router, private formbuild: FormBuilder,
    public dialogRef: MatDialogRef<ViewOuverturecompteComponent>,
     @Inject(MAT_DIALOG_DATA) public donnee: any,
     private translate: TranslateService, private notification: NotificationService) { }

  
  
  ngOnInit() {
  }
  
  closeDialog() {
    this.dialogRef.close();
  }
  
  
 

 
}
