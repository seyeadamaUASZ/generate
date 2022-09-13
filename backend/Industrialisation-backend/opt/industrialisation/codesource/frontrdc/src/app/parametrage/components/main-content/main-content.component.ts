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
import { ParametreService } from '../../services/parametre.service';
import { Secteur } from '../../models/secteur';
import { ConfirmDialogModel, ConfirmDialogComponent } from 'src/app/sharedcomponent/confirm-dialog/confirm-dialog.component';
import { AjoutQrcodeComponent } from '../../../qrcode/components/ajout-qrcode/ajout-qrcode.component';
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
  breakpoint:any;
  criterePwd: CriterePwd;
  formulairenotgenere:any;
  formulairegenere:any;
  loading:boolean=false;
  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  tabIndex = 0;
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
    ) { }


    onResize(event) {
      this.breakpoint = (event.target.innerWidth <= 400) ? 1 : 5;
    }

  ngOnInit() {
    this.breakpoint = (window.innerWidth <= 400) ? 1 : 5;
    this.route.queryParams.subscribe(params => {
			this.tabIndex = params.index||2;
    });
    this.getSecteur();
    this.infosCriterePwd();
  
  }

  infosCriterePwd() {
    this.criterePwdService.infoCriterePwd().subscribe(data => {
      this.criterePwd = data.data[0];
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




}



