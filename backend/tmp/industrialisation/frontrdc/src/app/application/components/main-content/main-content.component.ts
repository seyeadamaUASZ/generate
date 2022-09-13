import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { MatSnackBarRef, SimpleSnackBar, MatDialogConfig, MatDialog, MatSnackBar, MatDialogRef, MatPaginator, MatSort, MatTableDataSource } from '@angular/material';
import { Router, ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { AjoutAppComponent } from '../ajout-app/ajout-app.component';
import { AjoutUtilisComponent } from 'src/app/utilisateur/components/ajout-utilis/ajout-utilis.component';
import { AjoutModuleComponent } from '../ajout-module/ajout-module.component';
import { UserService } from 'src/app/utilisateur/services/user.service';
import { Application } from 'src/app/utilisateur/models/application';
import { ConfirmDialogModel, ConfirmDialogComponent } from 'src/app/sharedcomponent/confirm-dialog/confirm-dialog.component';
import { TranslateService } from '@ngx-translate/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EditAppComponent } from '../detail-app/edit-app.component';
import { EtapeCreationAppComponent } from '../edit-app/etape-creation-app.component';
import { ApplicationService } from '../../services/application.service';
import { NotificationService } from 'src/app/shared/services/notification.service';
import * as fileSaver from 'file-saver';
@Component({
  selector: 'app-main-content',
  templateUrl: './main-content.component.html',
  styleUrls: ['./main-content.component.scss']
})
export class MainContentComponent implements OnInit {
  tabIndex=0;
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  thirdFormGroup: FormGroup;
  isOptional = false;
  breakpoint: any;
  nbrCommerciaux: any;
  nbrAdmin: any;
  nbrAppli: any;
  nbrModule: any;
  nbrIntegrateur: any;
  nbrConnect: any;
  displayedColumns: string[] = [ 'appNom', 'appNomEntreprise', 'action'];
  // displayedColumns: string[] = ['appId', 'appNom', 'appAdresse', 'appNinea', 'appNomEntreprise', 'appEmail', 'action'];

  result;
  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  dataSource: MatTableDataSource<Application>;
  application: any;
  module: any;
  loading: boolean;
  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private dialog: MatDialog,
    private _formBuilder: FormBuilder,
    // private dialogRef: MatDialogRef<AjoutAppComponent>,
    private snackBar: MatSnackBar, private userService: UserService,
    private notification:NotificationService,
    private translate: TranslateService, private usersService: UserService,
    private appService: ApplicationService) {
    this.loading = false;
  }


  listApplication() {
    this.userService.listeApplication().subscribe(data => {
      if (data.statut) {
        this.application = data.data;
        console.log(this.application);
        this.dataSource = new MatTableDataSource<Application>(data.data);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      } else {
      }

    })
  }

  telecharger(id) {
    this.loading = true
    this.appService.zipper(id).subscribe(res => {
      this.result = res
      this.loading = false
      const message = this.result.data;
      const dialogData = new ConfirmDialogModel('Le lien d\'emplacement de votre application', message);
      const dialogRef = this.dialog.open(ConfirmDialogComponent, {
        maxWidth: "400px",
        data: dialogData
      });
    })
  }

  download(id) {
    this.loading = true
    this.appService.downloadFile(id).subscribe(response => {
      this.loading = false
			let blob:any = new Blob([response], { type: 'text/zip; charset=utf-8' });
			const url = window.URL.createObjectURL(blob);
			//window.open(url);
			//window.location.href = response.url;
			fileSaver.saveAs(blob, '.zip');
		}), error => console.log('Error downloading the file'),
                 () => console.info('File downloaded successfully');
  }
  openDialogUpdate(application) {
    this.router.navigate(['/application/detail/', application.appId]);

    console.log(application);
    // const dialog1 = this.dialog.open(EditAppComponent, {
    //  // width: '800px',
    //   data: application
    // }).afterClosed().subscribe(data => {
    //   this.listApplication();
    // });

  }
  openDialogDupliquer(form) {
    let messageDesactive;
    this.translate.get('application.confirmegeneration').subscribe((res: string) => {
      messageDesactive = res;
    });
    const message = "Alert.confirm-action";
    const dialogData = new ConfirmDialogModel("application.confirmegeneration", message);
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      maxWidth: "400px",
      data: dialogData
    });
    dialogRef.afterClosed().subscribe(dialogResult => {
      if (dialogResult === true) {
        this.genererApplication(form);
      }
    });
  }

  genererApplication(id) {
    this.loading = true;
    this.userService.genererFormulaire("frontrdc", id).subscribe(data => {
      this.loading = false;
      this.snackBar.open(data.description, 'Verification', {
        duration: 2000,

      });

    })
  }
  openDialogDeleteApplication(username) {
    const message = "Alert.confirm-action";
    const dialogData = new ConfirmDialogModel('application.alert-suppression', message);
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      disableClose: true,
      maxWidth: "400px",
      data: dialogData
    });
    dialogRef.afterClosed().subscribe(dialogResult => {
      if (dialogResult === true) {
        this.delete(username);
      }
    });
  }
  delete(username) {
    let messageSuccess;
    let messageError;
    this.translate.get('utilisateur.confirm-suppression').subscribe((res: string) => {
      messageSuccess = res;
    });
    this.translate.get('utilisateur.erreur-suppression').subscribe((res: string) => {
      messageError = res;
    });
    this.usersService.deleteApplication(username).subscribe(data => {
      if (data.statut) {
        let invalidForm;
        this.translate.get('application.application-supprime').subscribe((res: string) => {
           this.notification.success(res);
        });

      } else {
        this.translate.get('application.error').subscribe((res: string) => {
          this.notification.error(res);
       });
      }
      this.listApplication();
    });
  }
  statut(username) {

  }
  ngOnInit() {
    this.route.queryParams.subscribe(params => {
			this.tabIndex = params.index;
    	});
    this.breakpoint = (window.innerWidth <= 400) ? 1 : 5;
    this.listApplication();
    this.listeModule();
    this.nbrcomm();
    this.nbrconnect();
    this.nbrintegrateur();
    this.nbrapplication();
    this.nbrmodule();
    this.firstFormGroup = this._formBuilder.group({
      firstCtrl: ['', Validators.required]
    });
    this.secondFormGroup = this._formBuilder.group({
      secondCtrl: ''
    });
    this.thirdFormGroup = this._formBuilder.group({
      thirdCtrl: ''
    });

  }

  listeModule() {
    this.usersService.listeModule().subscribe(res => {
      this.module = res.data;
    })
  }
  nbrmodule() {
    this.userService.nbrModule().subscribe(res => {
      this.nbrModule = res.data;
      console.log(this.module)
    })
  }
  nbrapplication() {
    this.userService.nbrApplication().subscribe(res => {
      this.nbrAppli = res.data;
    })
  }
  nbrintegrateur() {
    this.userService.nbrIntegrateur().subscribe(res => {
      this.nbrIntegrateur = res.data;
    })
  }
  nbrconnect() {
    this.userService.nbrUserConnect().subscribe(res => {
      this.nbrConnect = res.data;
    })
  }
  nbrcomm() {
    this.userService.nbrCommerciaux().subscribe(res => {
      this.nbrCommerciaux = res.data;
    })
  }


  onResize(event) {
    this.breakpoint = (event.target.innerWidth <= 400) ? 1 : 5;
  }

  __cardClick(emp) {
    console.log(emp);
    this.router.navigate(['application', emp.id]);
  }
  openDialogAddApp(): void {
    this.router.navigate(['/application/creation']);
    // const dialog1 = this.dialog.open(AjoutAppComponent, {
    //   width: '1000px',

    // }).afterClosed().subscribe(data => {
    //   this.listApplication();
    // });
  }
  openDialogAddModule(): void {
    const dialog1 = this.dialog.open(AjoutModuleComponent, {
      disableClose: true

    }).afterClosed().subscribe(data => {
      this.listeModule();
      this.nbrModule();
    });
  }
  openDialogEtapeCreation(element) {
    this.router.navigate(['/application/edit/', element.appId]);

    // const dialog1 = this.dialog.open(EtapeCreationAppComponent, {
    // data:element
    // }).afterClosed().subscribe(data => {
    //   this.listApplication();
    //   this.nbrapplication();
    // });


  }


  openSnackBar(message: string, action: string): MatSnackBarRef<SimpleSnackBar> {
    return this.snackBar.open(message, action, {
      duration: 2000,
    });
  }



  openDialogFonctionnaliteApplication(module): void {
    //this.router.navigate(['/application/fonctionnalite/', id]);
    this.router.navigate(['/application/fonctionnalite/', module.modId]);

  }
}
