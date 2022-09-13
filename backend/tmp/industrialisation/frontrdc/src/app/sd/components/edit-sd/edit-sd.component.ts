import { SdService } from '../../service/sd.service';
import { Sd } from '../../model/sd';
import { Component, OnInit,Inject } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MatSnackBar,MAT_DIALOG_DATA } from '@angular/material';
@Component({
  selector: 'app-edit-sd',
  templateUrl: './edit-sd.component.html',
  styleUrls: ['./edit-sd.component.scss']
})
export class EditSdComponent implements OnInit {
result:any;
SdForm = this.formbuild.group({
id :['', Validators.required],
email-1597160920710 :['', Validators.required],
email-1597160922774 :['', Validators.required],
text-1597160904814 :['', Validators.required],
 });  constructor(private sdService: SdService,
    private router: Router, private formbuild: FormBuilder, 
    private _snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<EditSdComponent>,@Inject(MAT_DIALOG_DATA) public donnee: any) { }

  ngOnInit() {
this.initView();
  }
initView() {   
    this.SdForm.setValue({ 
     id: this.donnee.id,
email-1597160920710:this.donnee.email-1597160920710,
email-1597160922774:this.donnee.email-1597160922774,
text-1597160904814:this.donnee.text-1597160904814,
 });  
  }
   onSubmit() {
    this.sdService.createSd(this.SdForm.value).subscribe(data => {
  
  this.result=data 
      if (this.result.statut) {
        this._snackBar.open(this.result.description, 'Verification', {
          duration: 2000,
        });

        this.SdForm.reset();
        this.closeDialog();
      }
    }, error => {
      alert('Sd invalide');
    });
  }


  closeDialog() {
    this.dialogRef.close();
  }
}