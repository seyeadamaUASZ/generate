import { Component, OnInit, ViewChild, ElementRef} from '@angular/core';
import {MatDialog, MatSnackBar, MatPaginator, MatSort, MatTableDataSource } from '@angular/material';
import { Router, ActivatedRoute } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { UserService } from 'src/app/utilisateur/services/user.service';
import { EditQrcodeComponent } from 'src/app/qrcode/components/edit-qrcode/edit-qrcode.component';
import { ConfirmDialogModel, ConfirmDialogComponent } from 'src/app/sharedcomponent/confirm-dialog/confirm-dialog.component';
import { NotificationService } from 'src/app/shared/services/notification.service';
import { Document } from '../model/document';
import { DocumentService } from '../services/document.service';
import * as fileSaver from 'file-saver';
import { SignatureComponent } from './signature/signature.component';
import { PrivilegeSignerService } from '../services/privilegeSigner.service';
import { AjoutDocumentComponent } from './ajout-document/ajout-document.component';
import { EditDocumentComponent } from './edit-document/edit-document.component';


@Component({
  selector: 'app-document',
  templateUrl: './document.component.html',
  styleUrls: ['./document.component.scss']
})
export class DocumentComponent implements OnInit {
  @ViewChild('pdfViewer') pdfViewer: ElementRef;
  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;
	@ViewChild(MatSort) sort: MatSort;
  documents;
  documentSigne;

  dataSource: MatTableDataSource<Document>;
  dataSourceSigner: MatTableDataSource<Document>;
  dataSourceCertifier: MatTableDataSource<Document>;
  dataSourceConsulter: MatTableDataSource<Document>;
  dctId
  fileURL
  blob
  type

  constructor(private router: Router, private documentService:DocumentService,  private dialog: MatDialog,
    private notification: NotificationService,
    private dialogRef: MatDialog,
    private route: ActivatedRoute,
    private privilegeDocumentService:PrivilegeSignerService,
    private snackBar: MatSnackBar, private usersService: UserService, private translate: TranslateService) {

     }
     displayedColumns: string[] = [ 'dctTitre', 'dctAuteur', 'dctType','dctDate', 'action'];

  ngOnInit() {
  this.getListDocument();
  this.getListSigner();
  this.getListCertifier();


  }
  applyFilter(event: Event) {
		const filterValue = (event.target as HTMLInputElement).value;
		this.dataSource.filter = filterValue.trim().toLowerCase();
	}

  getListSigner(){
    this.privilegeDocumentService.listDocumentSignerByIdUser(localStorage.getItem("id")).subscribe(data=>{
      if(data.statut){
        this.documentSigne = data.data;

        console.log(JSON.stringify(this.documentSigne))
          this.dataSourceSigner = new MatTableDataSource<Document>(data.data);
          this.dataSourceSigner.paginator = this.paginator;
          this.dataSourceSigner.sort = this.sort;

      }
    })

  }
  getListCertifier(){
    this.privilegeDocumentService.listDocumentCertifierByIdUser(localStorage.getItem("id")).subscribe(data=>{
      if(data.statut){
          this.dataSourceCertifier = new MatTableDataSource<Document>(data.data);
          this.dataSourceCertifier.paginator = this.paginator;
          this.dataSourceCertifier.sort = this.sort;

      }
    })

  }
  getListConsulter(){
    this.privilegeDocumentService. listDocumentConsulterByIdUser(localStorage.getItem("id")).subscribe(data=>{
      if(data.statut){
          this.dataSourceConsulter = new MatTableDataSource<Document>(data.data);
          this.dataSourceConsulter.paginator = this.paginator;
          this.dataSourceConsulter.sort = this.sort;

      }
    })

  }
getListDocument(){
  this.documentService.getDocumentByUser(localStorage.getItem("id")).subscribe(data=>{
    if(data.statut){
      this.documents = data.data;
        this.dataSource = new MatTableDataSource<Document>(data.data);
				this.dataSource.paginator = this.paginator;
				this.dataSource.sort = this.sort;

    }
  })

}
openDialogDeleteDocument(dctId){
  const message = "Alert.confirm-action";
  const dialogData = new ConfirmDialogModel('document.alert-suppression', message);
  const dialogRef = this.dialog.open(ConfirmDialogComponent, {
    maxWidth: "400px",
    data: dialogData
  });
  dialogRef.afterClosed().subscribe(dialogResult => {
    if (dialogResult === true) {
      this.delete(dctId);
    }
  });

}

delete(dctId) {
  let messageSuccess;
  let messageError;
  this.translate.get('document.confirm-suppression').subscribe((res: string) => {
    messageSuccess = res;
  });
  this.translate.get('document.erreur-suppression').subscribe((res: string) => {
    messageError = res;
  });
  this.documentService.deleteDocument(dctId).subscribe(data => {
    if (data.statut) {
      this.snackBar.open(messageSuccess, 'Verification', {
        duration: 2000,
      });
    } else {
      this.snackBar.open(messageError, 'Verification', {
        duration: 2000,
      });
    }
  this.getListDocument();
  });
}



  openDialogAdd(): void{
    //this.router.navigate(['/document/creation']);
    this.dialog.open(AjoutDocumentComponent, {
      disableClose: true,
      width: '700px',
    }).afterClosed().subscribe(data => {
      this.getListDocument();

    });

  }

  openDialogUpdate(document): void{
    this.dialog.open(EditDocumentComponent, {
      disableClose: true,
      width: '700px',
      data:document
    }).afterClosed().subscribe(data => {
      this.getListDocument();

    });

  }

  openDialogSignerDocument(document): void{
    this.dialog.open(SignatureComponent, {
      disableClose: true,
      width: '700px',
      data:document
    }).afterClosed().subscribe(data => {
      this.getListDocument();
      this.getListSigner();
      this.getListCertifier();

    });

  }

  openDialogConsultDocument(document){
    this.documentService.consulter(document.dctId).subscribe(resp=>{
       this.saveFile(resp.body,document.dctTitre);

      });
      this.type = document.dctType
    }
    saveFile(data: any,  filename?: string) {
      const blob = new Blob([data], {type:this.type});
      fileSaver.saveAs(blob,  filename);
     }
}
