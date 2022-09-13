import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { MatSnackBar, MatDialog } from '@angular/material';
import { TranslateService } from '@ngx-translate/core';
import { ParametreService } from 'src/app/utilisateur/services/parametre.service';
import { StyleManagerService } from 'src/app/shared/style-manager.service';
import { NotificationService } from 'src/app/shared/services/notification.service';
import { ApplicationService } from 'src/app/application/services/application.service';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.scss']
})
export class LandingComponent implements OnInit {
  appName;
  langue;
  theme;
  appsPubliees;
  appLogo;
  constructor(public router: Router, private dialogRef: MatDialog,
    private route: ActivatedRoute,
    public translate: TranslateService,
    private paramService: ParametreService,
    public styleManager: StyleManagerService,
    public notification: NotificationService,
    private appService: ApplicationService
		) { }

  ngOnInit() {
    this.getListParam();
    this.appService.listeAppPubliee().subscribe(data=>{
      this.appsPubliees = data.data;
    });
  }
  goToLogin() {
    this.router.navigate(['/login']);
  }

   openDialogInscription() {    
    this.router.navigate(['/inscription']);
   }

   getListParam() {
    this.langue = localStorage.getItem("langue");
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
      
      this.appName = data.data?.param_nom_app;
      localStorage.setItem("appName",this.appName);  
      if (data.data?.param_img_id){
        this.getLogo(data.data?.param_img_id.imgId);
          // var base64Data = data.data.param_img_id.imgLogoByte;
          // this.appLogo = `data:image/png;base64,${base64Data}`         
          // localStorage.setItem('logo', this.appLogo);
      }
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
          this.appLogo = `data:image/png;base64,${base64Data}`         
          localStorage.setItem('logo', this.appLogo);
        }
      );
      
  }
  
  installTheme(themeName: string) {
    this.styleManager.setStyle('theme', themeName);
  }

 }
