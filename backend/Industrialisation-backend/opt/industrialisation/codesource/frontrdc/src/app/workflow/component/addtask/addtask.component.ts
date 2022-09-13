import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { NotificationService } from 'src/app/shared/services/notification.service';
import { WorkflowService } from '../../services/workflow.service';
import { UserService } from '../../../utilisateur/services/user.service';
@Component({
  selector: 'app-addtask',
  templateUrl: './addtask.component.html',
  styleUrls: ['./addtask.component.scss']
})
export class AddtaskComponent implements OnInit {
  loading:any;  
  workflowform: any[];
  selectedFile: File;
  containerId:any
  process_id:any
  processdef:any
  idwrkf:any 
  marked = false;
  theCheckbox = false;
  profiles: any[];

  status: any[];
  tasknfos: any[];
  idwrktsk:any

  addTskForm = this.formbuild.group({
    ownername:  ['', Validators.required],  
    taskname:['', Validators.required],   
    positionform: ['', Validators.required], 
    taskdescription: ['', Validators.required], 
    /*
<<<<<<< HEAD
    Tasknamesuivant: ['', Validators.required],
=======
*/
    statusId: ['', Validators.required],



  });
  constructor(private formbuild: FormBuilder, private router: Router,private userService: UserService, private workflowService: WorkflowService,
    private notification:NotificationService, private translate:TranslateService,
    public dialogRef: MatDialogRef<AddtaskComponent>, @Inject(MAT_DIALOG_DATA) public elementform: any) {
      this.containerId = this.elementform.wkfConteneur
      this.process_id = this.elementform.wkfProcess_id
      this.idwrkf  = this.elementform.wkfid
  }
  ngOnInit() {
    this.extractProcessDefUser(this.containerId ,this.process_id)
    this.extractProfilId()

    this.extractStatusId()

  }
  extractProfilId(){
  this.userService.listprofil().subscribe(res => {
    this.profiles = res.data;

    //console.log("+++++++++++++"+JSON.stringify(this.profiles));

});
}

extractStatusId(){
  this.workflowService.taskStatusAll().subscribe(res => {
    this.status =  res.data;
    console.log("+++++++++++++"+JSON.stringify(this.status));


});
}
  extractProcessDefUser(containerId:any,processId:any) {
    this.workflowService.extractProcessDefUser(this.containerId,this.process_id).subscribe(data => {  
      this.processdef = data.task
      for (var i = 0; i <  this.processdef.length; i++){  
        //console.log("+++++++++++++"+JSON.stringify(this.processdef[i]["task-name"] ));
      } 
      
    })
  }
  onSubmit(){ 
   
    let formData = new FormData(); 
    formData.append("taskgenform",JSON.stringify(this.addTskForm.value));  
    formData.append("idwrkf",this.idwrkf); 
   this.workflowService.createtask(formData).subscribe(data=>{
        this.notification.success('Document chargé');
        this.closeDialog();
       }); 
  }

  toggleVisibility(e){
    this.marked= e.target.checked;
  }
  closeDialog() {
    this.dialogRef.close();
  }


}
