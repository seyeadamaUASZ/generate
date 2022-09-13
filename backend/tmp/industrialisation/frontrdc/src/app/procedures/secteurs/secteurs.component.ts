import { Component, OnInit,  Input, ViewChild } from '@angular/core';
import { MatSnackBarRef, SimpleSnackBar, MatDialogConfig, MatDialog, MatSnackBar, MatDialogRef, MatPaginator, MatSort, MatTableDataSource } from '@angular/material';
import { Router, ActivatedRoute } from '@angular/router';
import { Observable, from } from 'rxjs';
import {Workflow} from 'src/app/utilisateur/models/workflow';
import {Task} from 'src/app/utilisateur/models/task';
import {ProcessInfo} from 'src/app/utilisateur/models/processinfo';
import { TranslateService } from '@ngx-translate/core';
import {WorkflowService} from 'src/app/utilisateur/services/workflow.service';
import { ConfirmDialogModel, ConfirmDialogComponent } from 'src/app/sharedcomponent/confirm-dialog/confirm-dialog.component';
import {AddworkflowComponent } from 'src/app/workflow/addworkflow/addworkflow.component';
import {TaskComponent } from 'src/app/procedures/task/task.component';
import {ProcessComponent } from 'src/app/procedures/process/process.component';
import {BpmComponent } from 'src/app/workflow/bpm/bpm.component'; 
import { environment } from 'src/environments/environment';
import { User } from 'src/app/utilisateur/models/user';
import { UserService } from 'src/app/utilisateur/services/user.service';

@Component({
  selector: 'app-secteurs',
  templateUrl: './secteurs.component.html',
  styleUrls: ['./secteurs.component.scss']
})
export class SecteursComponent implements OnInit {
  displayedColumnsMenu: string[] = ['description'];
  displayedColumns: string[] = ['name', 'description', 'wkfEtat','Gerer', 'action'];
  displayedColumns1: string[] = ['taskid', 'taskname', 'taskactualowner', 'taskstatus', 'taskcreatedon', 'action'];
  
  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;
	@ViewChild(MatSort) sort: MatSort;
  dataSource: MatTableDataSource<Workflow>;
  datasouce1:MatTableDataSource<any>;
  datasouceTask:MatTableDataSource<any>;
  workflow:any;
  task:any;
  secteurparam:any;
  processinfo:any;
  breakpoint:any;
  donnee1:any
  processId:any
  donnee:any={containerId:'',processId:''}
  donnee2:any; 
  username:any=[localStorage.getItem('username')];
  jbpmdiagrame = environment.jbpm;
  descriptionWkrfl:any;
  constructor(    private router: Router,
    private route: ActivatedRoute,
    private dialog: MatDialog,
    private snackBar: MatSnackBar,private workflowService:WorkflowService,private usersService: UserService, private translate: TranslateService) { }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.secteurparam = params.get("id")
    })
    //this.listWorkflow();
    this.listWorkflowsbysectors(this.secteurparam);
    this.listContainer();
    this.listOftask(this.username);   
    //this.listProcessByContainer("evaluation_1.0.0-SNAPSHOT");

  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
 
  workflowParams(workflow){			
		this.workflowService.listWorkflows = workflow;	
		 this.router.navigate(['/procedures/execution'], { 
            state: {workflow:workflow} 
          });
	}
  /*listWorkflow() {
    this.workflowService.listWorkflows().subscribe(data => {
      if (data.statut) {
        this.workflow = data.data;
        //console.log('------------------------------');
        console.log(this.workflow);
        this.dataSource = new MatTableDataSource<Workflow>(data.data);
        //console.log('+++++++++++++++++++++++++++++++++++++++++++++++'+JSON.stringify(data.data));
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      } else {
        console.log(data.description);
      }

    })
  }*/

  listWorkflowsbysectors(secteurparam){
    this.workflowService.listWorkflowsbysector(this.secteurparam).subscribe(data => {
      if (data.statut) {
        this.workflow = data.data;
        //console.log('------------------------------');
        console.log(this.workflow);
        this.dataSource = new MatTableDataSource<Workflow>(data.data);
        //console.log('+++++++++++++++++++++++++++++++++++++++++++++++'+JSON.stringify(data.data));
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      } else { 
        console.log(data.description);
      }

    })
  }
   
  listOftask(username:any) { 
    this.workflowService.listOftask(this.username).subscribe(data => {  
      
      this.donnee2 = data['task-summary']; 
      
      //console.log('+++++++++++++get proccessId++++++++++++++++++++++++++++'+ JSON.stringify(this.elementbutton))
       
      console.log('+++++++++++++get All Task++++++++++++++++++++++++++++'+ JSON.stringify(this.donnee2))
    })
  }
  listChampsProcess(data:any,data1:any) {
    this.workflowService.listChampsProcess(data,data1).subscribe(data => {  
      console.log(data);
      
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
        console.log('+++++++++++++++++++++++++++++++++++++++++++++++++'+JSON.stringify(data.data));
        this.datasouce1.paginator = this.paginator;
        this.datasouce1.sort = this.sort;
        console.log('+++++++++++++++++++++++++++++++'+JSON.stringify(this.donnee1))
    })
  }
 /* listProcessByContainer(containerId) {
    this.workflowService.listContainerIdByProcess(containerId).subscribe(data => {
        this.processId =data.processes[0]['process-id'];
        console.log('+++++++++++++++++++++++++++++++'+this.processId)
    })
  }*/
 executeProject(workflow,varprocess:any){ 
      this.workflowService.startProjet(workflow,varprocess).subscribe(data=>{
        console.log(data)
        alert('workflow demarer');
        this.listWorkflowsbysectors(this.secteurparam);
        this.listOftask(this.username);
      })
 }

 modeliserWorkflow(workflow){
  this.workflowService.modeliser(workflow).subscribe(data=>{
    console.log(data);
    alert('Workflow compiler');
    this.listWorkflowsbysectors(this.secteurparam);
  })

 }
  openDialogDeleteWorkflow(workflow) {
    const message = "Alert.confirm-action";
    const dialogData = new ConfirmDialogModel("workflow suppression", message);
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
    maxWidth: "400px",
    data: dialogData
    });
    dialogRef.afterClosed().subscribe(dialogResult => {
    if(dialogResult === true){
      this.delete(workflow);
    }
    });
  }
  
  openDialogAddProcess(element): void {
    const dialog2 = this.dialog.open(ProcessComponent , {
      width: '700px',  
      data : element
    }) 
  }
  openDialogAddTask(element): void {
    const dialog2 = this.dialog.open(TaskComponent , {
      width: '700px', 
      data : element
    }) 
  }

  delete(workflow) {
    let messageSuccess;
    let messageError;
    this.translate.get('workflow.confirm-suppression').subscribe((res: string) => {
      messageSuccess = res;
    });
    this.translate.get('workflow.erreur-suppression').subscribe((res: string) => {
      messageError = res;
    });
    this.workflowService.deleteWorkflow(workflow).subscribe(data => {
      if (data.statut) {
        this.snackBar.open(messageSuccess, 'Verification', {
          duration: 2000,
        });
      } else {
        this.snackBar.open(messageError, 'Verification', {
          duration: 2000,
        });
      }
      this.listWorkflowsbysectors(this.secteurparam);
    });
  }
  statut(workflow) {

  }
  onResize(event) {
    this.breakpoint = (event.target.innerWidth <= 400) ? 1 : 5;
  }
  
    __cardClick(emp) {
      console.log(emp);
      this.router.navigate(['workflow', emp.id]);
    }
    openDialogAddWorkflow(): void {
      const dialog1 = this.dialog.open(AddworkflowComponent , {
        width: '700px',
      }).afterClosed().subscribe(result => {
        this.listWorkflowsbysectors(this.secteurparam);
  
      });
    }
     
        openSnackBar(message: string, action: string): MatSnackBarRef < SimpleSnackBar > {
      return this.snackBar.open(message, action, {
        duration: 2000,
      });
    }

    goToLink(url: string){ 
      window.open(url, "_blank");
  }
}

 