import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { NotificationService } from 'src/app/shared/services/notification.service';
import { WorkflowService } from '../../services/workflow.service';

@Component({
  selector: 'app-editformulaire',
  templateUrl: './editformulaire.component.html',
  styleUrls: ['./editformulaire.component.scss']
})
export class EditformulaireComponent implements OnInit {
  loading:any;  
  idform:any
  marked = false;
  theCheckbox = false; 
  modifAddWrkForm = this.formbuild.group({
    wfcId:[''],
    nomform: ['', [Validators.required]],  
    idform: ['', Validators.required], 
   

  });
  constructor(private formbuild: FormBuilder, private router: Router, private workflowService: WorkflowService,
    private notification:NotificationService, private translate:TranslateService,
    public dialogRef: MatDialogRef<EditformulaireComponent>, @Inject(MAT_DIALOG_DATA) public element: any) { 
  console.log(JSON.stringify(element))
  }

  ngOnInit() {
  
    this.listeFormulaireNonGenerer()
    this.initView()
  
  }
  initView() {     
    this.modifAddWrkForm.setValue({  
      wfcId: this.element.wfcId,
      nomform: this.element.name,
      idform: this.element.wfcIdFormulaire
    });  
  }

  listeFormulaireNonGenerer(){
    this.workflowService.getList().subscribe((data:any)=>{
      this.idform =  data.data;
      console.log("++++++++++++"+JSON.stringify(this.idform))
       
    })
  }
 
  toggleVisibility(e){
    this.marked= e.target.checked;
  }
  onSubmit(){ 
    if (this.modifAddWrkForm.valid) {
      let formData = new FormData();  
      formData.append("workflowform",JSON.stringify(this.modifAddWrkForm.value));  
       
     this.workflowService.modifierjbpmform2(formData).subscribe(data=>{
          this.notification.success('formulaire chargé');
          this.closeDialog();
         });
        }
  }
  closeDialog() {
    this.dialogRef.close();
  }


}
