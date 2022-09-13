import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/utilisateur/services/user.service';
import { MatDialogRef, MatSnackBar } from '@angular/material';
import { HttpClient } from '@angular/common/http';
import { NotificationService } from 'src/app/shared/services/notification.service';
import { TranslateService } from '@ngx-translate/core';
import { User } from 'src/app/utilisateur/models/user';
import { ServiceVisageService } from '../services/service-visage.service';

@Component({
  selector: 'app-enrolement-user',
  templateUrl: './enrolement-user.component.html',
  styleUrls: ['./enrolement-user.component.scss']
})
export class EnrolementUserComponent implements OnInit {
  profiles: any[];

  public registreForm = this.formbuild.group({
    utiPrenom: ['', Validators.required],
    utiNom: ['', Validators.required],
    utiUsername: ['', [Validators.required, Validators.pattern('^[a-zA-Z0-9_]*$')]],
    utiPassword: [''],
    utiTelephone: ['', Validators.required],
    utiEmail: ['', Validators.required],
    utiAdresse: ['', Validators.required],
    uti_pro_id: ['', Validators.required]
  });

  constructor(private formbuild: FormBuilder, private router: Router, private userService: UserService,
    public dialogRef: MatDialogRef<EnrolementUserComponent>,
    private _snackBar: MatSnackBar,
    private _http: HttpClient,
    private notification: NotificationService,
    private visageService: ServiceVisageService,
    private translate: TranslateService) { }

  ngOnInit() {
    this.userService.listprofil().subscribe(res => {
      this.profiles = res.data;

    });
  }

  get f() { return this.registreForm.controls; }
  closeDialog() {
    this.dialogRef.close();
  }

  enrolleUser(formData:User) {
    this.visageService.enrolUser(formData).subscribe(data => {
      if (data.data) {
        this.translate.get("Enrollement reussi!!!!").subscribe((res: string) => {
          this.notification.success(res);
        });
        this.registreForm.reset();
        this.closeDialog();

      }
      else{
        this.translate.get("erreur!!!!").subscribe((res: string) => {
          this.notification.error(res);
        });

      }
    }),error => {
      this.translate.get(error).subscribe((res: string) => {
                  this.notification.error(res);
            });


}

  }


}
