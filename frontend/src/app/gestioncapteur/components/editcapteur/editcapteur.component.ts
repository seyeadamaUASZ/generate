import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MatSnackBar, MAT_DIALOG_DATA } from '@angular/material';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { NotificationService } from 'src/app/shared/services/notification.service';
import { CapteurSService } from '../../services/capteur-s.service';

@Component({
  selector: 'app-editcapteur',
  templateUrl: './editcapteur.component.html',
  styleUrls: ['./editcapteur.component.scss']
})
export class EditcapteurComponent implements OnInit {
  addForm = this.formbuild.group({
    libelle: [this.element.libelle, Validators.required],
    description: [this.element.description, Validators.required],
  });
  constructor(private formbuild: FormBuilder, private router: Router,
    private _snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<EditcapteurComponent>, private translate: TranslateService,
    private notification: NotificationService,private capS:CapteurSService,
    @Inject(MAT_DIALOG_DATA) public element: any) { }

  ngOnInit() {
  }

  closeDialog() {
    this.dialogRef.close();
  }

  onSubmit(){
    if(this.addForm.valid){
      this.capS.modifierCapteur(this.element.idCapteur,this.addForm.value)
      .subscribe(data=>{
       console.log(data);
       if (data.statut) {
         this.translate.get(data.description).subscribe((res: string) => {
           this.notification.success(res);
         });
 
         this.addForm.reset();
         this.closeDialog();
       }
      }, error => {
       this.translate.get(error).subscribe((res: string) => {
         this.notification.error(res);
       });
     });
   }else{
     let invalidForm;
     this.translate.get('application.invalid-form').subscribe((res: string) => {
       this.notification.error(res);
     });
   }
  }

}
