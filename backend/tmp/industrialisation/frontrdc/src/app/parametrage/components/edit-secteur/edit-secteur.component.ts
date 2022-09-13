import { Component, OnInit, Inject } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ParametreService } from 'src/app/utilisateur/services/parametre.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NotificationService } from 'src/app/shared/services/notification.service';
import { MatSnackBar, MatDialog, MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { TranslateService } from '@ngx-translate/core';
import { Secteur } from '../../models/secteur';

@Component({
  selector: 'app-edit-secteur',
  templateUrl: './edit-secteur.component.html',
  styleUrls: ['./edit-secteur.component.scss']
})
export class EditSecteurComponent implements OnInit {
secteur:Secteur;
  loading: any;
  idf;
  fontionnalite: any;
  secteurForm = this.formbuild.group({
    secId: ['', Validators.required],
    secNom: ['', Validators.required],
    secDescription: ['', Validators.required],

  });
  


  constructor(private formbuild: FormBuilder, private router: Router,
   private paramService:ParametreService,
    public dialogRef: MatDialogRef<EditSecteurComponent>, private _snackBar: MatSnackBar,
    private route: ActivatedRoute, private notification: NotificationService,
     private translate: TranslateService, @Inject(MAT_DIALOG_DATA) public element: any ) { }

  ngOnInit() {
    this. getDetail();
  }

  onSubmit() {
      this.paramService.updateSecteur(this.secteurForm.value).subscribe(data => {
        if (data.statut) {
          this.translate.get(data.description).subscribe((res: string) => {
            this.notification.success(res);
          });
          console.log(data);
          this.secteurForm.reset();
          this.closeDialog();

        }
      }, error => {
        // this.translate.get(error).subscribe((res: string) => {
        //   this.notification.error(res);
        // });
      });
  }
  closeDialog() {
    this.dialogRef.close();
  }
  getDetail(){
    console.log("-----------------"+this.element.data)
    this.secteur=this.element;
    this.secteurForm.setValue({
      secId:this.secteur.secId,
      secNom:this.secteur.secNom,
      secDescription:this.secteur.secDescription
    });

  }

}
