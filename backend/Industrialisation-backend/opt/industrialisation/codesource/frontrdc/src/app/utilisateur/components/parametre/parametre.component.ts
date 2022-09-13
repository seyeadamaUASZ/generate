
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ParametreService } from '../../services/parametre.service';
import { FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar, MatSnackBarRef, SimpleSnackBar, DateAdapter } from '@angular/material';
import { TranslateService } from '@ngx-translate/core';
import { NotificationService } from '../../../shared/services/notification.service';

@Component({
  selector: 'app-parametre',
  templateUrl: './parametre.component.html',
  styleUrls: ['./parametre.component.scss']
})
export class ParametreComponent implements OnInit {
  Themes;
  Langues;
  usernam;
  Image;
  selectedFile: File;
  retreivedImage: any;
  base64Data: any;
  retreivedResponse: any;
  imageId: any;
  mimeType: any;
  loading = false;
  defaultTheme;
  defaultLangue;
  defaultNomApp;
  app;
  //constructor(public paramService: ParametreService, public formBuilder: FormBuilder) { }

  constructor(public paramService: ParametreService, public formBuilder: FormBuilder,
    private snackBar: MatSnackBar, private notification: NotificationService, private translate: TranslateService,
    public dateAdapter: DateAdapter<Date>, private route: Router

  ) { }
  ParametreForm = this.formBuilder.group({
    param_lng_id: [],
    param_thm_id: [],
    param_img_id: [],
    param_nom_app: [localStorage.getItem('appName'), Validators.required],
    paramUtiUsername: [localStorage.getItem('username'), Validators.required],
  });
  ngOnInit() {
    this.listTheme();
    this.listLangue();

    this.getListParam();
   
    //this.onUpload();
  }

  listTheme() {
    this.paramService.getTheme().subscribe(data => {
    //  console.log(data);
      this.Themes = data.data;
    });
  }
  listLangue() {
    this.paramService.getLangue().subscribe(data => {
     // console.log(data);
      this.Langues = data.data;
    });
  }

  updateParametre() {
      const uploadImageData = new FormData();
      if (this.ParametreForm.value.param_img_id) {
        uploadImageData.append('imageFile', this.selectedFile, this.selectedFile.name);      
        this.paramService.uplaodImage(uploadImageData).subscribe(data => {
          this.ParametreForm.value.param_img_id = data.data.imgId ? data.data.imgId : null;
          this.paramService.updateParametre(this.ParametreForm.value).subscribe(data => {
            this.translate.get(data.description).subscribe((res: string) => {
              this.notification.info(res);
              this.getListParam();
              localStorage.removeItem('logo');
              localStorage.setItem('logo', 'data:image/png;base64,' + this.base64Data);
              location.reload();
             // this.route.navigate(['/roles']);
            });
          }
          );
        });
      } else {
        this.paramService.updateParametre(this.ParametreForm.value).subscribe(data => {
          this.translate.get(data.description).subscribe((res: string) => {
            this.notification.info(res);
            this.getListParam();
            location.reload();
           // this.route.navigate(['/roles']);
          });
        });
      }

      // this.paramService.updateParametre(this.ParametreForm.value).subscribe(data => {
      //   console.log(data);
      //   const message = 'param.update';
      //   this.translate.get(data.description).subscribe((res: string) => {
      //     this.notification.info(res);
      //   });
      //   //this.openSnackBar(data.description, 'confirmation');
      //   //alert(data.description);


      // });
    
  }
  openSnackBar(message: string, action: string): MatSnackBarRef<SimpleSnackBar> {
    return this.snackBar.open(message, action, {
      duration: 2000,
    });
  }
  //récupere le logo de l'app
  getListParam() {
    this.paramService.getDefautParametre().subscribe(data => {
      console.log(data);
      
      this.defaultTheme = data.data.param_thm_id;
      this.defaultLangue = data.data.param_lng_id;
      this.app = data.data.param_nom_app;
      this.imageId = data.data.param_img_id?.imgId 
      // this.ParametreForm.value.param_nom_app=data.data.param_nom_app;
     // console.log(data.data.param_thm_id);
     // console.log(data.data.param_lng_id);
      this.getImage();
    })
  }

  public onFileChanged(even) {
    this.selectedFile = even.target.files[0];
    if (even.target.files.length === 0)
      return;
    if (even.target.files[0].size > 2000000) {
      //this.openSnackBar("la taille de logo depasse la taille attendue!!", 'Erreur');
      this.translate.get("la taille de logo depasse la taille attendue!!").subscribe((res: string) => {
        this.notification.warn(res);
      });
      return;

    }

    this.mimeType = even.target.files[0].type;
    //alert(this.mimeType);
    if (this.mimeType.match(/image\/*/) == null) {
      //alert("seul les images sont supportées!!!.");
      this.translate.get("seul les images sont supportées!!!.").subscribe((res: string) => {
        this.notification.warn(res);
      });
      return;
    }

    var reader = new FileReader();
    reader.readAsDataURL(even.target.files[0]);
    reader.onload = (_event) => {
      this.retreivedImage = reader.result;
    }
  }
  getImage() {
    // alert("------------------getImage--- parametre component-------------------- "+this.imageId);
    //Make a call to Spring Boot to get the Image Bytes.
    this.paramService.getImage(this.imageId)
      .subscribe(
        res => {
          this.retreivedResponse = res;
          this.base64Data = this.retreivedResponse.data.imgLogoByte;
          this.retreivedImage = 'data:image/png;base64,' + this.base64Data;
        }
      );

  }
  compareTheme(t1, t2): boolean {
    return t1 && t2 ? t1.thmName === t2.thmName : false;
  }
  compareLangue(l1, l2): boolean {
    return l1 && l2 ? l1.lngLangue === l2.lngLangue : false;
  }

  switchLang(lang: string) {
    localStorage.setItem('langue', lang);
    this.useLang(lang);
    //alert("alert langue value"+JSON.stringify(this.userForm.value));
  }

  useLang(lang: string) {
    this.translate.use(lang);
    this.dateAdapter.setLocale(lang);
  }

}
