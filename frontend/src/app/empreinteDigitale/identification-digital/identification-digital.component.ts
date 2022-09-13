import { Component, OnInit } from '@angular/core';
import { Validators, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material';
import { NotificationService } from 'src/app/shared/services/notification.service';
import { ServiceVisageService } from 'src/app/reconnaissanceVisage/services/service-visage.service';
import { AuthService } from 'src/app/utilisateur/services/auth.service';
import { UserService } from 'src/app/utilisateur/services/user.service';
import { StyleManagerService } from 'src/app/shared/style-manager.service';
import { ParametreService } from 'src/app/utilisateur/services/parametre.service';
import { TranslateService } from '@ngx-translate/core';
import { ServicesService } from '../services/services.service';

@Component({
  selector: 'app-identification-digital',
  templateUrl: './identification-digital.component.html',
  styleUrls: ['./identification-digital.component.scss']
})
export class IdentificationDigitalComponent implements OnInit {
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
  constructor(private formBuilder: FormBuilder, private router: Router,
    private _snackBar: MatSnackBar,
    private notification: NotificationService,
    private visageService: ServiceVisageService,
    private auth: AuthService,
    public userService: UserService,
    public styleManager: StyleManagerService,
    public paramService: ParametreService,
    private serviceFinger:ServicesService,
    private translate: TranslateService) { }

  ngOnInit() {
    this.identificationAction();
  }

identificationAction(){
  this.serviceFinger.identFingerZk().subscribe(data=>{
    this.translate.get("posez votre doigt sur le voyant allumant du capteur").subscribe((res: string) => {
      this.notification.success(res);
});

  })
}
 
identificationUser(){
  this.serviceFinger.identUser().subscribe(res=>{
    if(res.id!=0){
      if(res.success=true){
        this.serviceFinger.findByEmpreinte(res.id).subscribe(Response=>{
          this.auth.login('integrateur', 'Gainde2000!').subscribe(data=>{
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
            });
  
          })
        })
      }else{
        this.translate.get("empreinte inconnue!!").subscribe((res: string) => {
          this.notification.success(res);
    });
      }
    }else{
      this.translate.get("enrollez vous!!").subscribe((res: string) => {
        this.notification.success(res);
  });
    }


  })
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

installTheme(themeName: string) {
  this.styleManager.setStyle('theme', themeName);
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
