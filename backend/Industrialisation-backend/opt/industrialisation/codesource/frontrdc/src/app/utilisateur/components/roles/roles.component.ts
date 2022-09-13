import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { Validators, FormBuilder } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { MatPaginator, MatTableDataSource, MatSort, MatDialog } from '@angular/material';
import { ConfirmDialogModel, ConfirmDialogComponent } from '../../../sharedcomponent/confirm-dialog/confirm-dialog.component';
import { RoleService } from '../../services/role.service';
import { Profile } from '../../models/profile';
import { AddroleComponent } from './addrole/addrole.component';
import { EditroleComponent } from './editrole/editrole.component';
import { ViewroleComponent } from './viewrole/viewrole.component';
import { NotificationService } from '../../../shared/services/notification.service';

@Component({
  selector: 'app-roles',
  templateUrl: './roles.component.html',
  styleUrls: ['./roles.component.scss']
})
export class RolesComponent implements OnInit {
    @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;
	  @ViewChild(MatSort, { static: true }) sort: MatSort;
  	roleDataSource: MatTableDataSource<Profile>;
    displayedColumnsRoles: string[] = ['proLibelle', 'proDescription', 'action'];
  constructor(private router: Router, private route: ActivatedRoute, private readonly translate: TranslateService, private roleService: RoleService, 
  private notification: NotificationService, private dialogRef: MatDialog) { }

  ngOnInit() {
  }

  applyFilterRoles(event: Event) {
		const filterValue = (event.target as HTMLInputElement).value;
		this.roleDataSource.filter = filterValue.trim().toLowerCase();
	}
	ngAfterViewInit() {	
		this.listRoles();
		
	}  

  listRoles() {		
		this.roleService.listprofils().subscribe(data => {			
			if (data.statut) {
				//this.user = data.data;								
				//console.log(this.user);
				this.roleDataSource = new MatTableDataSource<Profile>(data.data.reverse());				
        //this.paginator._intl.itemsPerPageLabel = this.translate.instant('Nombre de ligne');
        //alert(this.paginator._intl.itemsPerPageLabel);
				this.roleDataSource.paginator = this.paginator;
				this.roleDataSource.sort = this.sort;
			} else {
				window.alert(data.description);
			}

		})
	}

	openDialogAdd(): void {
		const dialog = this.dialogRef.open(AddroleComponent, {
			disableClose: true,

			width: '600px',
		}).afterClosed().subscribe(result => {
			//location.reload();
			this.listRoles();
    });
	}
	
	openDialogEdit(role): void {
		const dialog = this.dialogRef.open(EditroleComponent, {
			disableClose: true,

			width: '700px',
			data: role
		}).afterClosed().subscribe(result => {
    	this.listRoles();				
		});
	
		
	}

	openDialogView(role): void {
		const dialog = this.dialogRef.open(ViewroleComponent, {
			disableClose: true,
			width: '700px',
			data: role
		});
	}


// 	confirmationSuppression(profile): void {
//     const message = "Alert.confirm-action";
//     const dialogData = new ConfirmDialogModel("role.alert-delete", message);
//     const dialogRef = this.dialogRef.open(ConfirmDialogComponent, {
// 		disableClose: true,

//       maxWidth: "400px",
//       data: dialogData
//     });
//     dialogRef.afterClosed().subscribe(dialogResult => {
// 	if(dialogResult === true){
// 		 this.deleteRole(profile);
// 	}
//     });    
//   }

  deleteRole(profile){
	  this.roleService.deleteProfile(profile).subscribe(data => {
      if (data.statut) {
				 this.translate.get('role.success-delete').subscribe((res: string) => {
          this.notification.success(res);         
          });
        this.listRoles();
      }
    }, error => {
      alert('Formulaire invalide');
    });
  }

	allocaterights(profile){			
		this.roleService.profilId = profile.proId;	
		 this.router.navigate(['/utilisateur/accesrights'], { 
            state: {profil:profile} 
          });
	}

}
