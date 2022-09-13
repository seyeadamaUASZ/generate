import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatPaginator, MatSnackBar, MatSort, MatTableDataSource } from '@angular/material';
import { ActivatedRoute, Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { NotificationService } from 'src/app/shared/services/notification.service';
import { ConfirmDialogComponent, ConfirmDialogModel } from 'src/app/sharedcomponent/confirm-dialog/confirm-dialog.component';
import { DocumentService } from '../../services/document.service';
import { PrivilegeSignerService } from '../../services/privilegeSigner.service';
import * as fileSaver from 'file-saver';
import { SignatureDocumentService } from '../../services/signatureDocument.service';
import { DetailSignatureComponent } from '../detailSignature/detailSignature.component';
import { AjoutDocumentComponent } from '../ajout-document/ajout-document.component';
import { EditDocumentComponent } from '../edit-document/edit-document.component';
import { SignatureComponent } from '../signature/signature.component';

@Component({
  selector: 'app-documentRecu',
  templateUrl: './documentRecu.component.html',
  styleUrls: ['./documentRecu.component.scss']
})
export class DocumentRecuComponent implements OnInit {

  @ViewChild('pdfViewer') pdfViewer: ElementRef;
  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;
	@ViewChild(MatSort) sort: MatSort;
  documents;
  documentSigne;

  dataSource: MatTableDataSource<Document>;
  dataSourceSigner: MatTableDataSource<Document>;
  dataSourceCertifier: MatTableDataSource<Document>;
  dataSourceConsulter: MatTableDataSource<Document>;
  dataSourceDejaSigner: MatTableDataSource<Document>;
  dctId
  fileURL
  blob
  type

  constructor(private router: Router, private documentService:DocumentService,  private dialog: MatDialog,
    private notification: NotificationService,
    private dialogRef: MatDialog,
    private route: ActivatedRoute,
    private privilegeDocumentService:PrivilegeSignerService,
    private signatureDocumentService:SignatureDocumentService,
    private snackBar: MatSnackBar, private translate: TranslateService) {

     }
     displayedColumns: string[] = [ 'dctTitre', 'dctAuteur', 'dctType','dctDate', 'action'];

  ngOnInit() {
  this.getListDocument();
  this.getListSigner();
  this.getListCertifier();
 this.getListConsulter();
 this.getDocumentDejaSigner();
  }
  applyFilter(event: Event) {
		const filterValue = (event.target as HTMLInputElement).value;
		this.dataSourceSigner.filter = filterValue.trim().toLowerCase();
  }
  applyFilterCertifier(event: Event) {
		const filterValue = (event.target as HTMLInputElement).value;
		this.dataSourceCertifier.filter = filterValue.trim().toLowerCase();
  }
  applyFilterConsulter(event: Event) {
		const filterValue = (event.target as HTMLInputElement).value;
		this.dataSourceConsulter.filter = filterValue.trim().toLowerCase();
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

openDialogDeleteDocument(document){
  const message = "Alert.confirm-action";
  const dialogData = new ConfirmDialogModel('document.alert-suppression', message);
  const dialogRef = this.dialog.open(ConfirmDialogComponent, {
    maxWidth: "400px",
    data: dialogData
  });
  dialogRef.afterClosed().subscribe(dialogResult => {
    if (dialogResult === true) {
      this.delete(document);
    }
  });

}

delete(document) {
  let messageSuccess;
  let messageError;
  this.translate.get('document.confirm-suppression').subscribe((res: string) => {
    messageSuccess = res;
  });
  this.translate.get('document.erreur-suppression').subscribe((res: string) => {
    messageError = res;
  });
  this.documentService.deleteDocument(document).subscribe(data => {
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
     this.getListConsulter();
     this.getDocumentDejaSigner();
    });

  }


  consulterDocument(document){
    this.documentService.consulter(document.dctId).subscribe(resp=>{
       this.saveFile(resp.body, "document: "+document.dctTitre);

      });
      this.type = document.dctType
    }
    saveFile(data: any,  filename?: string) {
      const blob = new Blob([data], {type:this.type});
      fileSaver.saveAs(blob,  filename);
     }


     getDocumentDejaSigner(){
      this.signatureDocumentService.listeDocumentSignerByIdUser(localStorage.getItem("id")).subscribe(data=>{
        if(data.statut){
            this.dataSourceDejaSigner = new MatTableDataSource<Document>(data.data);
            this.dataSourceDejaSigner.paginator = this.paginator;
            this.dataSourceDejaSigner.sort = this.sort;

        }
      })

    }
    consulterDetailSignature(document) : void{
        this.dialog.open(DetailSignatureComponent, {
          disableClose: true,
          width: '700px',
          data:document
        }).afterClosed().subscribe(data => {

        });

      }

}
