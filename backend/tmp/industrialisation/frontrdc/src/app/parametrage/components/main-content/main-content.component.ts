import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { MatSnackBarRef, SimpleSnackBar, MatDialogConfig, MatDialog, MatSnackBar, MatDialogRef, MatPaginator, MatSort, MatTableDataSource } from '@angular/material';
import { Router, ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { EditCriterePwdComponent } from '../edit-critere-pwd/edit-critere-pwd.component';
// import { ConfirmDialogModel, ConfirmDialogComponent } from 'src/app/sharedcomponent/confirm-dialog/confirm-dialog.component';
import { TranslateService } from '@ngx-translate/core';
import { CriterePwdService } from '../../services/critere-pwd.service';
import { CriterePwd } from '../../models/critere-pwd';
import { NotificationService } from '../../../shared/services/notification.service';
import { AjoutSecteurComponent } from '../ajout-secteur/ajout-secteur.component';
import { ParametreService } from '../../services/parametre.service';
import { Secteur } from '../../models/secteur';
import { EditSecteurComponent } from '../edit-secteur/edit-secteur.component';
import { ConfirmDialogModel, ConfirmDialogComponent } from 'src/app/sharedcomponent/confirm-dialog/confirm-dialog.component';
import { AjoutQrcodeComponent } from '../../ajout-qrcode/ajout-qrcode.component';
import { QrcodeService } from '../../services/qrcode.service';
import { EditQrcodeComponent } from '../../edit-qrcode/edit-qrcode.component';
import * as fileSaver from 'file-saver';



@Component({
  selector: 'app-main-content',
  templateUrl: './main-content.component.html',
  styleUrls: ['./main-content.component.scss']
})
export class MainContentComponent implements OnInit {

  displayedColumns: string[] = [ 'name', 'weight', 'action'];
  dataSource: MatTableDataSource<Secteur>;
  @ViewChild(MatPaginator, { static: true }) paginator1: MatPaginator;
  secteur: Secteur;
  criterePwd: CriterePwd;
  formulairenotgenere:any;
  formulairegenere:any;
  loading:boolean=false;
  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  listeQrcode:any;
  images;
  href:any
  base64Data: any;
  tabIndex = 0;
  based
  fileUrl
  qrcQrcodeByte:any
  retreivedResponse:any;
  retreivedImage:any;
  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private dialog: MatDialog,
    private notification: NotificationService,
    private dialogRef: MatDialog,
    private translate: TranslateService,
    private paramService: ParametreService,
    private criterePwdService: CriterePwdService,
    private qrcodeService:QrcodeService,
    ) { }




  ngOnInit() {
    this.route.queryParams.subscribe(params => {
			this.tabIndex = params.index||2;		
    });
    this.getSecteur();
    this.infosCriterePwd();
    this.getAllQrcode();
    // console.log("*** ---"+this.criterePwd+"--- ***")
    const data = this.base64Data ;
    const blob = new Blob([data], { type: 'image/jpeg' });
   // this.fileUrl = this.sanitizer.bypassSecurityTrustResourceUrl(window.URL.createObjectURL(blob));
   // alert(this.base64Data)
  }
  getImage(qrcId) {
    //Make a call to Spring Boot to get the Image Bytes.
    this.qrcodeService.getimage(qrcId).subscribe( res => {
          this.retreivedResponse = res;
          this.base64Data = this.retreivedResponse.data.qrcQrcodeByte;
          this.retreivedImage = 'data:image/jpeg;base64,' + this.base64Data;
         // alert(this.retreivedImage)
        }
      );

  }
  downloadImage(qrcId){
    this.qrcodeService.download(qrcId).subscribe(resp=>{
    const qrcId = resp.headers.get('qrcId');
    this.saveFile(resp.body,  qrcId);

    });

  }
  saveFile(data: any,  qrcId?: string) {
    const blob = new Blob([data], {type: 'image/png'});
    fileSaver.saveAs(blob,  qrcId); 
  }
  
  getAllQrcode(){
    this.qrcodeService.getallQrcode().subscribe(data=>{
      this.listeQrcode=data.data
        this.base64Data = this.listeQrcode.qrcQrcodeByte;
        this.images = 'data:image/jpg;base64,' + this.base64Data;
        //console.log(JSON.stringify(this.listeQrcode))
        this.qrcQrcodeByte = data.qrcQrcodeByte;
      })
  }

  infosCriterePwd() {
    this.criterePwdService.infoCriterePwd().subscribe(data => {
      this.criterePwd = data.data;
      // alert("*** "+data.data.pwdDure+" ***");

    })
  }
  getSecteur() {
    this.paramService.getListSecteur().subscribe(data => {
      console.log("-------------" + data);
      this.secteur = data.data;
      this.dataSource = new MatTableDataSource<Secteur>(data.data);
      this.dataSource.paginator = this.paginator;


    });
  }

  openDialogUpdate(criterePwd) {
    console.log(criterePwd);
    const dialog1 = this.dialog.open(EditCriterePwdComponent, {
      disableClose: true,
      width: '700px',
      data: criterePwd
    }).afterClosed().subscribe(result => {
      this.infosCriterePwd();
    });

  }
  openDialogDeleteSecteur(username) {
		const message = "secteur.alert-suppression";
		const dialogData = new ConfirmDialogModel("secteur.delete", message);
		const dialogRef = this.dialogRef.open(ConfirmDialogComponent, {
      disableClose: true,
			maxWidth: "400px",
			data: dialogData
		});
		dialogRef.afterClosed().subscribe(dialogResult => {
			if (dialogResult === true) {
        this.deleteSecteur(username);
			}
		});
	}
  openDialogAddSecteur() {
    const dialog2 = this.dialog.open(AjoutSecteurComponent, {
      //  width: '700px',

    }).afterClosed().subscribe(result => {
      // this.infosCriterePwd();
      this.getSecteur();
    });
  }
  openDialogEditSecteur(element) {
    //console.log("hgh"+element);
    const dialog2 = this.dialog.open(EditSecteurComponent, {
      disableClose: true,

      //  width: '700px',
      data: element
    }).afterClosed().subscribe(result => {
      // this.infosCriterePwd();
      this.getSecteur();
    });
  }
  deleteSecteur(element) {
    this.paramService.deleteSecteur(element).subscribe(data => {
      // console.log("fffffffffff"+data.description);
      this.getSecteur();

    });

  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  /*openSnackBar(message: string, action: string): MatSnackBarRef < SimpleSnackBar > {
    return this.snackBar.open(message, action, {
      duration: 2000,
    });
}*/

openDialogAddQrcodes() {
  const dialog2 = this.dialog.open(AjoutQrcodeComponent, {
    disableClose: true,

    //  width: '700px',

  }).afterClosed().subscribe(result => {
    // this.infosCriterePwd();
    this.getAllQrcode();
  });
}
openDialogEditQrcodes(q){
  const dialog2 = this.dialog.open(EditQrcodeComponent, {
    disableClose: true,

    //  width: '700px',
    data:q
  }).afterClosed().subscribe(result => {
    // this.infosCriterePwd();
    this.getAllQrcode();
  });
}
}



