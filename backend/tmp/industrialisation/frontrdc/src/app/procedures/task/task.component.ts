import { Component, OnInit, Inject,Input, ViewChild  } from '@angular/core';
import {FormGroup, FormControl,Validators, FormBuilder  } from '@angular/forms';
import { Router } from '@angular/router';
import {  MatSnackBarRef, SimpleSnackBar, MatDialogConfig, MatDialog, MatSnackBar, MatPaginator, MatSort, MatTableDataSource , MAT_DIALOG_DATA,MatDialogRef} from '@angular/material';
import { TranslateService } from '@ngx-translate/core';
import { NotificationService } from '../../shared/services/notification.service';
import { WorkflowService } from 'src/app/utilisateur/services/workflow.service';
import { ChampsTask} from 'src/app/utilisateur/models/champstask';
import { Task} from 'src/app/utilisateur/models/task';
import {Workflow} from 'src/app/utilisateur/models/workflow';
import { version } from 'punycode';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.scss']
})
export class TaskComponent implements OnInit {
  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;
	@ViewChild(MatSort) sort: MatSort;
  form:any
name:any
  workflow:any={wkfConteneur:"",wkfProcess_id:""}; 
  formGen:FormGroup; 
  task:any;
  processinfo:any;
  breakpoint:any;
  donnee1:any
  processId:any
  donnee:any={containerId:'',processId:''}
  donnee2:any; 
  username:any=[localStorage.getItem('username')];
  datasouceTask:MatTableDataSource<any>;
  taskname:any
  taskoutput:any
  constructor(private formbuild: FormBuilder, private router: Router, private workflowService: WorkflowService,
      private notification:NotificationService, private translate:TranslateService,
      public dialogRef: MatDialogRef<TaskComponent>, @Inject(MAT_DIALOG_DATA) public element: any) { 
      this.workflow.wkfConteneur = this.element['task-container-id'];
      this.workflow.wkfTaskId = this.element['task-id'];
      this.workflow.taskactualowner = this.element['task-actual-owner']
      this.formGen = new FormGroup({});  
      this.taskname = this.element['task-name'];
    }


  ngOnInit() {
    this.initView();
    this.listOftask(this.username);  
  }
initView() {   
  this.workflowService.listChampsTask(this.element['task-container-id'],this.element['task-id']).subscribe(data=>{
    this.form=data
    //console.log(this.form) 
    this.formGen = this.toFormGroup(this.form);
   
  })
  this.workflowService.listOfOutputTask(this.element['task-proc-inst-id']).subscribe(data => { 
    this.taskoutput = data['variable-instance'];  
    //console.log('++++++++++++++precedent output+++++++++++++++++++++++'+JSON.stringify(this.taskoutput));
    
  })
  
}

listOftask(username:any) { 
  this.workflowService.listOftask(this.username).subscribe(data => { 
    this.donnee2 = data['task-summary']; 
    this.datasouceTask = new MatTableDataSource<any>(this.donnee2);
    this.datasouceTask.paginator = this.paginator;
    this.datasouceTask.sort = this.sort;
    //console.log(data);
    
  })
}
 

toFormGroup(form) {
  let group: any = {}; 
  for (let i = 0; i < this.form.length; i++) {   
    group[this.form[i]["name"]] = new FormControl('', Validators.required);
  }         
  return new FormGroup(group);
}



onSubmit(){
   
  let varprocess = JSON.stringify(this.formGen.value);  
   this.workflowService.startTask(this.workflow, (this.formGen.value)).subscribe(data=>{ 
        //alert("Task Terminer ");        
         this.notification.warn('Task terminé');     
        //console.log(data)   
        this.listOftask(this.username); 
        this.closeDialog();
  })   
}

closeDialog() {
  this.dialogRef.close();
}

}
