import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MatDialog, MatSnackBar } from '@angular/material';
import { UserService } from '../../services/user.service';
import { ToolbarComponent } from 'src/app/sharedcomponent/toolbar/toolbar.component';
import { TranslateService } from '@ngx-translate/core';
import { NotificationService } from '../../../shared/services/notification.service';

@Component({
  selector: 'app-change-pwd',
  templateUrl: './change-pwd.component.html',
  styleUrls: ['./change-pwd.component.scss']
})
export class ChangePwdComponent implements OnInit {
  constructor(public dialogRef: MatDialogRef<ToolbarComponent>, private userService: UserService,
    private formBuilder: FormBuilder,
    private _snackBar: MatSnackBar,
	public  translate: TranslateService,
    private notification: NotificationService) { }
    user;
  changeForm = this.formBuilder.group({
    username: [localStorage.getItem('username'), Validators.required],
    oldPwd: ['', Validators.required],
    newPwd: ['', Validators.required],
    confirmPwd: ['', Validators.required],
  });

  ngOnInit() {
    this.user = localStorage.getItem('username');
  }

  dialogClose() {
    this.dialogRef.close();
    console.log('fermé');
  }
  onSubmit() {
    let messageActive;
		this.translate.get('utilisateur.change-mdp').subscribe((res: string) => {
			messageActive = res;
		});
    if (!this.changeForm.invalid) {
      if ( this.changeForm.controls['newPwd'].value === this.changeForm.controls['confirmPwd'].value) {
        this.userService.changepwd(this.changeForm.value).subscribe( data => {
          console.log(data.statut)
          if ( data.statut ) {
            this.translate.get(messageActive).subscribe((res: string) => {
                  this.notification.success(res);
            });

            this.dialogClose();

          }else{
            this.translate.get(data.description).subscribe((res: string) => {
              this.notification.warn(res);
        });
          }

        });
      } else {

        this.translate.get('utilisateur.confirm').subscribe((res: string) => {
                  this.notification.warn(res);
            });
      }
    } else {
      this.translate.get('Formulaire invalide.').subscribe((res: string) => {
                  this.notification.error(res);
            });
    }
  }
}
