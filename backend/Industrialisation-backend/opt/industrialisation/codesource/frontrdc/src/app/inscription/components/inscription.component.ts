import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { MatDialog, MatSnackBar, MatDialogRef } from '@angular/material';
import { FormBuilder, Validators } from '@angular/forms';
import { TranslateService } from '@ngx-translate/core';
import { NotificationService } from 'src/app/shared/services/notification.service';
import { InscriptionService } from '../services/inscription.service';
import { UserService } from 'src/app/utilisateur/services/user.service';




@Component({
  selector: 'app-inscription',
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.scss']
})
export class InscriptionComponent implements OnInit {
  profiles
  InscriptionForm = this.formbuild.group({
    insPrenom: ['', Validators.required],
    insNom: ['', Validators.required],
    insUsername: ['', Validators.required],
    insTelephone: ['', Validators.required],
    insEmail: ['', Validators.required],

  });
  constructor(public router: Router,
    private route: ActivatedRoute,
    private formbuild: FormBuilder,
    private _snackBar: MatSnackBar,
    private translate: TranslateService,
    private inscriptionService: InscriptionService,
    private userService: UserService,
    public dialogRef: MatDialogRef<InscriptionComponent>,
    private notification: NotificationService,) { }

  ngOnInit() {
    this.translate.setDefaultLang('fr');
    this.translate.use(localStorage.getItem('langue'));

    this.userService.listprofilsInscri().subscribe(res => {
      this.profiles = res.data;

    });
  }
  get f() { return this.InscriptionForm.controls; }


  onSubmit() {
    if (this.InscriptionForm.valid) {
      this.inscriptionService.inscriptionUtilisateur(this.InscriptionForm.value).subscribe(data => {
        if (data.statut) {
          this.translate.get(data.description).subscribe((res: string) => {
            this.notification.success(res);
          });
          this.InscriptionForm.reset();
          this.goToLogin()
        } else {
          this.translate.get(data.description).subscribe((res: string) => {
            this.notification.info(res);
          });
        }
      },(err)=>{
        this.translate.get('internalServerError').subscribe((res: string) => {
          this.notification.warn(res);
        });
      })
    }
    else {
      this.translate.get('invalid-form').subscribe((res: string) => {
        this.notification.error(res);
      });
    }

  }

  closeDialog() {
    this.router.navigate(['/landing']);
  }

  goToLogin() {
    this.router.navigate(['/login']);
  }
}
