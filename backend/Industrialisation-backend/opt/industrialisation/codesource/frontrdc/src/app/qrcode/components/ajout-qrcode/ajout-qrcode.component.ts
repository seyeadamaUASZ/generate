import { Component, OnInit, Inject } from '@angular/core';
import { Validators, FormBuilder } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { MatDialog, MatSnackBar, MAT_DIALOG_DATA } from '@angular/material';
import { NotificationService } from 'src/app/shared/services/notification.service';
import { TranslateService } from '@ngx-translate/core';
import { QrcodeService } from '../../services/qrcode.service';
@Component({
  selector: 'app-ajout-qrcode',
  templateUrl: './ajout-qrcode.component.html',
  styleUrls: ['./ajout-qrcode.component.scss']
})
export class AjoutQrcodeComponent implements OnInit {


  loading: any;
  QrcodeForm = this.formbuild.group({
    qrcNom: ['', Validators.required],
    qrcDescription: ['', Validators.required],
    qrcContenu: [''],
  });
  result: any
  constructor(private formbuild: FormBuilder, private router: Router,
    private qrcodeService: QrcodeService,
    public dialogRef: MatDialog, private _snackBar: MatSnackBar,
    private route: ActivatedRoute, private notification: NotificationService, private translate: TranslateService, ) {

  }

  ngOnInit() {
  }

  closeDialog() {
    this.dialogRef.closeAll();
  }

  onSubmit() {
    if (this.QrcodeForm.valid) {
      this.qrcodeService.saveQrcode(this.QrcodeForm.value).subscribe(data => {
        if (data.statut) {
          this.translate.get(data.description).subscribe((res: string) => {
            this.notification.success(res);
          });
          console.log(data);
          this.QrcodeForm.reset();
          this.closeDialog();

        }

      }, error => {
        this.translate.get(error).subscribe((res: string) => {
          this.notification.error(res);
        });
      });



    }
    else {
      this.translate.get('formulaire invalide').subscribe((res: string) => {
        this.notification.error(res);
      });
    }
  }
}
