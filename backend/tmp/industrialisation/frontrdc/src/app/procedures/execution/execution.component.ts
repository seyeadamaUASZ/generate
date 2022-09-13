import { Component, OnInit,  Input, ViewChild, Inject } from '@angular/core';
import { MatSnackBarRef, SimpleSnackBar, MatDialogConfig, MatDialog, MatSnackBar, MatDialogRef, MatPaginator, MatSort, MatTableDataSource, MAT_DIALOG_DATA } from '@angular/material'; 
import { Router, Params, ActivatedRoute } from '@angular/router';
import { Observable, from } from 'rxjs';
import {Workflow} from 'src/app/utilisateur/models/workflow';
import {Task} from 'src/app/utilisateur/models/task';
import {ProcessInfo} from 'src/app/utilisateur/models/processinfo';
import { TranslateService } from '@ngx-translate/core';
import { NotificationService } from '../../shared/services/notification.service';
import {WorkflowService} from 'src/app/utilisateur/services/workflow.service';
import { ConfirmDialogModel, ConfirmDialogComponent } from 'src/app/sharedcomponent/confirm-dialog/confirm-dialog.component';
import {AddworkflowComponent } from 'src/app/workflow/addworkflow/addworkflow.component';
import {TaskComponent } from 'src/app/procedures/task/task.component';
import {ProcessComponent } from 'src/app/procedures/process/process.component';
import {BpmComponent } from 'src/app/workflow/bpm/bpm.component';
import { ViewworkflowComponent} from 'src/app/workflow/viewworkflow/viewworkflow.component';
import { EditworkflowComponent} from 'src/app/workflow/editworkflow/editworkflow.component';
import { environment } from 'src/environments/environment';
import { User } from 'src/app/utilisateur/models/user';
import { UserService } from 'src/app/utilisateur/services/user.service';
@Component({
  selector: 'app-execution',
  templateUrl: './execution.component.html',
  styleUrls: ['./execution.component.scss']
})
export class ExecutionComponent implements OnInit {
  displayedColumnsMenu: string[] = ['description'];
  displayedColumns: string[] = ['name', 'description', 'wkfEtat','Gerer', 'action'];
  displayedColumns1: string[] = ['taskid', 'taskname', 'taskactualowner', 'taskstatus', 'taskcreatedon','gerer','controle', 'action'];
  
  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;
	@ViewChild(MatSort) sort: MatSort;
  dataSource: MatTableDataSource<Workflow>;
  datasouce1:MatTableDataSource<any>;
  datasouceTask:MatTableDataSource<Task>;
  workflow:any;
  
  task:any;
  processinfo:any;
  breakpoint:any;
  donnee1:any
  processId:any
  donnee:any={containerId:'',processId:''}
  workflowtask:any={wkfConteneur:"",wkfProcess_id:"",username:""}; 
  donnee2:any; 
  donnee3:any;
  alltaks:any;
  nomdemarer:any=0;
  complete:any=0;
  encoucours:any=0;
  terminer:any=0;
  donneetask:any; 
  username:any=[localStorage.getItem('username')];
  jbpmdiagrame = environment.jbpm;
  descriptionWkrfl:any;
  container:any; 
  dtask:any;
  datafortask:any;
  fetchprocessId:any
  fetchprocessInstId:any
  elementbutton:any
  elementbpm:any
  workflowname:any
  nomwrkfl:any
  nomcallto: any
  nomwdgtlab : any
  displayColumns: string[];
  form:any
  taskoutput:any
  constructor(    private router: Router,
    private route: ActivatedRoute,
    private dialog: MatDialog,
    private notification:NotificationService,
    private snackBar: MatSnackBar,private workflowService:WorkflowService,private usersService: UserService, private translate: TranslateService) { 
      
    }

  ngOnInit() {
    this.listWorkflow();
    this.listContainer();
    this.listOftask(this.username);   
    this.route.paramMap.subscribe(params => {
      this.container = params.get('idname');
      console.log('++++++++++++++++++++++++++++++++++++++++'+this.container);

    });  
    
   this.nomDuWorkflows(this.container);
   
   //this.listOfAlltask();
  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  nomDuWorkflows(containerId:any){
    this.workflowService.nomDuWorkflows(this.container).subscribe(data => {
      if (data.statut) {
        this.workflowname = data.data;
        this.nomwrkfl = this.workflowname['name']
        this.nomcallto = this.workflowname['wkfCalltoaction']
        this.nomwdgtlab  = this.workflowname['wkfLabelwdgt']
         console.log('++++++++++++++Nom du workflow++++++++++++++++++++++++++'+JSON.stringify(data.data));
        //console.log('------------------------------'); 
        
         
      } else {        
         this.notification.warn(data.description);            
      }

    })
  }
  listWorkflow() {
    this.workflowService.listWorkflows().subscribe(data => {
      if (data.statut) {
        this.workflow = data.data;
        //console.log('------------------------------');
        console.log(this.workflow);
        this.dataSource = new MatTableDataSource<Workflow>(data.data);
        //console.log(JSON.stringify(data.data));
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      } else {        
         this.notification.warn(data.description);             
      }

    })
  }
   
  listOftask(username:any) { 
    this.workflowService.listOftask(this.username).subscribe(data => {  
      
      this.donnee2 = data['task-summary']; 
      this.datafortask = this.filterData(this.container);
      //console.log('+++++++++++++filtered json++++++++++++++++++++++++++++'+JSON.stringify(this.datafortask))
      this.datasouceTask = new MatTableDataSource<Task>(this.datafortask);
      this.datasouceTask.paginator = this.paginator;
      this.datasouceTask.sort = this.sort;  
      this.fetchprocessId = this.datafortask[0]['task-proc-def-id']
      this.fetchprocessInstId = this.datafortask[0]['task-proc-inst-id']
      
      this.elementbutton = {
        name: this.nomwrkfl,
        wkfConteneur: this.container,
        wkfProcess_id: this.fetchprocessId,
        wkfProcess_inst_id: this.fetchprocessInstId
      };   
      this.elementbpm= { 
        wkfConteneur: this.container, 
        wkfProcess_inst_id: this.fetchprocessInstId
      }; 
      //console.log('+++++++++++++get proccessId++++++++++++++++++++++++++++'+ JSON.stringify(this.elementbutton))
      
      for (var i = 0; i < this.donnee2.length; i++){ 
        if(this.donnee2[i]['task-container-id']==this.container){
          if(this.donnee2[i]['task-status']=="Completed")this.complete++; 
          if(this.donnee2[i]['task-status']=="Reserved")this.nomdemarer++; 
          if(this.donnee2[i]['task-status']=="InProgress") this.encoucours++;
        }
       
    }
      console.log('+++++++++++++get All Task++++++++++++++++++++++++++++'+ JSON.stringify(this.donnee2))
      this.listOfOutputTask(this.fetchprocessInstId);
    })
  }

   
  listOfOutputTask(data:any) {
    this.workflowService.listOfOutputTask(this.fetchprocessInstId).subscribe(data => {      
      this.taskoutput = data['variable-instance'] ;
     console.log('++++++++++++++precedent output+++++++++++++++++++++++'+JSON.stringify(this.taskoutput));
      
    })
  }
  /*listOfAlltask() { 
    this.workflowService.listOfAlltask().subscribe(data => {  
      
      this.alltaks = data['task-summary']; 
      for (var i = 0; i < this.alltaks.length; i++){
        var objTask = this.alltaks[i]['task-actual-owner'];
        console.log('+++++++++++++  ++++++++++++++++++++++++++++'+ objTask)
        for (var key in objTask){
            var attrName = key;
            var attrValue = objTask['task-status'];
            console.log('+++++++++++++  ++++++++++++++++++++++++++++'+ attrValue)
        }
    }
      console.log('+++++++++++++get All Task++++++++++++++++++++++++++++'+ JSON.stringify(this.alltaks))
    })
  }*/
  

   filterData(locationName) {
    return this.donnee2.filter(object => {
      return object['task-container-id'] == locationName;
    });
  }
  listChampsProcess(data:any,data1:any) {
    this.workflowService.listChampsProcess(data,data1).subscribe(data => {  
      console.log('+++++++++++++get champs task++++++++++++++++++++++++++++'+JSON.stringify(data));
      
    })
  }
  listChampsTask(data:any,data1:any) {
    this.workflowService.listChampsTask(data,data1).subscribe(data => {  
      console.log(data);
      
    })
  }
  listContainer() {
    this.workflowService.listOfContainer().subscribe(data => {
        this.donnee1 = data.result['kie-containers']['kie-container'];
        this.datasouce1 = new MatTableDataSource<any>(this.donnee1);
        //console.log(JSON.stringify(data.data));
        this.datasouce1.paginator = this.paginator;
        this.datasouce1.sort = this.sort;
        //console.log('+++++++++++++++++++++++++++++++'+this.donnee1)
    })
  }
  listProcessByContainer(containerId) {
    this.workflowService.listContainerIdByProcess(containerId).subscribe(data => {
        this.processId =data.processes[0]['process-id'];
        //console.log('+++++++++++++++++++++++++++++++'+this.processId)
    })
  }
  reclamerOneTask(containerId, TaskId,username){ 
       
   //alert(JSON.stringify(this.workflowtask))
  this.workflowService.reclamerTask(containerId,TaskId,username).subscribe(res=>{  
      this.listOftask(this.username);
})    
}
 executeProject(elementbutton,varprocess:any){ 
      this.workflowService.startProjet(elementbutton,varprocess).subscribe(data=>{
        //console.log(data)
       
        this.notification.info('Workflow démarré');
   
        this.listWorkflow();
        this.listOftask(this.username);
      })
 }
 
   
 openDialogBpm(containerId, procIntId): void {
    this.elementbpm= { 
      wkfConteneur: containerId, 
      wkfProcess_inst_id: procIntId
    }; 
  const dialog2 = this.dialog.open(BpmComponent , {
    width: '700px',  
    data :  this.elementbpm
  }) 
}
  openDialogAddProcess(element): void {
    const dialog2 = this.dialog.open(ProcessComponent , {
      width: '700px',  
      data : element
    }).afterClosed().subscribe(result => {
      this.listOftask(this.username);
    });  
  }
  openDialogAddTask(element): void {
    const dialog2 = this.dialog.open(TaskComponent , {
      width: '700px', 
      data : element
    }).afterClosed().subscribe(result => {
      this.listOftask(this.username);
    }); 
  }

   

    goToLink(url: string){ 
      window.open(url, "_blank");
  }
}
