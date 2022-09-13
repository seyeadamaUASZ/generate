import { Component, OnInit, Inject,Input, ViewChild } from '@angular/core';
import { FormGroup, FormControl,Validators, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import {  MatSnackBarRef, SimpleSnackBar, MatDialogConfig, MatDialog, MatSnackBar, MatPaginator, MatSort, MatTableDataSource , MAT_DIALOG_DATA,MatDialogRef } from '@angular/material';
import { TranslateService } from '@ngx-translate/core';
import { NotificationService } from '../../shared/services/notification.service';
import { WorkflowService } from 'src/app/utilisateur/services/workflow.service';
import {Workflow} from 'src/app/utilisateur/models/workflow';
import { version } from 'punycode';


@Component({
  selector: 'app-process',
  templateUrl: './process.component.html',
  styleUrls: ['./process.component.scss']
})
export class ProcessComponent implements OnInit {
  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;
	@ViewChild(MatSort) sort: MatSort;
  form:any
name:any
  workflow:any={name:"",wkfProcess_id:""}; 
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
  
  constructor(private formbuild: FormBuilder, private router: Router, private workflowService: WorkflowService,
    private notification:NotificationService, private translate:TranslateService,
    public dialogRef: MatDialogRef<ProcessComponent>, @Inject(MAT_DIALOG_DATA) public element: any) {
      this.workflow.name = this.element.name;
      this.workflow.wkfConteneur = this.element.wkfConteneur;
      this.workflow.wkfProcess_id = this.element.wkfProcess_id;
     /*this.formGen = this.formbuild.group({
        code :['', Validators.required],
     ref :['', Validators.required],
      }); */
      this.formGen = new FormGroup({});   

     }
    
  ngOnInit() {
    this.initView();
   this.listOftask(this.username);  
  }

  initView() {  
    this.workflowService.listChampsProcess(this.element.wkfConteneur,this.element.wkfProcess_id).subscribe(data=>{
      this.form=data
      console.log(this.form) 
      this.formGen = this.toFormGroup(this.form);
     
  })
    
  } 

  listOftask(username:any) { 
    this.workflowService.listOftask(this.username).subscribe(data => { 
      this.donnee2 = data['task-summary']; 
      this.datasouceTask = new MatTableDataSource<any>(this.donnee2);
      this.datasouceTask.paginator = this.paginator;
      this.datasouceTask.sort = this.sort;
      console.log(data);
      
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
     this.workflowService.startProjet(this.workflow, (this.formGen.value)).subscribe(data=>{ 
          //alert("Process actif");         
         this.notification.info('Process actif');    
         // console.log(data)  
          this.listOftask(this.username);  
          this.closeDialog();
         
    })    
  }

  closeDialog() {
    this.dialogRef.close();
  }

}
