import { Component, OnInit } from '@angular/core';
import { Validators, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { ResetpwdService } from '../services/resetpwd.service';
import { PasswordRecover } from '../../utilisateur/models/passwordRecover';
import { MatSnackBar } from '@angular/material';
import { TranslateService } from '@ngx-translate/core';


@Component({
  selector: 'app-forget-pwd',
  templateUrl: './forget-pwd.component.html',
  styleUrls: ['./forget-pwd.component.scss']
})
export class ForgetPwdComponent implements OnInit {
   ForgetPwdForm = this.formbuild.group({
    pwrEmail: ['', Validators.required],
  });
  constructor(private formbuild: FormBuilder,
    private _snackBar: MatSnackBar, 
		private translate: TranslateService,
     private router: Router,private resetpwd: ResetpwdService) {

  }

 

  ngOnInit() {
     
   }


  onSubmit() {
    
   this.resetpwd.sendToken(this.ForgetPwdForm.value).subscribe(data => {
      console.log(data);
      if (data.statut == true) {
        alert(data.description);
        this.ForgetPwdForm.reset();
        this.router.navigate(['/login']);
      }else{
      alert(data.description);
      }
    }, error => {
      alert('Formulaire invalide');
    });

  }


  
}
