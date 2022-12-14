import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatPaginator, MatSnackBar, MatSort, MatTableDataSource } from '@angular/material';
import { ActivatedRoute, Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { WorkflowService } from 'src/app/workflow/services/workflow.service';
import { UserService } from 'src/app/utilisateur/services/user.service';
import { AddformulaireComponent } from '../addformulaire/addformulaire.component';
import { VisualiserFormComponent } from '../visualiserForm/visualiserForm.component';
import { ConfirmDialogComponent, ConfirmDialogModel } from 'src/app/sharedcomponent/confirm-dialog/confirm-dialog.component';
import { NotificationService } from 'src/app/shared/services/notification.service';
import { ThemeService } from 'ng2-charts';
import { Workflowform } from '../../models/workflowform';
import { AssemblerformComponent } from '../assemblerform/assemblerform.component';
import { EditformulaireComponent } from '../editformulaire/editformulaire.component';
import * as fileSaver from 'file-saver';
import { Addformulairev2Component } from '../addformulairev2/addformulairev2.component';
import { Workflowform2 } from '../../models/workflowform2';


@Component({
  selector: 'app-liste-formulairev2',
  templateUrl: './liste-formulairev2.component.html',
  styleUrls: ['./liste-formulairev2.component.scss']
})
export class ListeFormulairev2Component implements OnInit {
  wrkform:any
  wrkformGenere:any
  idwrkf:any

  containerId:any

  elementform:any
  loading:boolean
  filelink:any
  typedoc:any
  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  dataSourceFrmGenerer: MatTableDataSource<Workflowform2>;
  dataSourceFrmNoGenerer: MatTableDataSource<Workflowform2>;
  displayedColumns: string[] = ['name', 'wfcPrimaire','action'];
  constructor(
    private route: ActivatedRoute,private _snackBar: MatSnackBar, 
    private dialog: MatDialog,private workflowService:WorkflowService,private notification: NotificationService, private translate: TranslateService) { }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.idwrkf = params.get("idwrkf")
      this.containerId = params.get("containerid")
    })
    this.listFormulaireworkflow2(this.idwrkf);
   // this.listFormulaireworkflowGenerer();
  }
  applyFilter(event: Event) {
		const filterValue = (event.target as HTMLInputElement).value;
		this.dataSourceFrmGenerer.filter = filterValue.trim().toLowerCase();
  }
  applyFilterg(event: Event) {
		const filterValue = (event.target as HTMLInputElement).value;
		this.dataSourceFrmNoGenerer.filter = filterValue.trim().toLowerCase();
	}

  listFormulaireworkflow2(idwrkf) {
    this.workflowService.listFormulaireworkflow2(idwrkf).subscribe(data => {
    if(data.statut){
      this.wrkform = data.data;
      console.log('+++++++++++++++++++++++++++++++++++++'+JSON.stringify(this.wrkform))
        this.dataSourceFrmNoGenerer = new MatTableDataSource<Workflowform2>(data.data.reverse());
        this.dataSourceFrmNoGenerer.paginator = this.paginator;
        this.dataSourceFrmNoGenerer.sort = this.sort;

    }

    })
  }
  /*listFormulaireworkflowGenerer() {
    this.workflowService.listFormulaireGenerer(this.idwrkf).subscribe(data => {
    if(data.statut){
      this.wrkformGenere = data.data;
      console.log(JSON.stringify(this.wrkformGenere))
        this.dataSourceFrmGenerer = new MatTableDataSource<Workflowform>(data.data.reverse());

        this.dataSourceFrmGenerer.paginator = this.paginator;
        this.dataSourceFrmGenerer.sort = this.sort;

    }

    })

  }*/
  genererFrm(element){
    this.loading = true;
    this.workflowService.genererFrm("frontrdc",element.jfrmId).subscribe(data=>{
      this.loading = false;
      this.translate.get('formulaire.successgenerer').subscribe((res: string) => {
        this.notification.success(res);
     });
   // this.listFormulaireworkflowGenerer();
    this.listFormulaireworkflow2(this.idwrkf);
    })
  }
   confirmGenerer(element){
     let messageDesactive
    this.translate.get('formulaire.confirmegeneration').subscribe((res: string) => {
      messageDesactive = res;
    });
    const message = "Alert.confirm-action";
    const dialogData = new ConfirmDialogModel("formulaire.confirmegeneration", message);
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      disableClose: true,
      maxWidth: "400px",
      data: dialogData
    });
    dialogRef.afterClosed().subscribe(dialogResult => {
      if (dialogResult === true) {
        this.genererFrm(element);
      }
    });
   }

  


  openDialogAddform(containerId): void {
    this.elementform= {
      wkfConteneur: this.containerId,
      idwrkf: this.idwrkf

    };


    const dialog1 = this.dialog.open(Addformulairev2Component , {
      disableClose: true,
      data : this.elementform,
      width: '700px',
    }).afterClosed().subscribe(result => {
      this.listFormulaireworkflow2(this.idwrkf);

    });
  }
  visualiserFormulaire(element){
    const dialog1 = this.dialog.open(VisualiserFormComponent, {
      disableClose: true,
      data : element,
      width: '700px',
    }).afterClosed().subscribe(result => {
      this.listFormulaireworkflow2(this.idwrkf);

    });
  }
  assemblerFormulaire(element){
    this.elementform= { 
      wkfConteneur: this.containerId,
      idwrkf: this.idwrkf
    }; 
    const dialog1 = this.dialog.open(AssemblerformComponent, {
      disableClose: true,
      data : this.elementform,
      width: '700px',
    }).afterClosed().subscribe(result => {
       
    });
  }
  

 modifierFormulaire(element){
    const dialog1 = this.dialog.open(EditformulaireComponent, {
      disableClose: true,
      data : element,
      width: '700px',
    }).afterClosed().subscribe(result => {
      this.listFormulaireworkflow2(this.idwrkf);

    });
  }
  /*deleteform(element) {
		let messageSuccess;
		let messageError;
		this.translate.get('formulaire.confirm-suppression').subscribe((res: string) => {
			messageSuccess = res;
		});
		this.translate.get('formulaire.erreur-suppression').subscribe((res: string) => {
			messageError = res;
    });
     
		this.workflowService.deleteWrkflForm(element).subscribe(data => {
			if (data.statut) {
				this._snackBar.open(messageSuccess, 'Verification', {
					duration: 2000,
				});
			} else {
				this._snackBar.open(messageError, 'Verification', {
					duration: 2000,
				});
			}
      this.listFormulaireworkflow2(this.idwrkf);
		});
  }*/

  openDialogDeleteWformConfig(workflowformconf) {
    const message = "Alert.confirm-action";
    const dialogData = new ConfirmDialogModel("Supprimer ce workflow", message);
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      disableClose: true,

    maxWidth: "400px",
    data: dialogData
    });
    dialogRef.afterClosed().subscribe(dialogResult => {
    if(dialogResult === true){
      this.delete(workflowformconf);
    }
    });
  }
   
  delete(workflowformconf) {
  
    this.workflowService.deletejbpmform2(workflowformconf).subscribe(data => {
      if (data.statut) {
        this.translate.get('workflow.confirm-suppression').subscribe((res: string) => {           
          this.notification.success(res);
        });         
         
      } else {
        this.translate.get('workflow.erreur-suppression').subscribe((res: string) => {           
          this.notification.error(res);
        });   
        
      }
      this.listFormulaireworkflow2(this.idwrkf);
    });
  }
  

  recupFrmFile(element) {  
    this.filelink = element 
    this.workflowService.downloadFile(this.filelink.replace("opt/formulaire/","").replace("/","")).subscribe(response => {
     
      this.typedoc = response.body.type
      console.log(response.body.type)
      console.log(response.body)
     var contentDisposition = response.headers.get('content-disposition');
            var jbpmfilename = contentDisposition.split(';')[1].split('filename')[1].split('=')[1].trim();
            console.log(jbpmfilename);
    this.saveFile(response.body, jbpmfilename);
    })
  }
  saveFile(data: any, filename?: string) {
 
    const blob = new Blob([data], {type: 'application/octet-stream'+this.typedoc});
    fileSaver.saveAs(blob, filename);
  }

}
