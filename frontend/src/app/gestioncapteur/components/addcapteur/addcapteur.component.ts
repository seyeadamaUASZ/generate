import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { NotificationService } from 'src/app/shared/services/notification.service';
import { CapteurSService } from '../../services/capteur-s.service';

@Component({
  selector: 'app-addcapteur',
  templateUrl: './addcapteur.component.html',
  styleUrls: ['./addcapteur.component.scss']
})
export class AddcapteurComponent implements OnInit {
  constructor(private formbuild: FormBuilder, private router: Router,
    private translate:TranslateService,private notification:NotificationService,private capS:CapteurSService,
    public dialogRef: MatDialogRef<AddcapteurComponent>) { }
    
    addForm = this.formbuild.group({
      libelle: ['', Validators.required],  
      description: ['', Validators.required] 
     // wdgType:['', Validators.required],  
    });


    closeDialog(){
      this.dialogRef.close();
    }

    onSubmit(){
      if(this.addForm.valid){
        this.capS.addCapteur(this.addForm.value)
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

  ngOnInit() {
  }

}
