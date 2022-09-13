import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/utilisateur/services/user.service';
import { MatDialogRef, MatSnackBar } from '@angular/material';
import { HttpClient } from '@angular/common/http';
import { NotificationService } from 'src/app/shared/services/notification.service';
import { ServiceVisageService } from 'src/app/reconnaissanceVisage/services/service-visage.service';
import { TranslateService } from '@ngx-translate/core';
import { ServicesService } from '../services/services.service';

@Component({
  selector: 'app-enrole-user',
  templateUrl: './enrole-user.component.html',
  styleUrls: ['./enrole-user.component.scss']
})
export class EnroleUserComponent implements OnInit {
  profiles: any[];
  respons:any;
  loading: any;
  idEmpriente
  public registreForm = this.formbuild.group({
    utiPrenom: ['', Validators.required],
    utiNom: ['', Validators.required],
    utiUsername: ['', [Validators.required, Validators.pattern('^[a-zA-Z0-9_]*$')]],
    utiPassword: [''],
    utiTelephone: ['', Validators.required],
    utiEmail: ['', Validators.required],
    utiAdresse: ['', Validators.required],
    uti_pro_id: ['', Validators.required],
    idEmpriente:['']
  });
  constructor(private formbuild: FormBuilder, private router: Router, private userService: UserService,
    public dialogRef: MatDialogRef<EnroleUserComponent>,
    private _snackBar: MatSnackBar,
    private _http: HttpClient,
    private notification: NotificationService,
    private visageService: ServiceVisageService,
    private serviceFinger:ServicesService,
    private translate: TranslateService) { }

  ngOnInit() {
    this.userService.listprofil().subscribe(res => {
      this.profiles = res.data;

    });
    this.reFinger();
  }

  get f() { return this.registreForm.controls; }
  closeDialog() {
    this.dialogRef.close();
  }
  reFinger(){
    this.serviceFinger.getFingerZk().subscribe(data=>{
        this.translate.get(data.data).subscribe((res: string) => {
          this.notification.success(res);
    });

    })
}
enrolFinger(){
  this.serviceFinger.enrolFingerZk().subscribe(data=>{
    if(data.idEnrolle==3){
      this.idEmpriente=data.id
      this.registreForm.value.idEmpriente=this.idEmpriente;
      if (this.registreForm.valid) {
            this.userService.registreUser(this.registreForm.value).subscribe(data => {
                  if (data.statut) {
                        this.translate.get(data.description).subscribe((res: string) => {
                              this.notification.success(res);
                        });
                        this.registreForm.reset();
                        this.closeDialog();
                  }
                  else{
                    this.translate.get(data.description).subscribe((res: string) => {
                      this.notification.warn(res);
                });
                  }
                   this.loading = false;
            }, error => {
                  this.translate.get(error).subscribe((res: string) => {
                              this.notification.error(res);
                        });
  
                  this.loading = false;
            });
      }else{
            this.translate.get('formulaire invalide').subscribe((res: string) => {
                  this.notification.error(res);
            });
  
            this.loading = false;
      }

    }else{
      this.translate.get('poser votre doigt sur le capteur trois fois').subscribe((res: string) => {
        this.notification.error(res);
  });

    }
 

  })
}

}
