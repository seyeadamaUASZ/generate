import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material';
import { NotificationService } from 'src/app/shared/services/notification.service';
import { ServiceVisageService } from '../services/service-visage.service';
import { TranslateService } from '@ngx-translate/core';
import { AuthService } from 'src/app/utilisateur/services/auth.service';
import { UserService } from 'src/app/utilisateur/services/user.service';
import { StyleManagerService } from 'src/app/shared/style-manager.service';
import { ParametreService } from 'src/app/utilisateur/services/parametre.service';

@Component({
  selector: 'app-identification-user',
  templateUrl: './identification-user.component.html',
  styleUrls: ['./identification-user.component.scss']
})
export class IdentificationUserComponent implements OnInit {
  tokkens;
  appName;
  appLogo
  imageAccueil;
  loading: boolean;
  langue;
  theme

  loginForm = this.formBuilder.group({
    username: ['', Validators.required],
  });

  constructor( private formBuilder: FormBuilder, private router: Router,
    private _snackBar: MatSnackBar,
    private notification: NotificationService,
    private visageService: ServiceVisageService,
    private auth: AuthService,
    public userService: UserService,
    public styleManager: StyleManagerService,
    public paramService: ParametreService,
    private translate: TranslateService ) {    
      this.loading = false;
   }

  ngOnInit() {
    this.imageAccueil=localStorage.getItem('image');
  }

  loginVisage(){
    this.loading = true
    this.visageService.loginByVisage().subscribe(data=>{
      if(data){
        this.loading = false;
        if(data.data==true){
          this.auth.login('admin', 'Gainde2000@').subscribe(data => {
            if (data.statut) {
                localStorage.setItem('prenom', data.data?.prenom);
                localStorage.setItem('nom', data.data?.nom);
                localStorage.setItem('token', data.data?.token);
                localStorage.setItem('profile', data.data?.profile);
                localStorage.setItem('username', data.data?.username);
                localStorage.setItem('profil', data.data?.profile);
                localStorage.setItem('profileLibelle', data.data?.profileLib);
                localStorage.setItem('id', data.data?.id);
                localStorage.setItem('session', data.data?.session);
                localStorage.removeItem('tentative');
                this.getListParamUser();
                this.router.navigate(['/home']);
                this.translate.get(data.description).subscribe((res: string) => {
                  this.notification.success(res);
                })
              
        
            }
        
        })

        }else{
          this.loading = false;
          this.translate.get("Personne inconnue ou verifiez votre username!!").subscribe((res: string) => {
            this.notification.error(res);
          });
          this.loginForm.reset();

        }

      }else{
        this.loading = false;
        this.translate.get("personne inconnue").subscribe((res: string) => {
          this.notification.error(res);
        });
      }
    },(error) => {
      this.translate.get(error).subscribe((res: string) => {
                  this.notification.error(res);
            });
})
  }
  installTheme(themeName: string) {
    this.styleManager.setStyle('theme', themeName);
  }

  getListParamUser() {

    this.userService.getParamUser(localStorage.getItem('username')).subscribe(data => {
      localStorage.setItem('langue', data.data.uti_lng_id.lngLangue);
      localStorage.setItem('theme', data.data.uti_thm_id.thmName);
      this.langue = localStorage.getItem('langue') ? localStorage.getItem('langue') : 'fr';
      this.theme = localStorage.getItem('theme') ? localStorage.getItem('theme') : 'gainde-green';
      this.installTheme(this.theme);
      this.translate.setDefaultLang(this.langue);
      this.translate.use(localStorage.getItem('langue'));

    }, error => {
      this.getListParam();

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
      if (data.data?.param_img_id)
        this.getLogo(data.data?.param_img_id.imgId);

      this.appName = data.data?.param_nom_app;
      localStorage.setItem("appName", this.appName);
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
    //localStorage.setItem('logo', 'data:image/png;base64,' + logoRef);    
    this.paramService.getImage(logoRef)
      .subscribe(
        res => {
          var base64Data = res.data.imgLogoByte;
          localStorage.setItem('logo', 'data:image/png;base64,' + base64Data);
        }
      );

  }

}
