import { InscriptionService } from '../../service/inscription.service';
import { Inscription } from '../../model/inscription';
import { Component, OnInit,Input } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MatSnackBar } from '@angular/material';
import { TranslateService } from '@ngx-translate/core';
import { NotificationService } from '../../../shared/services/notification.service';
@Component({
  selector: 'app-add-inscription',
  templateUrl: './add-inscription.component.html',
  styleUrls: ['./add-inscription.component.scss']
})
export class AddInscriptionComponent implements OnInit {
result:any;
  public inscription: Array<File>;
 @Input() accept = '.';resp:any;
 isFileValid: boolean;
  images;
  href:any
  base64Data: any;
  based
  isUpload: boolean;
isfile:boolean;
extention:any;
startDate:any

InscriptionForm = this.formbuild.group(
  {profile :['', Validators.required],
prenom :['', Validators.required],
datenaiss :['', Validators.required],
nom :['', Validators.required],
 });  constructor(private inscriptionService: InscriptionService,
    private router: Router, private formbuild: FormBuilder,
    private _snackBar: MatSnackBar,
	private translate:TranslateService,
private notification: NotificationService,
    public dialogRef: MatDialogRef<AddInscriptionComponent >) {this.isfile=false }

  ngOnInit() {
  }

 onSubmitFile() {
    if (this.InscriptionForm.value) {
      this.inscriptionService.addInscription(this.inscription[0],this.InscriptionForm.value).subscribe(data => {
        if (data.statut) {
          let ReportSaveSuccess;
          this.translate.get('inscription.confirmEnr').subscribe((res: string) => {
            ReportSaveSuccess = res;
          });
          this.translate.get(ReportSaveSuccess).subscribe((res: string) => {
            this.notification.success(res);
          });

         this.InscriptionForm.reset();
          this.closeDialog();
        }
      }, error => {
        let ReportSaveError;
        this.translate.get('inscription.erreurEnr').subscribe((res: string) => {
          ReportSaveError = res;
        });
        this.translate.get(ReportSaveError).subscribe((res: string) => {
          this.notification.error(res);
        });
      });
    } else {
      let errorChamps;
      let form;
      this.translate.get('control.error').subscribe((res: string) => {
        form = res;
      });

      this.translate.get('control.required').subscribe((res: string) => {
        errorChamps = res;
      });
      this.translate.get(errorChamps).subscribe((res: string) => {
        this.notification.error(res);
      });

    }
  }
  uploadImage(event: any) {
this.isfile=true
    if (event.target.files[0]) {
       this.extention= event.target.files[0].name.split('.')[1].toLowerCase();

      this.inscription = event.target.files;
      if (this.inscription[0].size >3000000){
        this.translate.get("Verifier la taille du document!!").subscribe((res: string) => {
          this.notification.warn(res);
        });
        return;

      }
      this.isUpload = true;
    }
  }
  closeDialog() {
    this.dialogRef.close();
  }
}
