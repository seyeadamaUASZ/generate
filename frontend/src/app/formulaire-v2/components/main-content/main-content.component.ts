import { AfterViewInit, ChangeDetectorRef, Component, OnInit, QueryList, ViewChild, ViewChildren } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatDialog, MatPaginator, MatSort, MatTableDataSource } from '@angular/material';
import { ActivatedRoute, Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { merge, Observable } from 'rxjs';
import { NotificationService } from 'src/app/shared/services/notification.service';
import { ConfirmDialogComponent, ConfirmDialogModel } from 'src/app/sharedcomponent/confirm-dialog/confirm-dialog.component';
import { Formulairev2Service } from '../../services/formulairev2.service';
import { DetailComponent } from '../detail/detail.component';
import { EditFormComponent } from '../edit-form/edit-form.component';

@Component({
  selector: 'app-main-content',
  templateUrl: './main-content.component.html',
  styleUrls: ['./main-content.component.scss']
})
export class MainContentComponent implements OnInit, AfterViewInit {
  dataSourceFormulaireNotGeneres: MatTableDataSource<any>;
  displayedColumnsFormulaireNotGeneres = ['frmNom','frmDescription','action_s'];
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  selectedColumns: string[] = ['frmNom', 'frmDescription', 'action_s'];

  saveCols(){
    localStorage.setItem("formulaireCols1",this.selectedColumns.toString());
  }
  applyFilterNotGeneres(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSourceFormulaireNotGeneres.filter = filterValue.trim().toLowerCase();
  }

  

  constructor(private router: Router,
    private route: ActivatedRoute,
    private dialog: MatDialog,
    private notification: NotificationService, private translate: TranslateService,
    private formulaireV2Service: Formulairev2Service) { }
  
    ngAfterViewInit(): void {
    this.listeFormulaireNonGenerer();

  }

  ngOnInit() {
    let cols = localStorage.getItem("formulaireCols1");
    if(cols != undefined){
      this.selectedColumns = cols.split(",");
    }
    
  }

  listeFormulaireNonGenerer(){
    this.formulaireV2Service.getList().subscribe((data:any)=>{
      this.dataSourceFormulaireNotGeneres = new MatTableDataSource(data.data);
      this.dataSourceFormulaireNotGeneres.paginator = this.paginator;
      this.dataSourceFormulaireNotGeneres.sort = this.sort;

    })
  }
  openDialogAddForm(): void {
    const dialog1 = this.dialog.open(EditFormComponent, {
      disableClose: true,
      // width: '700px',

    }).afterClosed().subscribe(result => {
			if(result.status){
        this.listeFormulaireNonGenerer();
      }
		});

  }
  openDialogEditForm(element){
    const dialog1 = this.dialog.open(EditFormComponent, {
      disableClose: true,
      data: element
      // width: '700px',

    }).afterClosed().subscribe(result => {
			if(result.status){
        this.listeFormulaireNonGenerer();
      }
		});
  }

  openDialogDetail(element){
    const dialog1 = this.dialog.open(DetailComponent, {
      disableClose: true,
      data: element,
      width: 'fit-content'

    }).afterClosed().subscribe(result => {
			
		});
  }

  supprimerFormulaire(element){

    let alertSupp;
    this.translate.get('configuration.confirm-suppression').subscribe((res: string) => {
      alertSupp = res;
    });
    const message = "Alert.confirm-action";
    const dialogData = new ConfirmDialogModel("configuration.alert-suppression", message);
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      disableClose: true,
      maxWidth: "400px",
      data: dialogData
    });
    dialogRef.afterClosed().subscribe(dialogResult => {
      if (dialogResult === true) {
        this.formulaireV2Service.deleteForm(element).subscribe((data:any)=>{
          if(data.statut){
            this.translate.get("notif.confirmSuppression").subscribe((res: string)=>{
              this.notification.success(res);
            });
            this.listeFormulaireNonGenerer();
          }
        })
      }
    });

  }

  dupliquer(form){
    let formCopy = Object.assign({},form);
    formCopy.frmId = null;
    formCopy.frmNom+="_copy_"+Date.now();
    formCopy.frmDescription+=" copy";
    formCopy.steps.forEach(step => {
      step.id = null;
      step.champs.forEach(chp => {
        chp.chpId = null;
        chp.values.forEach(val => {
          val.id = null;
        });
      });
    });
    this.formulaireV2Service.saveForm(formCopy).subscribe((data: any) => {
      if(data.statut){
        this.translate.get('formulaire.saveSuccessNotif').subscribe((resp:any)=>{
          this.notification.success(resp);
          this.listeFormulaireNonGenerer();
        })
      }
    })
    
  }
}
