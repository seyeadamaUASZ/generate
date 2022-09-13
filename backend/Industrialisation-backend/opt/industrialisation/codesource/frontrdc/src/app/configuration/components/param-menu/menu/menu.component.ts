import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { MatPaginator, MatTableDataSource, MatSort, MatDialog } from '@angular/material';
import { TranslateService } from '@ngx-translate/core';
import {FlatTreeControl} from '@angular/cdk/tree';
import {MatTreeFlatDataSource, MatTreeFlattener} from '@angular/material/tree';


import { ViewMenuComponent } from '../view-menu/view-menu.component';
import { EditMenuComponent } from '../edit-menu/edit-menu.component';
import { AddMenuComponent } from '../add-menu/add-menu.component';
import { Menu } from 'src/app/parametrage/models/menu';
import { MenuService } from 'src/app/parametrage/services/menu.service';


/********************************************************* */
/** Flat node with expandable and level information */
interface MenuFlatNode {
  expandable: boolean;
  hasActions: boolean;
  name: string;
  level: number;
  menu:Menu;
}

/********************************************************* */

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {

/********************************************************* */
 
  message:string;
  private _transformermenu = (node: Menu, level: number) => {
    return {
      expandable: !!node.sousMenus && node.sousMenus.length > 0,
      hasActions: !!node.actions && node.actions.length > 0,
      name: node.menNom,
      menu: node,
      level: level,
    };
  }

  treeControl = new FlatTreeControl<MenuFlatNode>(
      node => node.level, node => node.expandable);
 

  treeFlattenerMenu = new MatTreeFlattener(
      this._transformermenu, node => node.level, node => node.expandable, node => node.sousMenus);

  dataSourceMenu = new MatTreeFlatDataSource(this.treeControl, this.treeFlattenerMenu);
   hasChild = (_: number, node: MenuFlatNode) => node.expandable;
   hasActions = (_: number, node: MenuFlatNode) => node.hasActions;
  /********************************************************* */

  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;
	@ViewChild(MatSort, { static: true }) sort: MatSort;
  menuDataSource: MatTableDataSource<Menu>;
  displayedColumnsRoles: string[] = ['menNom', 'menPath', 'menIcone', 'action'];
   
  constructor(private readonly translate: TranslateService, private menuService: MenuService,
     private dialogRef: MatDialog,private router: Router, private route: ActivatedRoute) {
   }

  ngOnInit() {
   
  }

  ngAfterViewInit() {	
		this.listMenus();
	}    

  listMenus() {		
		this.menuService.listeMenuHierarchique().subscribe(data => {			
			if (data.statut) {
				//this.user = data.data;								
				console.log(data.data);
				//this.menuDataSource = new MatTableDataSource<Menu>(data.data);	
        this.dataSourceMenu.data =	data.data;		
        //this.paginator._intl.itemsPerPageLabel = this.translate.instant('Nombre de ligne');
        //alert(this.paginator._intl.itemsPerPageLabel);
				//this.menuDataSource.paginator = this.paginator;
				//this.menuDataSource.sort = this.sort;
			} else {
				window.alert("------------Description----------"+data.description);
			}
		})
	}

  openDialogAdd(menu): void {
		const dialog = this.dialogRef.open(AddMenuComponent, {
			width: '700px',
			data: menu
		}).afterClosed().subscribe(result => {
    	this.listMenus();				
		});	  		
	}

  openDialogEdit(menu): void {
		const dialog = this.dialogRef.open(EditMenuComponent, {
			width: '700px',
			data: menu
		}).afterClosed().subscribe(result => {
    	this.listMenus();				
		});	  		
	}

	openDialogView(menu): void {    
		const dialog1 = this.dialogRef.open(ViewMenuComponent, {
			width: '700px',
			data: menu
		}).afterClosed().subscribe(result => {
			//location.reload();
			this.listMenus();
		});
	}

	confirmationSuppression(profile): void {
    const message = "Alert.confirm-action";
   /* const dialogData = new ConfirmDialogModel("role.alert-delete", message);
    const dialogRef = this.dialogRef.open(ConfirmDialogComponent, {
      maxWidth: "400px",
      data: dialogData
    });
    dialogRef.afterClosed().subscribe(dialogResult => {
	if(dialogResult === true){
		 this.deleteRole(profile);
   
      //this.result = dialogResult;
    });   
     }*/ 
  }

  addChildMenu(node:MenuFlatNode){
   /* let menAdd = new Menu();
    menAdd.menIdParent = node.menu.menIdParent;*/
    this.openDialogAdd(node);
  }

  addAction(node:MenuFlatNode){   
     this.menuService.changemenu(node.menu);
     this.router.navigate(['/parametrage/action']);
  }

}
