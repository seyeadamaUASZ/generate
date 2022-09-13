import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { NotificationService } from 'src/app/shared/services/notification.service';
import { WorkflowService } from '../../services/workflow.service';
import { AddtaskComponent } from '../addtask/addtask.component';
import { GestionTaskComponent } from '../gestion-task/gestion-task.component';

@Component({
  selector: 'app-add-transition',
  templateUrl: './add-transition.component.html',
  styleUrls: ['./add-transition.component.scss']
})
export class AddTransitionComponent implements OnInit {
  loading:any;  
  workflowform: any[];
  selectedFile: File;
  containerId:any
  process_id:any
  processdef:any
  idwrkf:any 
  taskId:any
  marked = false;
  theCheckbox = false;
  addTrnForm = this.formbuild.group({
    trnId:  [],  
    trnAction:[''],
    trnTskActuel: [''],    
    trnTskSuiv: [''],    
  });
  constructor(private formbuild: FormBuilder, private router: Router, private workflowService: WorkflowService,
    private notification:NotificationService, private translate:TranslateService,
    public dialogRef: MatDialogRef<AddtaskComponent>, @Inject(MAT_DIALOG_DATA) public elementform: any) {
      this.containerId = this.elementform.wkfConteneur
      this.process_id = this.elementform.wkfProcess_id
      this.idwrkf  = this.elementform.wkfid
      this.taskId=this.elementform.tskId
    
  }
  ngOnInit() {  
    console.log(this.elementform)
    this.addTrnForm.setValue({
      trnId:"",
      trnAction:[''],
      trnTskActuel:this.elementform.idwrktsk,
      trnTskSuiv: [], 
    })
    this.extractProcessDefUser(this.containerId ,this.process_id)
  }
  extractProcessDefUser(containerId:any,processId:any) {
    this.workflowService.extractProcessDefUser(this.containerId,this.process_id).subscribe(data => {  
      this.processdef = data.task
      for (var i = 0; i <  this.processdef.length; i++){  
        console.log("+++++++++++++"+JSON.stringify(this.processdef[i]["task-id"] ));
      } 
      
    })
  }
  onSubmit(){  
   // alert( this.taskId)
  //  this.addTrnForm.setValue({
  //   trnId:'',
  //   trnTskActuel:this.taskId,
  //   trnAction:[],
  //   trnTskSuiv: [], 
  // })
   //this.addTrnForm.value.trnTskActuel=this.elementform.tskId
    //this.addTrnForm.value.trnId=this.elementform.tskId;
    //alert('------------------------'+JSON.stringify(this.addTrnForm.value))
   this.workflowService.createTransition(this.addTrnForm.value).subscribe(data=>{
        this.notification.success('transition créé');
       // alert(data.data);
        this.closeDialog();
       }); 
  }

  
  closeDialog() {
    this.dialogRef.close();
  }


}
