import { SdService } from '../../service/sd.service';
import { Sd } from '../../model/sd';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MatSnackBar } from '@angular/material';
@Component({
  selector: 'app-add-sd',
  templateUrl: './add-sd.component.html',
  styleUrls: ['./add-sd.component.scss']
})
export class AddSdComponent implements OnInit {
result:any;
resp:any;

SdForm = this.formbuild.group({email-1597160920710 :['', Validators.required],
email-1597160922774 :['', Validators.required],
text-1597160904814 :['', Validators.required],
 });  constructor(private sdService: SdService,
    private router: Router, private formbuild: FormBuilder, 
    private _snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<AddSdComponent >) { }

  ngOnInit() {
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