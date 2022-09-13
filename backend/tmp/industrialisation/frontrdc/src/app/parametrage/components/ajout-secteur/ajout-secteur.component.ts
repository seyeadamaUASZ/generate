import { Component, OnInit, Inject } from '@angular/core';
import { Validators, FormBuilder } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { MatDialog, MatSnackBar, MAT_DIALOG_DATA } from '@angular/material';
import { NotificationService } from 'src/app/shared/services/notification.service';
import { TranslateService } from '@ngx-translate/core';
import { ParametreService } from 'src/app/utilisateur/services/parametre.service';

@Component({
  selector: 'app-ajout-secteur',
  templateUrl: './ajout-secteur.component.html',
  styleUrls: ['./ajout-secteur.component.scss']
})
export class AjoutSecteurComponent implements OnInit {
  loading: any;
  idf;
  fontionnalite: any;
  secteurForm = this.formbuild.group({
    secNom: ['', Validators.required],
    secDescription: ['', Validators.required],

  });
  


  constructor(private formbuild: FormBuilder, private router: Router,
   private paramService:ParametreService,
    public dialogRef: MatDialog, private _snackBar: MatSnackBar,
    private route: ActivatedRoute, private notification: NotificationService, private translate: TranslateService, ) { }

  ngOnInit() {
  }

  onSubmit() {
    if(!this.secteurForm.valid){
      this.translate.get('fonctionnalite.invalid-form').subscribe((res: string) => {
        this.notification.error(res);
       });
    }else{
      this.paramService.createSecteur(this.secteurForm.value).subscribe(data => {
        if (data.statut) {
          this.translate.get(data.description).subscribe((res: string) => {
            this.notification.success(res);
          });
          console.log(data);
          this.secteurForm.reset();
          this.closeDialog();

        }
      }, error => {
       this.translate.get('secteur.error').subscribe((res: string) => {
          this.notification.error(res);
         });
      });
    }
      
  }
  closeDialog() {
    this.dialogRef.closeAll();
  }

}
