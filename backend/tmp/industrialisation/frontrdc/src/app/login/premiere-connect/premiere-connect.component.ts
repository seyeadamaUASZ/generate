import { Component, OnInit } from '@angular/core';
import { UserService } from '../../utilisateur/services/user.service';
import { FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-premiere-connect',
  templateUrl: './premiere-connect.component.html',
  styleUrls: ['./premiere-connect.component.scss']
})
export class PremiereConnectComponent implements OnInit {

  constructor(private userService: UserService,
    private formBuilder: FormBuilder,private router: Router, private route: ActivatedRoute,
    private _snackBar: MatSnackBar) { }
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

  onSubmit() {
    if (!this.changeForm.invalid) {
      if ( this.changeForm.controls['newPwd'].value === this.changeForm.controls['confirmPwd'].value) {
        this.userService.changepwd(this.changeForm.value).subscribe( data => {
          if ( data.statut ) {
            this.router.navigate(['/utilisateur']);
            this._snackBar.open(data.description, 'Verification', {
              duration: 2000,
            });
          }else{
            this._snackBar.open(data.description, 'Verification', {
              duration: 2000,
            });
          }
          //window.alert(data.description);
        });
      } else {
        this._snackBar.open('La confirmation du nouveau mot de passe est incorrecte.', 'Verification', {
          duration: 2000,
        });
      }
    } else {
      this._snackBar.open('Formulaire invalide', 'Erreur', {
        duration: 2000,
      });
    }
  }

}
