import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MatPaginator, MatTableDataSource, MatSort, MatDialog, MatSnackBar } from '@angular/material';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AddInscriptionComponent } from '../add-inscription/add-inscription.component';
import { EditInscriptionComponent } from '../edit-inscription/edit-inscription.component';
import { ViewInscriptionComponent } from '../view-inscription/view-inscription.component';
import { InscriptionService } from '../../service/inscription.service';
import { Inscription} from '../../model/inscription';
import { ConfirmDialogModel, ConfirmDialogComponent } from 'src/app/sharedcomponent/confirm-dialog/confirm-dialog.component';
import { TranslateService } from '@ngx-translate/core';
import { merge, Observable } from 'rxjs';
import { NotificationService } from '../../../shared/services/notification.service';
import * as fileSaver from 'file-saver';

@Component({
	selector: 'list-inscription',
	templateUrl: './list-inscription.component.html',
	styleUrls: ['./list-inscription.component.scss']
})
export class ListInscriptionComponent implements AfterViewInit {
	@ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;
	@ViewChild(MatSort) sort: MatSort;
	form;
 result:any
	dataSource: MatTableDataSource<Inscription>;
   langue;
	constructor(private inscriptionService: InscriptionService, private dialogRef: MatDialog,
		private route: ActivatedRoute,
		private formbuild: FormBuilder,
		private _snackBar: MatSnackBar, 
		private translate: TranslateService,
		private notification: NotificationService,		private router: Router) {
	}
	displayedColumns: string[] = ['profile','prenom','datenaiss','nom','action'];

 columnDefinitions = [
{ def: 'profile', label: 'profile', hide: false},{ def: 'prenom', label: 'prenom', hide: false},{ def: 'datenaiss', label: 'datenaiss', hide: false},{ def: 'nom', label: 'nom', hide: false},];
formchamps:FormGroup = new FormGroup({
profile: new FormControl(false),
prenom: new FormControl(false),
datenaiss: new FormControl(false),
nom: new FormControl(false),
});
profile= this.formchamps.get('profile');
prenom= this.formchamps.get('prenom');
datenaiss= this.formchamps.get('datenaiss');
nom= this.formchamps.get('nom');
 getDisplayedColumns():string[] {
    return this.columnDefinitions.filter(cd=>!cd.hide).map(cd=>cd.def);
  }
mesChamps(){let o0:Observable<boolean> = this.profile.valueChanges;
let o1:Observable<boolean> = this.prenom.valueChanges;
let o2:Observable<boolean> = this.datenaiss.valueChanges;
let o3:Observable<boolean> = this.nom.valueChanges;
merge(o0,o1,o2,o3).subscribe( v => { 
this.columnDefinitions[0].hide = this.profile.value;
this.columnDefinitions[1].hide = this.prenom.value;
this.columnDefinitions[2].hide = this.datenaiss.value;
this.columnDefinitions[3].hide = this.nom.value;
});
}
	applyFilter(event: Event) {
		const filterValue = (event.target as HTMLInputElement).value;
		this.dataSource.filter = filterValue.trim().toLowerCase();
	}

	ngAfterViewInit() {
		this.listInscription();
this.mesChamps();	}
	ngOnInit() {
		}

	listInscription() {
		this.inscriptionService.getInscriptionAll().subscribe(data => {
this.form = data
			if (this.form.statut) {
				//console.log('------------------------------');
				console.log(this.form);
				this.dataSource = new MatTableDataSource<Inscription>(this.form.data);
				this.dataSource.paginator = this.paginator;
				this.dataSource.sort = this.sort;
			} else {
				window.alert(this.form.description);
			}
		})
	}


	openDialogAdd(): void {
		const dialog = this.dialogRef.open(AddInscriptionComponent, {
			width: '700px',

		}).afterClosed().subscribe(result => {
			//location.reload();
			this.listInscription();
		});

	}
	openDialogUpdate(username) {
		console.log(username);
		const dialog1 = this.dialogRef.open(EditInscriptionComponent, {
			width: '700px',
			data: username
		}).afterClosed().subscribe(result => {
			//location.reload();
			this.listInscription();
		});
	}
openDialogDeleteUser(username) {
		const message = "utilisateur.alert-suppression";
		const dialogData = new ConfirmDialogModel("utilisateur.titre-suppression", message);
		const dialogRef = this.dialogRef.open(ConfirmDialogComponent, {
			maxWidth: "400px",
			data: dialogData
		});
		dialogRef.afterClosed().subscribe(dialogResult => {
			if (dialogResult === true) {
				this.delete(username);
			}
		});
	}
 delete(data) {
		let messageSuccess;
		let messageError;
		this.translate.get('inscription.confirm-suppression').subscribe((res: string) => {
			messageSuccess = res;
		});
		this.translate.get('inscription.erreur-suppression').subscribe((res: string) => {
			messageError = res;
		});
		this.inscriptionService.deleteInscription(data).subscribe(data => {
				this.result=data
			if (this.result.statut) {
				 this.notification.info(messageSuccess);
			} else {
				 this.notification.error(messageError);
			}
			this.listInscription();
		});
	}
openDialogView(username) {
		console.log(username);
		const dialog1 = this.dialogRef.open(ViewInscriptionComponent, {
			width: '700px',
			data: username
		}).afterClosed().subscribe(result => {
			//location.reload();
			this.listInscription();
		});
	}
downloadImage(data){
		this.inscriptionService.download(data.id).subscribe(resp=>{
		this.saveFile(resp.body, "fichier:"+data.id,data.extention);

		});

	  }
	  saveFile(data: any,  filename?: string, extention?:any) {
      let blob = new Blob([data], {type:extention});
		fileSaver.saveAs(blob,  filename);
	  }}