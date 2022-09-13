import { Component, OnInit, Input, Inject, ViewChild, QueryList } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MatPaginator, MatSort, MatTableDataSource, MAT_DIALOG_DATA } from '@angular/material';
import { TranslateService } from '@ngx-translate/core';
import { NotificationService } from '../../../shared/services/notification.service';  
import { UserService } from 'src/app/utilisateur/services/user.service';
import * as XLSX from 'xlsx';
import { Workflowform2 } from '../../models/workflowform2';
import { WorkflowService } from '../../services/workflow.service';
import { Addformulairev2Component } from '../addformulairev2/addformulairev2.component';
import { EditformulaireComponent } from '../editformulaire/editformulaire.component';
import { ConfirmDialogComponent, ConfirmDialogModel } from 'src/app/sharedcomponent/confirm-dialog/confirm-dialog.component';
import { Task } from '../../models/task';
import { AddTransitionComponent } from '../add-transition/add-transition.component';
import { EditworkflowtaskComponent } from '../editworkflowtask/editworkflowtask.component';
import { AddstatustaskComponent } from '../addstatustask/addstatustask.component';
import { VoirTransitionComponent } from '../voir-transition/voir-transition.component';
import { AddtaskComponent } from '../addtask/addtask.component';

@Component({
  selector: 'app-configurer-workflow',
  templateUrl: './configurer-workflow.component.html',
  styleUrls: ['./configurer-workflow.component.scss']
})
export class ConfigurerWorkflowComponent implements OnInit {
 
 
  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  wrkform:any
  wrkformGenere:any
  idwrkf:any

  containerId:any

  elementform:any
  loading:boolean
  filelink:any
  typedoc:any
  idform:any
  profiles: any[];
  status: any[];
  wrktask:any 
  dataSourceFrmGenerer: MatTableDataSource<Workflowform2>;
  dataSourceFrmNoGenerer: MatTableDataSource<Workflowform2>;
  displayedColumnsFrm: string[] = ['name', 'wfcPrimaire','action'];
  dataSourceWrkfTask: MatTableDataSource<Task>;  
  displayedColumnsWrkfTask: string[] = ['tskId', 'tskName', 'poOwner.proLibelle','action'];
  addWrkForm = this.formBuilder.group({
    nomform: ['', [Validators.required,Validators.pattern('^[a-zA-Z0-9_]*$')]],  
    idform: ['', Validators.required],  
  });
  addTskForm = this.formBuilder.group({
    ownername:  ['', Validators.required],  
    taskname:['', Validators.required],   
    tasknamesuivant:[''],    
    taskdescription: ['', Validators.required],  
    statusId: ['', Validators.required],



  });
  constructor( 
    private route: ActivatedRoute,private router: Router, private formBuilder: FormBuilder,
    private translate: TranslateService,
    private notification: NotificationService,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private dialog: MatDialog, 
    private userService: UserService,private workflowService:WorkflowService) {
  }

   

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.idwrkf = params.get("idwrkf") 
    })
    this.listFormulaireworkflow2(this.idwrkf);
    this.listeFormulaireNonGenerer()
    this.extractProfilId() 
    this.extractStatusId()
    this.listetaskparid(this.idwrkf)
  }

  extractStatusId(){
    this.workflowService.taskStatusAll().subscribe(res => {
      this.status =  res.data;
      console.log("+++++++++++++"+JSON.stringify(this.status));
  
  
  });
  }
  extractProfilId(){
    this.userService.listprofil().subscribe(res => {
      this.profiles = res.data;
   
  });
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
  listeFormulaireNonGenerer(){
    this.workflowService.getList().subscribe((data:any)=>{
      this.idform =  data.data;
      console.log("++++++++++++"+JSON.stringify(this.idform))
       
    })
  }

  listetaskparid(idwrkf:any) {
    this.workflowService.listetaskparid(this.idwrkf).subscribe(data => {  

     
      if(data.statut){
        this.wrktask = data;

        console.log(JSON.stringify(this.wrktask))
          this.dataSourceWrkfTask = new MatTableDataSource<Task>(data.data.reverse());
          this.dataSourceWrkfTask.paginator = this.paginator;
          this.dataSourceWrkfTask.sort = this.sort;
  
      }
      
    })
  }
  openDialogAddTask(): void {
    this.elementform= {  
      wkfid: this.idwrkf
    }; 
    const dialog1 = this.dialog.open(AddtaskComponent , {
      disableClose: true,
      data : this.elementform,
      width: '700px',
    }).afterClosed().subscribe(result => {
      this.listetaskparid(this.idwrkf)

    });
  }

  openDialogAddform(): void {
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
   
 modifierFormulaire(element){
  const dialog1 = this.dialog.open(EditformulaireComponent, {
    disableClose: true,
    data : element,
    width: '700px',
  }).afterClosed().subscribe(result => {
    this.listFormulaireworkflow2(this.idwrkf);

  });
}

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

openDialogDeleteTaskConfig(taskid) {
  const message = "Alert.confirm-action";
  const dialogData = new ConfirmDialogModel("Supprimer ce workflow", message);
  const dialogRef = this.dialog.open(ConfirmDialogComponent, {
    disableClose: true,

  maxWidth: "400px",
  data: dialogData
  });
  dialogRef.afterClosed().subscribe(dialogResult => {
  if(dialogResult === true){
    this.deleteTask(taskid);
  }
  });
}
 
deleteTask(taskid) {

  this.workflowService.deleteTaskConf(taskid).subscribe(data => {
    if (data.statut) {
      this.translate.get('tache.confirm-suppression').subscribe((res: string) => {           
        this.notification.success(res);
      });         
       
    } else {
      this.translate.get('tache.erreur-suppression').subscribe((res: string) => {           
        this.notification.error(res);
      });   
      
    }
    this.listetaskparid(this.idwrkf)
  });
}

AddTransition(element){
  console.log(element);
  this.elementform= {  
    wkfid: this.idwrkf,
    idwrktsk: element.tskId,
    tskName:element.tskName
  }; 
  const dialog1 = this.dialog.open(AddTransitionComponent , {
    disableClose: true,
    data :  this.elementform,
    width: '700px',
  }).afterClosed().subscribe(result => {
  });
}

voirTransition(element){
  console.log(element);
  this.elementform= {  
    wkfid: this.idwrkf,
    idwrktsk: element.tskId,
    tskName:element.tskName
  }; 
  const dialog1 = this.dialog.open(VoirTransitionComponent , {
    disableClose: true,
    data :  this.elementform,
    width: '700px',
  }).afterClosed().subscribe(result => {
  });
}

openDialogUpdateTask(element): void {
 /* this.elementform= { 
    wkfid: this.idwrkf,
    idtsk: element.tskId
  }; */ 
  const dialog1 = this.dialog.open(EditworkflowtaskComponent , {
    disableClose: true,
    data : element,
    width: '700px',
  }).afterClosed().subscribe(result => {
    this.listetaskparid(this.idwrkf)

  });
}

openDialogAddstatus(): void {
   
  const dialog1 = this.dialog.open(AddstatustaskComponent , {
    disableClose: true, 
    width: '700px',
  }).afterClosed().subscribe(result => { 

  });
}

  onSubmitliasonform(){ 
    
    if (this.addWrkForm.valid) {
      alert("test")
      let formData = new FormData(); 
      formData.append("workflowform",JSON.stringify(this.addWrkForm.value));  
      formData.append("idwrkf",this.idwrkf); 
      
     this.workflowService.chargerjbpmform2(formData).subscribe(data=>{
          this.notification.success('formulaire chargé');
          this.listFormulaireworkflow2(this.idwrkf);
         });
        }
  }
  onSubmitAddtask(){

    let formData = new FormData(); 
    formData.append("taskgenform",JSON.stringify(this.addTskForm.value));  
    formData.append("idwrkf",this.idwrkf); 
   this.workflowService.createtask(formData).subscribe(data=>{
        this.notification.success('Document chargé'); 
       }); 
  }
  
 
}

