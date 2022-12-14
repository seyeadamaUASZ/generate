import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { MatDialogRef, MatSnackBar, MatDialog } from '@angular/material';
import {STEPPER_GLOBAL_OPTIONS} from '@angular/cdk/stepper';
import { FichierService } from '../../services/fichier.service';
import { TranslateService } from '@ngx-translate/core';
import { QrcodeService } from 'src/app/parametrage/services/qrcode.service';

@Component({
  selector: 'app-ajouter-fichier',
  templateUrl: './ajouter-fichier.component.html',
  styleUrls: ['./ajouter-fichier.component.scss'],
  providers: [{
    provide: STEPPER_GLOBAL_OPTIONS, useValue: {showError: true}
  }]
})
export class AjouterFichierComponent implements OnInit {
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  public fichierjrxml: Array<File>;
  isFileValid: boolean;
  images;
  href:any
  base64Data: any;
  based

  @Input() accept = '.jrxml';
  @Input() progress;

  FormulaireForm = this.formbuild.group({
    // rptDescription: ['', Validators.required, true],
    rptNom: ['', Validators.required],
  });
  constructor(private formbuild: FormBuilder, private router: Router,private dialog:MatDialog, private qrcodeService:QrcodeService,
    private fichierService: FichierService,
    private _snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<AjouterFichierComponent>, 
		private translate: TranslateService) {
    this.isFileValid = false;
  }

  ngOnInit() {
    
  }

  get f() { return this.FormulaireForm.controls; }

  onSubmit() {
    if (this.FormulaireForm.valid && this.isFileValid) {
      this.fichierService.addRapport(this.fichierjrxml[0], this.FormulaireForm.value).subscribe(data => {
        if (data.statut) {
          let ReportSaveSuccess;
          this.translate.get('fichier.confirmEnr').subscribe((res: string) => {
            ReportSaveSuccess = res;
          });
          this._snackBar.open(ReportSaveSuccess, 'Verification', {
            duration: 2000,
          });

          this.FormulaireForm.reset();
          this.closeDialog();
        }
      }, error => {
        let ReportSaveError;
        this.translate.get('fichier.erreurEnr').subscribe((res: string) => {
          ReportSaveError = res;
        });
        this._snackBar.open(ReportSaveError, 'Verification', {
          duration: 2000,
        });
      });
    } else {
      let errorChamps;
      let form;
      this.translate.get('control.error').subscribe((res: string) => {
        form = res;
      });
      this.translate.get('control.required').subscribe((res: string) => {
        errorChamps = res;
      });
      this._snackBar.open(errorChamps, form, {
        duration: 2000,
      });
    }
  }

  uploadJrxmlFile(event: any) {
    if (event.target.files[0]) {
      const extension = event.target.files[0].name.split('.')[1].toLowerCase();
      if ( "jrxml" === extension ) {
        this.isFileValid = true;
      }
      this.fichierjrxml = event.target.files;
    }
  }

  closeDialog() {
    this.dialogRef.close();
  }
  openDialogAttributionQrCodes(): void {
    //this.router.navigate(['/application/fonctionnalite/', id]);
    this.router.navigate(['/fichier/attribut']);

  }

}
