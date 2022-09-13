import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { Validators, FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MatPaginator, MatTableDataSource, MatSort, MatDialog, MatSnackBar } from '@angular/material';
import { User } from '../../models/user';
// import { DetailUtilisComponent } from '../detail-utilis/detail-utilis.component';
// import { AjoutNotificationComponent } from '../ajout-notification/ajout-notification.component';
// import { EditUtilisComponent } from '../edit-notification/edit-notification.component';
import { NotificationServiceMessage } from '../../services/notification.service';
import { ConfirmDialogModel, ConfirmDialogComponent } from 'src/app/sharedcomponent/confirm-dialog/confirm-dialog.component';
import { TranslateService } from '@ngx-translate/core';
import { NotificationMessage } from '../../models/notification';
import { AjoutEvenementComponent } from './ajout-evenement/ajout-evenement.component';
import { AjoutNotificationComponent } from './ajout-notification/ajout-notification.component';

@Component({
	selector: 'app-notification',
	templateUrl: './notification.component.html',
	styleUrls: ['./notification.component.scss']
})
export class NotificationComponent implements AfterViewInit {
	@ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;
	@ViewChild(MatSort) sort: MatSort;	
	notification;
	dataSource: MatTableDataSource<NotificationMessage>;
   langue;
	constructor(
		private notificationService: NotificationServiceMessage, private dialogRef: MatDialog,
		private route: ActivatedRoute,
		private formbuild: FormBuilder,
		private _snackBar: MatSnackBar, 
		private translate: TranslateService,
		private router: Router
	) {
}
	displayedColumns: string[] = [ 'ntfObjet', 'ntfTntId.tntNom', 'action'];
	// userForm = this.formbuild.group({
	// 	utiPrenom: ['', Validators.required],
	// 	utiNom: ['', Validators.required],
	// 	username: ['', Validators.required],
	// 	password: ['', Validators.required],
	// 	utiTelephone: ['', Validators.required],
	// 	email: ['', Validators.required],
	// 	//utiDateCreation: ['', Validators.required],
	// 	//utiAdresse: ['', Validators.required],
	// 	statut: ['', Validators.required],
	// 	uti_pro_id: ['', Validators.required]
	// });
	applyFilter(event: Event) {
		const filterValue = (event.target as HTMLInputElement).value;
		this.dataSource.filter = filterValue.trim().toLowerCase();
	}

	ngAfterViewInit() {
		this.listNotification();
	}
	ngOnInit() {
	//	this.loardChart();
		}

	listNotification() {
		this.notificationService.listeNotifications().subscribe(data => {
			if (data) {
				this.notification = data.data;
				this.dataSource = new MatTableDataSource<NotificationMessage>(data.data);
				this.dataSource.paginator = this.paginator;
				this.dataSource.sortingDataAccessor = this.sortingCaseInsentive(); 
				this.dataSource.sort = this.sort;
			} else {
				this.translate.get("error").subscribe((res: string) => {
					this.notification.warn(res);
				});
			}

		})
	}

   sortingCaseInsentive() {      
    return (data, sortHeaderId) => data[sortHeaderId].toLocaleLowerCase();
   }


	openDialogAdd(): void {
		const dialog = this.dialogRef.open(AjoutNotificationComponent, {
			width: '800px',
			disableClose: true
		}).afterClosed().subscribe(result => {
			//location.reload();
			this.listNotification();
		});
	}
	
	// openDialogDroit() {
	// 	const dialog1 = this.dialogRef.open(DetailUtilisComponent, {
	// 		width: '800px',

	// 	}).afterClosed().subscribe(result => {
	// 		//location.reload();
	// 		this.listNotification();
	// 	});

	// }
	// openDialogUpdate(username) {
	// 	console.log(username);
	// 	const dialog1 = this.dialogRef.open(EditUtilisComponent, {
	// 		width: '700px',
	// 		data: username
	// 	}).afterClosed().subscribe(result => {
	// 		//location.reload();
	// 		this.listNotification();
	// 	});

	// }

	// openDialogDetail(username) {
	// 	console.log(username);
	// 	const dialog1 = this.dialogRef.open(DetailUtilisComponent, {
	// 		width: '700px',
	// 		data: username
	// 	}).afterClosed().subscribe(result => {
	// 		//location.reload();
	// 		this.listNotification();
	// 	});

	// }

	openDialogEvenement(notification) {
		const dialog1 = this.dialogRef.open(AjoutEvenementComponent, {
			width: '1200px',
			disableClose: true,
			data: notification
		}).afterClosed().subscribe(result => {
			//location.reload();
			this.listNotification();
		});
	}

	// openDialogDesactive(utiId) {
	// 	let messageDesactive;
	// 	this.translate.get('utilisateur.confirm-desactive').subscribe((res: string) => {
	// 		messageDesactive = res;
	// 	});
	// 	const message = "Alert.confirm-action";
	// 	const dialogData = new ConfirmDialogModel("utilisateur.alert-desactive", message);
	// 	const dialogRef = this.dialogRef.open(ConfirmDialogComponent, {
	// 		maxWidth: "400px",
	// 		data: dialogData
	// 	});
	// 	dialogRef.afterClosed().subscribe(dialogResult => {
	// 		if (dialogResult === true) {
	// 			this.desactived(utiId, messageDesactive);
	// 		}
	// 	});
	// }

	// openDialogDeleteUser(username) {
	// 	const message = "utilisateur.alert-suppression";
	// 	const dialogData = new ConfirmDialogModel("utilisateur.titre-suppression", message);
	// 	const dialogRef = this.dialogRef.open(ConfirmDialogComponent, {
	// 		maxWidth: "400px",
	// 		data: dialogData
	// 	});
	// 	dialogRef.afterClosed().subscribe(dialogResult => {
	// 		if (dialogResult === true) {
	// 			this.delete(username);
	// 		}
	// 	});
	// }

	// delete(username) {
	// 	let messageSuccess;
	// 	let messageError;
	// 	this.translate.get('utilisateur.confirm-suppression').subscribe((res: string) => {
	// 		this.notification.success(res);
	// 	});
	// 	this.translate.get('utilisateur.erreur-suppression').subscribe((res: string) => {
	// 		this.notification.warn(res);
	// 	});
	// 	this.usersService.deleteUser(username).subscribe(data => {
	// 		if (data.statut) {
	// 			this.translate.get(messageSuccess).subscribe((res: string) => {
	// 				this.notification.success(res);
	// 			});
	// 			/*
	// 			this._snackBar.open(messageSuccess, 'Verification', {
	// 				duration: 2000,
	// 	});*/
	// 		} else {
	// 			this.translate.get(messageError).subscribe((res: string) => {
	// 				this.notification.warn(res);
	// 			});
	// 			/*this._snackBar.open(messageError, 'Verification', {
	// 				duration: 2000,
	// 	});*/
	// 		}
	// 		this.listNotification();
	// 	});
	// }
	// statut(username) {

	// }

	// actived(utiId: any, message: any) {
	// 	this.usersService.activer(utiId).subscribe(res => {
	// 		this.translate.get(message).subscribe((res: string) => {
	// 				this.notification.success(res);
	// 			});
	// 		/*this._snackBar.open(message, 'Verification', {
	// 			duration: 2000,
	// 	});*/
	// 		this.listNotification();
	// 	})
	// }
	// desactived(utiId: any, message: any) {
	// 	this.usersService.desactiver(utiId).subscribe(res => {
	// 		this.translate.get(message).subscribe((res: string) => {
	// 				this.notification.success(res);
	// 			});
	// 		this.listNotification();
	// 	})
	// }

	// onChartClick($event){
    
  	// }

}
