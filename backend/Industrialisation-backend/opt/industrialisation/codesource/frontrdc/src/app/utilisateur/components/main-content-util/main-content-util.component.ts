import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { Validators, FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MatPaginator, MatTableDataSource, MatSort, MatDialog, MatSnackBar, MatTable } from '@angular/material';
import { User } from '../../models/user';
import { Profile } from '../../models/profile';
import { DetailUtilisComponent } from '../detail-utilis/detail-utilis.component';
import { AjoutUtilisComponent } from '../ajout-utilis/ajout-utilis.component';
import { EditUtilisComponent } from '../edit-utilis/edit-utilis.component';
import { UserService } from '../../services/user.service';
import { ConfirmDialogModel, ConfirmDialogComponent } from 'src/app/sharedcomponent/confirm-dialog/confirm-dialog.component';
import { TranslateService } from '@ngx-translate/core';


@Component({
	selector: 'app-main-content-util',
	templateUrl: './main-content-util.component.html',
	styleUrls: ['./main-content-util.component.scss']
})
export class MainContentUtilComponent implements AfterViewInit {
	@ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;
	@ViewChild(MatSort) sort: MatSort;
	@ViewChild(MatTable) table: MatTable<any>;

	user;
	dataSource: MatTableDataSource<User>;
	tabIndex: any;
	langue;
	chartOptions = {
		responsive: true
	};
	chartData1 = [
		{ data: [0, 6, 2, 3, 6, 2, 7, 3, 6, 2, 1, 4], label: 'Applications' },
		{ data: [0, 14, 10, 4, 5, 10, 3, 3, 6, 6, 2, 9], label: 'Workflows' },
		{ data: [0, 26, 12, 21, 30, 11, 25, 10, 34, 15, 24, 19], label: 'Formulaires' }
	];
	chartLabels1 = ['January', 'February', 'Mars', 'April', 'Mai', 'Juin', 'Juillet', 'Aout', 'Septembre', 'Octobre', 'Novembre', 'Décembre'];
	chartData2 = [
		{ data: [3, 16, 2, 13, 6, 8, 7, 13, 16, 12, 11, 14], label: 'Utilisateurs' },
		{ data: [2, 14, 10, 4, 5, 10, 3, 3, 6, 6, 2, 9], label: 'Connectés' },
	];
	chartLabels2 = ['January', 'February', 'Mars', 'April', 'Mai', 'Juin', 'Juillet', 'Aout', 'Septembre', 'Octobre', 'Novembre', 'Décembre'];

  

	constructor(private usersService: UserService, private dialogRef: MatDialog,
		private route: ActivatedRoute,
		private formbuild: FormBuilder,
		private _snackBar: MatSnackBar,
		private translate: TranslateService,
		private router: Router) {
	}
	displayedColumns: string[] = ['utiPrenom', 'utiNom', 'utiUsername', 'uti_pro_id.proLibelle', 'utiEmail', 'action'];
	userForm = this.formbuild.group({

		utiPrenom: ['', Validators.required],
		utiNom: ['', Validators.required],
		username: ['', Validators.required],
		password: ['', Validators.required],
		utiTelephone: ['', Validators.required],
		email: ['', Validators.required],
		//utiDateCreation: ['', Validators.required],
		//utiAdresse: ['', Validators.required],
		statut: ['', Validators.required],
		uti_pro_id: ['', Validators.required]
	});
	applyFilter(event: Event) {
		const filterValue = (event.target as HTMLInputElement).value;
		this.dataSource.filter = filterValue.trim().toLowerCase();
	}

	ngAfterViewInit() {
		this.listUsers();
	}
	ngOnInit() {
		this.route.queryParams.subscribe(params => {
			this.tabIndex = params.index;
		});
	}

	listUsers() {
		this.usersService.listeUser().subscribe(data => {
			if (data.statut) {
				this.user = data.data.reverse();
				//console.log('------------------------------');
				console.log(this.user);
				this.dataSource = new MatTableDataSource<User>(data.data);
				//console.log(JSON.stringify(data.data));
				//this.paginator._intl.itemsPerPageLabel = 'Nombre de ligne';
				this.dataSource.paginator = this.paginator;
				this.dataSource.sort = this.sort;
			} else {
				window.alert(data.description);
			}

		})
	}


	openDialogAdd(): void {
		const dialog = this.dialogRef.open(AjoutUtilisComponent, {
			disableClose: true,
			width: '700px',

		}).afterClosed().subscribe(result => {
			//location.reload();
			//this.table.renderRows();
			this.listUsers();
		});

	}
	openDialogDroit() {
		const dialog1 = this.dialogRef.open(DetailUtilisComponent, {
			disableClose: true,
			width: '800px',

		}).afterClosed().subscribe(result => {
			//location.reload();
			//this.table.renderRows();
			//this.listUsers();
		});

	}
	openDialogUpdate(username) {
		//console.log(username);
		const dialog1 = this.dialogRef.open(EditUtilisComponent, {
			disableClose: true,
			width: '700px',
			data: username
		}).afterClosed().subscribe(result => {
			//location.reload();
			this.listUsers();
		});

	}
	openDialogDetail(username) {		 
		const dialog1 = this.dialogRef.open(DetailUtilisComponent, {
			disableClose: true,
			width: '700px',
			data: username
		}).afterClosed().subscribe(result => {
			//location.reload();
			//this.listUsers();
			this.table.renderRows();
		});

	}
	openDialogActive(utiId) {
		let messageActive
		this.translate.get('utilisateur.confirm-active').subscribe((res: string) => {
			messageActive = res;
		});
		const message = "Alert.confirm-action";
		const dialogData = new ConfirmDialogModel("utilisateur.alert-active", message);
		const dialogRef = this.dialogRef.open(ConfirmDialogComponent, {
			disableClose: true,
			maxWidth: "400px",
			data: dialogData
		});
		dialogRef.afterClosed().subscribe(dialogResult => {
			if (dialogResult === true) {
				this.actived(utiId, messageActive);
			}
		});
	}

	openDialogDesactive(utiId) {
		let messageDesactive;
		this.translate.get('utilisateur.confirm-desactive').subscribe((res: string) => {
			messageDesactive = res;
		});
		const message = "Alert.confirm-action";
		const dialogData = new ConfirmDialogModel("utilisateur.alert-desactive", message);
		const dialogRef = this.dialogRef.open(ConfirmDialogComponent, {
			disableClose: true,
			maxWidth: "400px",
			data: dialogData
		});
		dialogRef.afterClosed().subscribe(dialogResult => {
			if (dialogResult === true) {
				this.desactived(utiId, messageDesactive);
			}
		});
	}

	openDialogDeleteUser(username) {
		const message = "Alert.confirm-action";
		const dialogData = new ConfirmDialogModel("Alert.suppression", message);
		const dialogRef = this.dialogRef.open(ConfirmDialogComponent, {
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
		this.usersService.deleteUser(username).subscribe(data => {
			if (data.statut) {
				this._snackBar.open(messageSuccess, 'Verification', {
					duration: 2000,
				});
			} else {
				this._snackBar.open(messageError, 'Verification', {
					duration: 2000,
				});
			}
			this.listUsers();
		});
	}
	statut(username) {

	}

	actived(utiId: any, message: any) {
		this.usersService.activer(utiId).subscribe(res => {
			this._snackBar.open(message, 'Verification', {
				duration: 2000,
			});
			this.listUsers();
		})
	}
	desactived(utiId: any, message: any) {
		this.usersService.desactiver(utiId).subscribe(res => {
			this._snackBar.open(message, 'Verification', {
				duration: 2000,
			});
			this.listUsers();
		})
	}

	onChartClick($event) {

	}
	// loardChart(){
	// 	this.chart = new Chart('canvas', {
	// 		type: 'bar',
	// 		data: {
	// 		  labels: [ 'max', 'moy', 'moi', 'min'],
	// 		  datasets: [{
	// 			data: [ 590, 400, 500, 300 ],
	// 			backgroundColor: [
	// 			  'rgba(255, 99, 132, 0.2)',
	// 			  'rgba(54, 162, 235, 0.2)',
	// 			  'rgba(255, 206, 86, 0.2)',
	// 			  'rgba(60, 50, 192, 0.2)',
	// 			  'rgba(153, 102, 255, 0.2)',
	// 			  'rgba(255, 159, 64, 0.2)',
	// 			  'rgba(105, 036, 27, 0.2)'
	// 			],
	// 			borderColor: [
	// 			  'rgba(255,99,132,1)',
	// 			  'rgba(54, 162, 235, 1)',
	// 			  'rgba(255, 206, 86, 1)',
	// 			  'rgba(60, 50, 192, 1)',
	// 			  'rgba(153, 102, 255, 1)',
	// 			  'rgba(255, 159, 64, 1)',
	// 			  'rgba(105, 036, 27, 1)'
	// 			],
	// 			borderWidth: 1
	// 		  }
	// 		]
	// 		},
	// 		options: {
	// 		  scales: {
	// 			yAxes: [{
	// 			  ticks: {
	// 				beginAtZero: true
	// 			  }
	// 			}]
	// 		  },
	// 		  legend: {
	// 			display: false,
	// 			position: 'top',
	// 			labels: {
	// 			  boxWidth: 10,
	// 			  fontSize: 10
	// 			}
	// 		  },
	// 		  title: {
	// 			position: 'top',
	// 			display: true,
	// 			text: 'Appels Traités',
	// 			fontSize: 10
	// 		  }
	// 		}
	// 	  });


	// }

}
