import { Component, OnInit, Input, Inject, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { TranslateService } from '@ngx-translate/core';
import { NotificationService } from '../../../shared/services/notification.service';
import {OuverturecompteService} from '../../service/ouverturecompte.service';

@Component({
	selector: 'app-add-ouverturecompte',
	templateUrl: './add-ouverturecompte.component.html',
	styleUrls: ['./add-ouverturecompte.component.scss']
})
export class AddOuverturecompteComponent implements OnInit {

	status:any;
	donnee:any;
	
	
	constructor(
		private ouverturecompteService: OuverturecompteService,
    	private router: Router, private formBuilder: FormBuilder,
    	private translate: TranslateService,
    	private notification: NotificationService,
   		@Inject(MAT_DIALOG_DATA) public data: any,
    	public dialog: MatDialogRef<AddOuverturecompteComponent> ) { }
	
	EditForm = this.formBuilder.group({
		id: [''],
    	nom:['',Validators.required],
prenom:['',Validators.required],
adresse:['',Validators.required],
datenaissance:['',Validators.required],
telephone:['',Validators.required],
cni:['',Validators.required],
certificationresidence:['',Validators.required],
typecompte:['',Validators.required],
typeservice:['',Validators.required],

    	poOwner: [''],
    	status: [''],
    	owner: [''],
    	idLink: ['']
    });

	ngOnInit() {
		
		if(this.data){
			this.donnee = this.data.data;
			this.status = this.data.status;
			this.EditForm.setValue(this.donnee);
		}
  	}
  	
  	onSubmit(){
  		if (this.donnee) {
      		this.EditForm.value.idLink = this.donnee.id
    	} else { 
    		this.EditForm.value.idLink = null 
    	}
  		this.EditForm.value.poOwner = localStorage.getItem("profile")
  this.EditForm.value.owner = localStorage.getItem("id")
  this.EditForm.value.status = this.status
if(this.cni || this.certificationresidence){
      this.translate.get("ouverturecompte.selectAllFilesNotif").subscribe((res: string) => {
        this.notification.warn(res);
      });
      return;
    }if(this.EditForm.invalid){
      this.translate.get('ouverturecompte.remplirTousLesChampsNotif').subscribe(data=>{
        this.notification.warn(data);
      })
      return;
    }
      this.ouverturecompteService.createOuverturecompte(this.EditForm.value , this.cni, this.certificationresidence).subscribe((data:any) => {
        if (data.statut) {
          this.translate.get('ouverturecompte.confirmEnr').subscribe((res: string) => {
            this.notification.success(res);
          });

          			
this.ouverturecompteService.updateTaskOuverturecompte(this.donnee.id, this.status).subscribe(data => {

          })
		   this.EditForm.reset();
          this.dialog.close({status:true});
        }
      }, error => {
        let ReportSaveError;
        this.translate.get('ouverturecompte.erreurEnr').subscribe((res: string) => {
          this.notification.error(res);
        });
      });

  	}
  	
  	closeDialog(){
  		this.dialog.close({status:false});
  	}
  	
  	@ViewChild('filecni') filecni;

 cni:File;

  addFilecni(){
    this.filecni.nativeElement.click();
  }

  onFileAddedcni(){
    this.cni = this.filecni.nativeElement.files[0];
    const extension = this.cni.name.split('.')[1].toLowerCase();
       if ( "pdf" != extension ) {
        this.translate.get("ouverturecompte.fileExtenxionNotif").subscribe((res: string) => {
          this.notification.warn(res);
        });
        this.cni =null;
        return;
       }
      if (this.cni.size >3000000){
        this.translate.get("ouverturecompte.fileSizeNotif").subscribe((res: string) => {
          this.notification.warn(res);
        });
        this.cni = null;
        return;
      }
  }
@ViewChild('filecertificationresidence') filecertificationresidence;

 certificationresidence:File;

  addFilecertificationresidence(){
    this.filecertificationresidence.nativeElement.click();
  }

  onFileAddedcertificationresidence(){
    this.certificationresidence = this.filecertificationresidence.nativeElement.files[0];
    const extension = this.certificationresidence.name.split('.')[1].toLowerCase();
       if ( "pdf" != extension ) {
        this.translate.get("ouverturecompte.fileExtenxionNotif").subscribe((res: string) => {
          this.notification.warn(res);
        });
        this.certificationresidence =null;
        return;
       }
      if (this.certificationresidence.size >3000000){
        this.translate.get("ouverturecompte.fileSizeNotif").subscribe((res: string) => {
          this.notification.warn(res);
        });
        this.certificationresidence = null;
        return;
      }
  }

  	
  	
}
