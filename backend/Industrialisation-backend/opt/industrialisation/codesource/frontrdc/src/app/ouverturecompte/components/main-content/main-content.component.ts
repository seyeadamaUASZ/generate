import { Component, OnInit, ViewChild, AfterViewInit, QueryList, ViewChildren } from '@angular/core';
import { Validators, FormBuilder, FormGroup, FormControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MatPaginator, MatTableDataSource, MatSort, MatDialog, MatSnackBar } from '@angular/material';
import { AddOuverturecompteComponent } from '../add-ouverturecompte/add-ouverturecompte.component';
import { ViewOuverturecompteComponent } from '../view-ouverturecompte/view-ouverturecompte.component';
import { OuverturecompteService } from '../../service/ouverturecompte.service';
import { ConfirmDialogModel, ConfirmDialogComponent } from 'src/app/sharedcomponent/confirm-dialog/confirm-dialog.component';
import { TranslateService } from '@ngx-translate/core';
import { NotificationService } from '../../../shared/services/notification.service';
import * as fileSaver from 'file-saver';
import { merge, Observable } from 'rxjs';
import { AddAssistantclientComponent } from '../add-assistantclient/add-assistantclient.component';
import { AddGestionnairecompteComponent } from '../add-gestionnairecompte/add-gestionnairecompte.component';


@Component({
	selector: 'app-main-content',
	templateUrl: './main-content.component.html',
	styleUrls: ['./main-content.component.scss']
})
export class MainContentComponent implements AfterViewInit {
	@ViewChildren(MatPaginator) paginator: QueryList<MatPaginator> = new QueryList();
	@ViewChild(MatSort) sort: MatSort;
	form;
	result: any
	dataSource: MatTableDataSource<any>;
	dataSource2: MatTableDataSource<any>;
	dataSourceTraites: MatTableDataSource<any>;
	profilLibelle = localStorage.getItem('profileLibelle');

	task: any = []
	status: any
	
	columnDefinitions=['id','nom','prenom','adresse','datenaissance','telephone','cni','certificationresidence','typecompte','typeservice','action'];
    selectedColumns = this.columnDefinitions;
    getDisplayedColumns(){
    	return this.selectedColumns;
    }
	constructor(private ouverturecompteService: OuverturecompteService,
		private dialogRef: MatDialog,
		private route: ActivatedRoute,
		private formBuilder: FormBuilder,
		private translate: TranslateService,
		private notification: NotificationService,
		private router: Router) {
	}

	
	ngAfterViewInit() {
		this.listOuverturecompte();
		this.verifyOuverturecompte();
		if (!this.ouverturecompte) {
			this.listTask(localStorage.getItem('profil'))
		}
		
		this.verifyAssistantclient();
this.verifyGestionnairecompte();

	}
	ngOnInit() {
			}
ouverturecompte = false
	verifyOuverturecompte() {
		this.ouverturecompteService.getAllTask().subscribe(data => {
			this.task = data
			console.log(this.task.data)
			for (let i = 0; i < this.task.data.length; i++) {
				if (this.task.data[i].poOwner.proId == localStorage.getItem("profil") && this.task.data[i].tskFormName == 'Ouverturecompte') {
					this.ouverturecompte = true
					this.listOuverturecompte();
					this.ouverturecompteService.getStatus(this.task.data[i].tskId).subscribe((data: any) => {
						this.status = data.data
					})
					break
				}
			}
		})
	}
listOuverturecompte() {
		this.ouverturecompteService.getOuverturecompteAll(localStorage.getItem('id')).subscribe(data => {
			this.form = data
			if (this.form.statut) {
				this.dataSource = new MatTableDataSource<any>(this.form.data);
				this.dataSource.paginator = this.paginator.toArray()[0];
				this.dataSource.sort = this.sort;
			} else {
				
			}
		})
	}
listTask(poowner) {
		this.ouverturecompteService.getTask(poowner).subscribe(data => {
			this.form = data
			if (this.form.statut) {
				this.dataSource2 = new MatTableDataSource<any>(this.form.data);
				this.dataSource2.paginator = this.paginator.toArray()[0];
				this.dataSource2.sort = this.sort;
				this.getTaskTraite();
			} else {
			}
		})
	}
getTaskTraite() {
		let traitant = null;
		if(this.assistantclient){
traitant = 't1';}
if(this.gestionnairecompte){
traitant = 't2';}
		this.ouverturecompteService.getTaskTraite(localStorage.getItem('profil'), traitant).subscribe((data: any) => {
			if (data.statut) {
				this.dataSourceTraites = new MatTableDataSource<any>(data.data);
				this.dataSourceTraites.paginator = this.paginator.toArray()[1];
				this.dataSourceTraites.sort = this.sort;
			}
		});
	}
 openDialogAdd(): void {
		const dialog = this.dialogRef.open(AddOuverturecompteComponent, {
			width: '700px',
			data: this.status,
			disableClose: true
		}).afterClosed().subscribe(result => {
			location.reload();
			this.listOuverturecompte();
		});

	}
	openDialogEdit(data) {
		const dialog1 = this.dialogRef.open(AddOuverturecompteComponent, {
			width: '700px',
			data: { data: data, status: this.status }
			, disableClose: true
		}).afterClosed().subscribe(result => {
		});
	}
openDialogDetailOuverturecompte(data) {
		const dialog1 = this.dialogRef.open(ViewOuverturecompteComponent, {
			width: '700px',
			data: { data: data, status: this.status }
		}).afterClosed().subscribe(result => {
		});
	}

	assistantclient = false
	verifyAssistantclient() {
		this.ouverturecompteService.getAllTask().subscribe(data => {
			this.task = data
			console.log(this.task.data)
			for (let i = 0; i < this.task.data.length; i++) {
				if (this.task.data[i].poOwner.proId == localStorage.getItem("profil") && this.task.data[i].tskFormName == 'Assistantclient') {
					this.assistantclient = true
					this.ouverturecompteService.getStatus(this.task.data[i].tskId).subscribe((data: any) => {
						this.status = data.data
					})
					break
				}
			}
		})
	}


openDialogAddAssistantclient(data) {
		const dialog1 = this.dialogRef.open(AddAssistantclientComponent, {
			width: '700px',
			data: { data: data, status: this.status }
		}).afterClosed().subscribe(result => {
			location.reload();
			this.listOuverturecompte();
		});
	}


gestionnairecompte = false
	verifyGestionnairecompte() {
		this.ouverturecompteService.getAllTask().subscribe(data => {
			this.task = data
			console.log(this.task.data)
			for (let i = 0; i < this.task.data.length; i++) {
				if (this.task.data[i].poOwner.proId == localStorage.getItem("profil") && this.task.data[i].tskFormName == 'Gestionnairecompte') {
					this.gestionnairecompte = true
					this.ouverturecompteService.getStatus(this.task.data[i].tskId).subscribe((data: any) => {
						this.status = data.data
					})
					break
				}
			}
		})
	}


openDialogAddGestionnairecompte(data) {
		const dialog1 = this.dialogRef.open(AddGestionnairecompteComponent, {
			width: '700px',
			data: { data: data, status: this.status }
		}).afterClosed().subscribe(result => {
			location.reload();
			this.listOuverturecompte();
		});
	}



	
	
	
	
	

	

	
	
}
