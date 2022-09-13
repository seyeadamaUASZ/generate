import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { Validators, FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MatPaginator, MatTableDataSource, MatSort, MatDialog, MatSnackBar } from '@angular/material';
import { AddCommandeComponent } from '../add-commande/add-commande.component';
import { EditCommandeComponent } from '../edit-commande/edit-commande.component';
import { ViewCommandeComponent } from '../view-commande/view-commande.component';
import { CommandeService } from '../../service/Commande.service';
import { Commande} from '../../model/commande';
import { ConfirmDialogModel, ConfirmDialogComponent } from 'src/app/sharedcomponent/confirm-dialog/confirm-dialog.component';
import { TranslateService } from '@ngx-translate/core';


@Component({
	selector: 'list-Commande',
	templateUrl: './list-Commande.component.html',
	styleUrls: ['./list-Commande.component.scss']
})
export class ListCommandeComponent implements AfterViewInit {
	@ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;
	@ViewChild(MatSort) sort: MatSort;
	form;
 result:any
	dataSource: MatTableDataSource<Commande>;
   langue;
	constructor(private commandeService: CommandeService, private dialogRef: MatDialog,
		private route: ActivatedRoute,
		private formbuild: FormBuilder,
		private _snackBar: MatSnackBar, 
		private translate: TranslateService,
		private router: Router) {
	}
	displayedColumns: string[] = ['email','date','quantite','produit','action'];

	applyFilter(event: Event) {
		const filterValue = (event.target as HTMLInputElement).value;
		this.dataSource.filter = filterValue.trim().toLowerCase();
	}

	ngAfterViewInit() {
		this.listCommande();
	}
	ngOnInit() {
		}

	listCommande() {
		this.commandeService.getCommandeAll().subscribe(data => {
this.form = data
			if (this.form.statut) {
				//console.log('------------------------------');
				console.log(this.form);
				this.dataSource = new MatTableDataSource<Commande>(this.form.data);
				this.dataSource.paginator = this.paginator;
				this.dataSource.sort = this.sort;
			} else {
				window.alert(this.form.description);
			}
		})
	}


	openDialogAdd(): void {
		const dialog = this.dialogRef.open(AddCommandeComponent, {
			width: '700px',

		}).afterClosed().subscribe(result => {
			//location.reload();
			this.listCommande();
		});

	}
	openDialogUpdate(username) {
		console.log(username);
		const dialog1 = this.dialogRef.open(EditCommandeComponent, {
			width: '700px',
			data: username
		}).afterClosed().subscribe(result => {
			//location.reload();
			this.listCommande();
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
		this.translate.get('commande.confirm-suppression').subscribe((res: string) => {
			messageSuccess = res;
		});
		this.translate.get('commande.erreur-suppression').subscribe((res: string) => {
			messageError = res;
		});
		this.commandeService.deleteCommande(data).subscribe(data => {
				this.result=data
			if (this.result.statut) {
				this._snackBar.open(messageSuccess, 'Verification', {
					duration: 2000,
				});
			} else {
				this._snackBar.open(messageError, 'Verification', {
					duration: 2000,
				});
			}
			this.listCommande();
		});
	}
openDialogView(username) {
		console.log(username);
		const dialog1 = this.dialogRef.open(ViewCommandeComponent, {
			width: '700px',
			data: username
		}).afterClosed().subscribe(result => {
			//location.reload();
			this.listCommande();
		});
	}}