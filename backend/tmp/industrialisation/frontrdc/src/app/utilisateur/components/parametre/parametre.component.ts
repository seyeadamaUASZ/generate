import { Component, OnInit } from '@angular/core';
import { ParametreService } from '../../services/parametre.service';
import { FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar, MatSnackBarRef, SimpleSnackBar } from '@angular/material';
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
  //constructor(public paramService: ParametreService, public formBuilder: FormBuilder) { }

  constructor(public paramService: ParametreService, public formBuilder: FormBuilder,
    private snackBar: MatSnackBar, private notification: NotificationService, private translate: TranslateService
  ) { }
  ParametreForm = this.formBuilder.group({
    param_lng_id: [],
    param_thm_id: [],
    param_img_id: [],
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
      console.log(data);
      this.Themes = data.data;
    });
  }
  listLangue() {
    this.paramService.getLangue().subscribe(data => {
      console.log(data);
      this.Langues = data.data;
    });
  }

  updateParametre() {
    if (this.ParametreForm.valid){
    const uploadImageData = new FormData();
    uploadImageData.append('imageFile', this.selectedFile, this.selectedFile.name);
    this.paramService.uplaodImage(uploadImageData).subscribe(data => {
      this.ParametreForm.value.param_img_id = data.data.imgId ? data.data.imgId:null;
      if (data.statut) {
        //alert(data.description);
        // this.translate.get(data.description).subscribe((res: string) => {
        //   this.notification.success(res);
        // });
      }else{
        this.translate.get('formulaire invalide').subscribe((res: string) => {
          this.notification.error(res);
    });
      }

      this.paramService.updateParametre(this.ParametreForm.value).subscribe(data => {
        this.translate.get(data.description).subscribe((res: string) => {
          this.notification.info(res);
        });
      },error =>{
        this.translate.get(data.description).subscribe((res: string) => {
          this.notification.success(res);
        });
      }
      );
    });
    // this.paramService.updateParametre(this.ParametreForm.value).subscribe(data => {
    //   console.log(data);
    //   const message = 'param.update';
    //   this.translate.get(data.description).subscribe((res: string) => {
    //     this.notification.info(res);
    //   });
    //   //this.openSnackBar(data.description, 'confirmation');
    //   //alert(data.description);


    // });
  }  else{
    this.translate.get('formulaire invalide').subscribe((res: string) => {
      this.notification.error(res);
});
  }
}
  openSnackBar(message: string, action: string): MatSnackBarRef<SimpleSnackBar> {
    return this.snackBar.open(message, action, {
      duration: 2000,
    });
  }
  getListParam() {
    this.paramService.getDefautParametre().subscribe(data => {
      this.imageId = data.data.param_img_id.imgId;
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
    return l1 && l2 ? l1.lnglangue === l2.lnglangue : false;
  }
}
