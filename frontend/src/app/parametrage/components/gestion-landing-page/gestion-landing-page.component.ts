import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ParametreService } from '../../services/parametre.service';
import { FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar, MatSnackBarRef, SimpleSnackBar, DateAdapter, MatDialog } from '@angular/material';
import { TranslateService } from '@ngx-translate/core';
import { NotificationService } from '../../../shared/services/notification.service';
import { AddpatnerComponent } from '../addpatner/addpatner.component';
@Component({
  selector: 'app-gestion-landing-page',
  templateUrl: './gestion-landing-page.component.html',
  styleUrls: ['./gestion-landing-page.component.scss']
})
export class GestionLandingPageComponent implements OnInit {
  Themes;
  Langues;
  usernam;
  Image;
  selectedFile1: File;
  selectedFile2: File;
  selectedFile3: File;
  selectedFile4: File;
  selectedFile5: File;
  selectedFile6: File;
  selectedFile7: File;


  retreivedImage1: any;
  retreivedImage2: any;
  retreivedImage3: any;
  retreivedImage4: any;
  retreivedImage5: any;
  retreivedImage6: any;
  retreivedImage7: any;
  retreivedImage8: any;
  retreivedImage9: any;
  retreivedImage10: any;

  base64Data5: any;
  base64Data1: any;
  base64Data2: any;
  base64Data3: any;
  base64Data4: any;
  base64Data6: any;
  base64Data7: any;
  base64Data8: any;
  base64Data9: any;
  base64Data10: any;


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
    public dateAdapter: DateAdapter<Date>, private route: Router,public dialog:MatDialog

  ) { }
  LandingForm = this.formBuilder.group({
    lndId: [],
    lndIm1: [],
    lndIm2: [],
    lndIm3: [],
    lndIm4: [],
    lndIm5: [],
    lndIm6: [],
    lndIm7: [],
    lndIm8: [],
    lndIm9: [],
    lndIm10: [],


    username: [localStorage.getItem('username'), Validators.required],

  });
  ngOnInit() {
    this.getImagelanding();

  }
  uploadImageData = new FormData();

  updateParametre() {
    this.uploadImageData.append('username', localStorage.getItem('username'));
    //alert("file name"+this.selectedFile1)
    this.paramService.chargerImageLanding(this.uploadImageData).subscribe(data => {
      this.translate.get('landing.imgCharger').subscribe((res: string) => {
        this.notification.info(res);
        //localStorage.setItem('logo', 'data:image/png;base64,' + this.base64Data);
      });
      location.reload;
    });
  }

  getImagelanding() {
    this.paramService.getImageLanding().subscribe(data => {
      console.log(data);
      if (data.statut) {
        this.retreivedResponse = data.data;
        this.base64Data1 = this.retreivedResponse.lndIm1;
        this.base64Data2 = this.retreivedResponse.lndIm2;
        this.base64Data3 = this.retreivedResponse.lndIm3;
        this.base64Data4 = this.retreivedResponse.lndIm4;
        this.base64Data5 = this.retreivedResponse.lndIm5;
        this.base64Data6 = this.retreivedResponse.lndIm6;
        this.base64Data7 = this.retreivedResponse.lndIm7;


        //alert('hello'+this.retreivedResponse);
        this.retreivedImage1 = 'data:image/png;base64,' + this.base64Data1;
        this.retreivedImage2 = 'data:image/png;base64,' + this.base64Data2;
        this.retreivedImage3 = 'data:image/png;base64,' + this.base64Data3;
        this.retreivedImage4 = 'data:image/png;base64,' + this.base64Data4;
        this.retreivedImage5 = 'data:image/png;base64,' + this.base64Data5;
        this.retreivedImage6 = 'data:image/png;base64,' + this.base64Data6;
        this.retreivedImage7 = 'data:image/png;base64,' + this.base64Data7;


      } else {
        this.retreivedResponse = false;
      }
    })
  }

  public onFileChanged1(even) {
    this.selectedFile1 = even.target.files[0];
    this.uploadImageData.append('imageFile1', this.selectedFile1, this.selectedFile1.name);
    //alert("-------------"+ this.selectedFile1 );
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
      this.retreivedImage1 = reader.result;
    }
    
  }


  public onFileChanged2(even) {
    this.selectedFile2 = even.target.files[0];
    this.uploadImageData.append('imageFile2', this.selectedFile2, this.selectedFile2.name);
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
      this.retreivedImage2 = reader.result;
    }

  }
  public onFileChanged3(even) {
    this.selectedFile3 = even.target.files[0];
    this.uploadImageData.append('imageFile3', this.selectedFile3, this.selectedFile3.name);
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
      this.retreivedImage3 = reader.result;
    }

  }
  public onFileChanged4(even) {
    this.selectedFile4 = even.target.files[0];
    this.uploadImageData.append('imageFile4', this.selectedFile4, this.selectedFile4.name);
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
      this.retreivedImage4 = reader.result;
    }

  }
  public onFileChanged5(even) {
    this.selectedFile5 = even.target.files[0];
    this.uploadImageData.append('imageFile5', this.selectedFile5, this.selectedFile5.name);
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
      this.retreivedImage5 = reader.result;
    }

  }


  public onFileChanged6(even) {
    this.selectedFile6 = even.target.files[0];
    this.uploadImageData.append('imageFile6', this.selectedFile6, this.selectedFile6.name);
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
      this.retreivedImage6 = reader.result;
    }

  }

  public onFileChanged7(even) {
    this.selectedFile7 = even.target.files[0];
    this.uploadImageData.append('imageFile7', this.selectedFile7, this.selectedFile7.name);
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
      this.retreivedImage7 = reader.result;
    }

  }

  openDialogAddWidget() {
    const dialog1 = this.dialog.open(AddpatnerComponent, {
      disableClose: true,
      width: '200px' 
    }).afterClosed().subscribe(result => {
    });

  }


}
