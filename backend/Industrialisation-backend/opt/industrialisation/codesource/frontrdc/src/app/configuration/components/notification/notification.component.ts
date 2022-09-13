import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { Validators, FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MatPaginator, MatTableDataSource, MatSort, MatDialog, MatSnackBar } from '@angular/material';
// import { DetailUtilisComponent } from '../detail-utilis/detail-utilis.component';
// import { AjoutNotificationComponent } from '../ajout-notification/ajout-notification.component';
// import { EditUtilisComponent } from '../edit-notification/edit-notification.component';
import { ConfirmDialogModel, ConfirmDialogComponent } from 'src/app/sharedcomponent/confirm-dialog/confirm-dialog.component';
import { TranslateService } from '@ngx-translate/core';
import { AjoutEvenementComponent } from './ajout-evenement/ajout-evenement.component';
import { AjoutNotificationComponent } from './ajout-notification/ajout-notification.component';
import { EditNotificationComponent } from './edit-notification/edit-notification.component';
import { NotificationMessage } from 'src/app/parametrage/models/notification';
import { NotificationServiceMessage } from 'src/app/parametrage/services/notification.service';

@Component({
	selector: 'app-notification',
	templateUrl: './notification.component.html',
	styleUrls: ['./notification.component.scss']
})
export class NotificationComponent implements AfterViewInit {

	displayedColumns: string[] = ['ntfObjet', 'ntfTntId', 'action'];
	@ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;
	@ViewChild(MatSort) sort: MatSort;
	notificationProfils;
	dataSource: MatTableDataSource<NotificationMessage>;
	langue;
	constructor(
		private notificationService: NotificationServiceMessage, private dialogRef: MatDialog,
		private route: ActivatedRoute,
		private formbuild: FormBuilder,
		private _snackBar: MatSnackBar,
		private translate: TranslateService,
		private router: Router) {
	}
	applyFilter1(event: Event) {
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
		this.notificationService.listeNotificationProfils().subscribe(data => {
			if (data) {
				this.notificationProfils = data.data;
				console.log(this.notificationProfils);
				this.dataSource = new MatTableDataSource<NotificationMessage>(this.notificationProfils);
				this.dataSource.paginator = this.paginator;
				this.dataSource.sort = this.sort;
			} else {
				this.translate.get("error").subscribe((res: string) => {
					this.notificationProfils.warn(res);
				});
			}
		});
	}

	sortingCaseInsentive() {
		return (data, sortHeaderId) => data[sortHeaderId].toLocaleLowerCase();
	}


	openDialogAdd(): void {
		const dialog = this.dialogRef.open(AjoutNotificationComponent, {
			width: '700px',
			disableClose: true
		}).afterClosed().subscribe(result => {
			//location.reload();
			this.listNotification();
		});
	}

	openDialogUpdate(notification) {
		const dialog1 = this.dialogRef.open(EditNotificationComponent, {
			disableClose: true,
			width: '700px',
			data: notification
		}).afterClosed().subscribe(result => {
			this.listNotification();
		});
	}



	openDialogEvenement(notification) {
		const dialog1 = this.dialogRef.open(AjoutEvenementComponent, {
			disableClose: true,
			data: notification
		}).afterClosed().subscribe(result => {
			//location.reload();
			this.listNotification();
		});
	}



}
