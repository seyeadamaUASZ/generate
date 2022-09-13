import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';
import { TranslateService } from '@ngx-translate/core';
import { AuthService } from '../utilisateur/services/auth.service';
import { StyleManagerService } from 'src/app/shared/style-manager.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ParametreService } from '../utilisateur/services/parametre.service';
import { UserService } from '../utilisateur/services/user.service';
import { NotificationService } from '../shared/services/notification.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  model: any = {};
  tokkens;
  langue;
  theme;
  state: boolean;
  type = 'password';
  lasse = 'visibility';
  icone = 'checked';
  loginForm = this.formBuilder.group({
    username: ['', Validators.required],
    password: ['', Validators.required]
  });
  loading: boolean;

  constructor(private router: Router, private route: ActivatedRoute, private formBuilder: FormBuilder,
    public translate: TranslateService,
    public userService: UserService,
    private auth: AuthService,
    public styleManager: StyleManagerService,
    public paramService: ParametreService,
    private _snackBar: MatSnackBar,
    public notification: NotificationService) {

    // translate.addLangs(['en', 'fr', 'es']);
    // this.translate.setDefaultLang('fr');
    this.langue = localStorage.getItem('langue');
    this.theme = localStorage.getItem('theme');

    this.getListParam();


    this.loading = false;
  }
  openSnackBar(message: string) {
    this._snackBar.open(message, 'Verification', {
      duration: 2000,
    });
  }

  ngOnInit() {    
    this.tokkens = localStorage.getItem('token');
   // this.langue = localStorage.getItem('langue');
    //this.theme = localStorage.getItem('theme');
    //this.getListParam();
    if (this.tokkens) {
      let message = 'Deja connecté';
      this.openSnackBar(message);
      this.router.navigate(['/home']);
    } else {
      this.router.navigate(['/']);
    }
  }
  switchLang(lang: string) {
    this.translate.use(lang);
  }
  regarder() {
    if (this.icone) {
      this.type = 'text';
      this.lasse = 'visibility_off';
      this.icone = '';
      this.myFunction();
    } else {
      this.type = 'password';
      this.lasse = 'visibility';
      this.icone = 'checked';
    }
  }
   myFunction() {
    setTimeout (() => {
      this.alertFunc();
   }, 400);
  }
   alertFunc() {
    this.icone = '';
    this.type = 'password';
    this.lasse = 'visibility';
    this.icone = 'checked';
  }

  // verifySession(): boolean {
  //   this.auth.VerifierConnexion(this.loginForm.controls['username'].value).subscribe(data => {
  //     console.log(data.statut);
  //     this.state = data.statut;
  //   });
  //   return this.state;
  // }

  getListParamUser() {
    this.userService.getParamUser(localStorage.getItem('username')).subscribe(data => {
      
      localStorage.setItem('langue', data.data.uti_lng_id.lngLangue);         
      localStorage.setItem('theme', data.data.uti_thm_id.thmName);
      this.langue = localStorage.getItem('langue') ? localStorage.getItem('langue') : 'fr';
      this.theme = localStorage.getItem('theme') ? localStorage.getItem('theme') : 'gainde-green';
      this.installTheme(this.theme);
      this.translate.setDefaultLang(this.langue);
      this.translate.use(localStorage.getItem('langue'));
    })

  }
  getListParam() {   
    this.paramService.getDefautParametre().subscribe(data => {
      //console.log(data);
      this.langue = this.langue ? this.langue : data.data.param_lng_id.lngLangue;
      this.theme = this.theme ? this.theme : data.data.param_thm_id.thmName;
      localStorage.setItem('langue', this.langue);
      localStorage.setItem('theme', this.theme);
      this.langue = localStorage.getItem('langue') ? localStorage.getItem('langue') : 'fr';
      this.theme = localStorage.getItem('theme') ? localStorage.getItem('theme') : 'gainde-green';
      this.installTheme(this.theme);
      this.translate.setDefaultLang(this.langue);
      this.translate.use(this.langue);
      if(data.data?.param_img_id)
        this.getLogo(data.data?.param_img_id.imgId);
      //console.log('Le theme'+ localStorage.getItem('theme'));
      // this.translate.setDefaultLang(localStorage.getItem('langue'));
      //location.reload();      
    }
    , error => {     
      this.langue = 'fr';
      this.theme = 'gainde-green';
      this.installTheme(this.theme);
      this.translate.setDefaultLang(this.langue);
      this.translate.use(this.langue);    
      this.translate.get('Error.internalservererror').subscribe((res: string) => {
          this.notification.error(res);            
          });         
    }
    );
 }

  getLogo(logoRef) {
    //Make a call to Sprinf Boot to get the Image Bytes.
    this.paramService.getImage(logoRef)
      .subscribe(
        res => {
          var base64Data = res.data.imgLogoByte;
          localStorage.setItem('logo', 'data:image/png;base64,' + base64Data);
        }
      );

  }

  onSubmit() {
    this.loading = true;
    this.auth.login(this.loginForm.controls['username'].value, this.loginForm.controls['password'].value).subscribe(data => {
      if (data.statut) {
        this.loading = false;
        if (data.premiereConnec) {              
          this.translate.get(data.description).subscribe((res: string) => {
          this.notification.info(res);            
        });
          localStorage.setItem('prenom', data.data.prenom);
          localStorage.setItem('nom', data.data.nom);
          localStorage.setItem('token', data.data.token);
          localStorage.setItem('profile', data.data.profile);
          localStorage.setItem('profileLibelle', data.data.profileLib);
          localStorage.setItem('username', data.data.username);
          localStorage.setItem('password', data.data.password);
          localStorage.setItem('id', data.data.utiId);
          localStorage.setItem('session', data.data.session);
          this.getListParamUser();
          this.router.navigate(['/login/premiereConnect']);

        } else {
          //window.alert(data.description);                   
         // console.log('data:' + data.data.username);
          localStorage.setItem('prenom', data.data.prenom);
          localStorage.setItem('nom', data.data.nom);
          localStorage.setItem('token', data.data.token);
          localStorage.setItem('profile', data.data.profile);
          localStorage.setItem('username', data.data.username);
          localStorage.setItem('profil', data.data.profile);
          localStorage.setItem('profileLibelle', data.data.profileLib);
          localStorage.setItem('id', data.data.id);
          localStorage.setItem('session', data.data.session);
          this.getListParamUser();
          this.router.navigate(['/home']);           
          this.translate.get(data.description).subscribe((res: string) => {
          this.notification.success(res);            
        });
        }

      } else {
        this.loading = false;       
         this.translate.get(data.description).subscribe((res: string) => {
          this.notification.warn(res);            
        });
      }
    }, error => {
      this.loading = false;
      this.translate.get('Error.internalservererror').subscribe((res: string) => {
          this.notification.error(res);            
      });     
    });

  }

  //}

  installTheme(themeName: string) {
    this.styleManager.setStyle('theme', themeName);
  }

}
