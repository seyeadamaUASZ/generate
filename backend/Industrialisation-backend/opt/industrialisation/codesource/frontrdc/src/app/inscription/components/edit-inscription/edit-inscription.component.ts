import { InscriptionService } from '../../service/inscription.service';
import { Inscription } from '../../model/inscription';
import { Component, OnInit,Inject,Input } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MatSnackBar,MAT_DIALOG_DATA } from '@angular/material';
import { NotificationService } from '../../../shared/services/notification.service';
import { TranslateService } from '@ngx-translate/core';
@Component({
  selector: 'app-edit-inscription',
  templateUrl: './edit-inscription.component.html',
  styleUrls: ['./edit-inscription.component.scss']
})
export class EditInscriptionComponent implements OnInit {
result:any
 @Input() accept = '.';
isFileValid: boolean;
  images;
  href:any
  base64Data: any;
  based
  isUpload: boolean 
 public inscription: Array<File>;
InscriptionForm = this.formbuild.group({
id :['', Validators.required],
profile :['', Validators.required],
prenom :['', Validators.required],
datenaiss :['', Validators.required],
nom :['', Validators.required],
 });  constructor(private inscriptionService: InscriptionService,
    private router: Router, private formbuild: FormBuilder, 
    private _snackBar: MatSnackBar,
private notification: NotificationService,
private translate:TranslateService,
    public dialogRef: MatDialogRef<EditInscriptionComponent>,@Inject(MAT_DIALOG_DATA) public donnee: any) { }

  ngOnInit() {
this.initView();
  }
initView() {   
    this.InscriptionForm.setValue({ 
     id: this.donnee.id,
profile:this.donnee.profile,
prenom:this.donnee.prenom,
datenaiss:this.donnee.datenaiss,
nom:this.donnee.nom,
 });}
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
    if (event.target.files[0]) {
       const extension = event.target.files[0].name.split('.')[1].toLowerCase();
       if ( "png" === extension ||"jpeg"===extension || "jpg"===extension || "gif"===extension ) {
         this.isFileValid = true;
       }
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