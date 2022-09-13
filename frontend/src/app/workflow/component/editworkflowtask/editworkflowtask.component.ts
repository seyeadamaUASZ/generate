import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { NotificationService } from 'src/app/shared/services/notification.service';
import { UserService } from 'src/app/utilisateur/services/user.service';
import { WorkflowService } from '../../services/workflow.service';


@Component({
  selector: 'app-editworkflowtask',
  templateUrl: './editworkflowtask.component.html',
  styleUrls: ['./editworkflowtask.component.scss']
})
export class EditworkflowtaskComponent implements OnInit {
  loading:any;  
  workflowform: any[];
  selectedFile: File;
  containerId:any
  process_id:any
  processdef:any
  idwrktsk:any 
  idwrkf:any
  idtsk:any
  marked = false;
  theCheckbox = false;
  profiles: any[];
  tasknfos: any[];

  status: any[];
  tskId:any
  idform: any[]
  addTskForm = this.formbuild.group({
    tskId:  ['', Validators.required],
    ownername:  ['', Validators.required],  
    taskname:['', Validators.required],   
    taskdescription: ['', Validators.required],  
    Tasknamesuivant: [null],  
    statusId: ['', Validators.required], 
  });
  constructor(private formbuild: FormBuilder, private router: Router,private userService: UserService, private workflowService: WorkflowService,
    private notification:NotificationService, private translate:TranslateService,
    public dialogRef: MatDialogRef<EditworkflowtaskComponent>, @Inject(MAT_DIALOG_DATA) public elementform: any) { 
      console.log("++++++++++++++++++++++++++++++++++"+JSON.stringify(elementform))
     
  }
  ngOnInit() {
    this.extractProcessDefUser(this.containerId ,this.process_id)
    this.extractProfilId()
    this.listetaskparid(this.idwrktsk)
    this.listeFormulaireNonGenerer()
    this.extractStatusId()
    this.initView()
  }
   
  initView() {     
    this.addTskForm.setValue({  
      tskId: this.elementform.tskId,
      ownername: this.elementform.poOwner.proId,  
      taskname:this.elementform.tskFormName,    
      taskdescription:this.elementform.tskDescription,  
      Tasknamesuivant:this.elementform.tskFormNameSuiv,  
      statusId:this.elementform.tskStatusId, 
    });  
  }
  listeFormulaireNonGenerer(){
    this.workflowService.getList().subscribe((data:any)=>{
      this.idform =  data.data;
      //console.log("++++++++++++"+JSON.stringify(this.idform))
       
    })
  }
  extractStatusId(){
    this.workflowService.taskStatusAll().subscribe(res => {
      this.status =  res.data; 
  
  });
  }

  listetaskparid(idwrktsk:any) {
    this.workflowService.listetaskparid(this.idwrktsk).subscribe(data => {  
     
      if(data.statut){
        this.tasknfos = data.data;
      }
      
    })
  }
  extractProfilId(){
    this.userService.listprofil().subscribe(res => {
      this.profiles = res.data; 
  
  });
  }
  extractProcessDefUser(containerId:any,processId:any) {
    this.workflowService.extractProcessDefUser(this.containerId,this.process_id).subscribe(data => {  
      this.processdef = data.task
      for (var i = 0; i <  this.processdef.length; i++){   
      } 
      
    })
  }
  onSubmit(){ 
   
    let formData = new FormData(); 
    formData.append("taskgenform",JSON.stringify(this.addTskForm.value));    
   this.workflowService.updateworkflowtask(formData).subscribe(data=>{
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

