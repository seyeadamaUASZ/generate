import { Component, OnInit, Input, Output, EventEmitter, ViewChild, ElementRef } from '@angular/core';
import { Validators, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../../services/user.service';
import { MatDialogRef, MatSnackBar } from '@angular/material';
import { Subscription } from 'rxjs/Subscription';
import { of } from 'rxjs/observable/of';
import { catchError, last, map, tap } from 'rxjs/operators';
import { trigger, state, style, animate, transition } from '@angular/animations';
import { TranslateService } from '@ngx-translate/core';
import { HttpRequest, HttpClient, HttpEventType, HttpErrorResponse } from '@angular/common/http';
import { NotificationService } from '../../../shared/services/notification.service';
import { User } from '../../models/user';
import { RoleService } from 'src/app/utilisateur/services/role.service';
import * as XLSX from 'xlsx';

@Component({
      selector: 'app-ajout-utilis',
      templateUrl: './ajout-utilis.component.html',
      styleUrls: ['./ajout-utilis.component.scss'],
      animations: [
            trigger('fadeInOut', [
                  state('in', style({ opacity: 100 })),
                  transition('* => void', [
                        animate(300, style({ opacity: 0 }))
                  ])
            ])
      ]
})
export class AjoutUtilisComponent implements OnInit {

      importContacts: User[] = [];
      profiles: any[];
      pro: any[];
      file: any;
      /** Link text */
      @Input() text = 'Upload';
      /** Name used in form which will be sent in HTTP request. */
      @Input() param = 'file';
      /** Target URL for file uploading. */
      @Input() target = 'localhost';
      /** File extension that accepted, same as 'accept' of <input type="file" />.
          By the default, it's set to 'image/*'. */
      // @Input() accept = 'image/*';
      @Input() accept = '.xlsx,.xls,.csv';
      /** Allow you to add handler after its completion. Bubble up response text from remote. */
      imgSrc: string;
      @Output() complete = new EventEmitter<string>();
      public ficheExcel: Array<File>;

      public files: Array<FileUploadModel> = [];
      loading: any;
      public registreForm = this.formbuild.group({
            utiPrenom: ['', Validators.required],
            utiNom: ['', Validators.required],
            utiUsername: ['', [Validators.required, Validators.pattern('^[a-zA-Z0-9_]*$')]],
            utiPassword: [''],
            utiTelephone: ['', Validators.required],
            utiEmail: ['', Validators.required],
            utiAdresse: ['', Validators.required],
            uti_pro_id: ['', Validators.required]
      });
      constructor(private formbuild: FormBuilder, private router: Router, private userService: UserService, private roleService: RoleService,
            public dialogRef: MatDialogRef<AjoutUtilisComponent>,
            private _snackBar: MatSnackBar,
            private _http: HttpClient,
            private notification: NotificationService,
            private translate: TranslateService) {

      }

      ngOnInit() {
            this.userService.listprofil().subscribe(res => {
                  this.profiles = res.data;

            });
      }


      get f() { return this.registreForm.controls; }

      /** Upload File debut code ***/

      onClick() {
            const fileUpload = document.getElementById('fileUpload') as HTMLInputElement;
            fileUpload.onchange = () => {
                  for (let index = 0; index < fileUpload.files.length; index++) {
                        const file = fileUpload.files[index];
                        this.files.push({
                              data: file, state: 'in',
                              inProgress: false, progress: 0, canRetry: false, canCancel: true
                        });
                  }
                  this.uploadFiles();
            };
            fileUpload.click();
      }

      cancelFile(file: FileUploadModel) {
            file.sub.unsubscribe();
            this.removeFileFromArray(file);
      }

      retryFile(file: FileUploadModel) {
            this.uploadFile(file);
            file.canRetry = false;
      }

      private uploadFile(file: FileUploadModel) {
            const fd = new FormData();
            fd.append(this.param, file.data);

            const req = new HttpRequest('POST', this.target, fd, {
                  reportProgress: true
            });

            file.inProgress = true;
            file.sub = this._http.request(req).pipe(
                  map(event => {
                        switch (event.type) {
                              case HttpEventType.UploadProgress:
                                    file.progress = Math.round(event.loaded * 100 / event.total);
                                    break;
                              case HttpEventType.Response:
                                    return event;
                        }
                  }),
                  tap(message => { }),
                  last(),
                  catchError((error: HttpErrorResponse) => {
                        file.inProgress = false;
                        file.canRetry = true;
                        return of(`${file.data.name} upload failed.`);
                  })
            ).subscribe(
                  (event: any) => {
                        if (typeof (event) === 'object') {
                              this.removeFileFromArray(file);
                              this.complete.emit(event.body);
                        }
                  }
            );
      }

      private uploadFiles() {
            const fileUpload = document.getElementById('fileUpload') as HTMLInputElement;
            fileUpload.value = '';

            this.files.forEach(file => {
                  this.uploadFile(file);
            });

      }

      private removeFileFromArray(file: FileUploadModel) {
            const index = this.files.indexOf(file);
            if (index > -1) {
                  console.log(this.files.splice(index, 1));
            }
      }

      addUserByExcel() {

            const formData = new FormData();
            formData.append('excelFile', this.ficheExcel[0]);
            this.userService.chargerFile(formData).subscribe(
                  (res) => {
                        if (res.statut) {
                              this.translate.get(res.description).subscribe((res: string) => {
                                    this.notification.success(res);
                              });

                              this.closeDialog();
                        } else {
                              this.translate.get(res.description).subscribe((res: string) => {
                                    this.notification.warn(res);
                              });
                        }
                  }, error => {
                        this.translate.get("Verifier les  données saisies dans le fichier excel!!").subscribe((res: string) => {
                              this.notification.error(res);
                        });

                  })

      }

      // method upload excel file
      uploadExcelFile(event: any) {
            if (event.target.files[0]) {
                  this.ficheExcel = event.target.files;
            }
      }

      /** Upload File fin code **/

      onSubmit() {
            this.loading = true;
            if (this.registreForm.valid) {
                  this.userService.registreUser(this.registreForm.value).subscribe(data => {
                        if (data.statut) {
                              alert(JSON.stringify(data.data))
                              this.translate.get(data.description).subscribe((res: string) => {
                                    this.notification.success(res);
                              });
                              this.registreForm.reset();
                              //this.closeDialog();
                        }
                        else {
                              this.translate.get(data.description).subscribe((res: string) => {
                                    this.notification.warn(res);
                              });
                        }
                       // this.loading = false;
                  }, error => {
                        this.translate.get(error).subscribe((res: string) => {
                              this.notification.error(res);
                        });

                        //this.loading = false;
                  });
            } else {
                  this.translate.get('formulaire invalide').subscribe((res: string) => {
                        this.notification.error(res);
                  });

                  //this.loading = false;
            }

      }


      closeDialog() {
            this.dialogRef.close();
      }

      onFileChange(event: any) {
            this.file = event.target.files[0];
            this.fileReader(this.file, User);
      }


      private fileReader(file: any, User: any) {
            let fileReader = new FileReader();

            fileReader.onload = (e) => {
                  let arrayBuffer: any = fileReader.result;
                  const data = new Uint8Array(arrayBuffer);
                  const arr = new Array();

                  for (let i = 0; i !== data.length; i++) {
                        arr[i] = String.fromCharCode(data[i]);
                  }

                  const bstr = arr.join('');
                  const workbook = XLSX.read(bstr, { type: 'binary', cellDates: true });
                  const first_sheet_name = workbook.SheetNames[0];

                  const fworksheet = workbook.Sheets[first_sheet_name];
                  let worksheet = XLSX.utils.sheet_to_json(fworksheet, { raw: true });
                  console.log(worksheet);

                  /**
                   * Call matching function
                   */
                  this.matchingCell(worksheet, User);
            };
            fileReader.readAsArrayBuffer(file);
      }

      private matchingCell(worksheet: any, operation) {
            for (let i = 0; i < worksheet.length; i++) {
                  const worksheetLine = worksheet[i];
                  const updatedLine = {
                        utiPrenom: worksheetLine['Prenom'],
                        utiNom: worksheetLine['Nom'],
                        utiUsername: worksheetLine['Username'],
                        utiTelephone: worksheetLine['Telephone'],
                        utiEmail: worksheetLine['Mail'],
                        utiAdresse: worksheetLine['Adresse'],
                        uti_pro_id: worksheetLine['Profil'],
                  };
                  this.roleService.profilByLibelle(updatedLine.uti_pro_id).subscribe(data => {
                        if (data.statut) {
                              this.pro = data.data;
                              this.registreForm.value.utiPrenom = updatedLine.utiPrenom;
                              this.registreForm.value.utiNom = updatedLine.utiNom;
                              this.registreForm.value.utiUsername = updatedLine.utiUsername;
                              this.registreForm.value.utiTelephone = updatedLine.utiTelephone;
                              this.registreForm.value.utiEmail = updatedLine.utiEmail;
                              this.registreForm.value.utiAdresse = updatedLine.utiAdresse;
                              this.registreForm.value.uti_pro_id = this.pro;
                              this.userService.registreUser(this.registreForm.value).subscribe(data => {
                                    if (data.statut) {
                                          this.translate.get(data.description).subscribe((res: string) => {
                                                this.notification.success(res);
                                          });
                                          this.registreForm.reset();
                                          this.closeDialog();
                                          location.reload();
                                    } else {
                                          this.translate.get(data.description).subscribe((res: string) => {
                                                this.notification.warn(res);
                                          });
                                    }
                                    this.loading = false;
                              }, error => {
                                    this.translate.get(error).subscribe((res: string) => {
                                          this.notification.error(res);
                                    });
                              });
                              this.translate.get("Enregistrement reussi!!").subscribe((res: string) => {
                                    this.notification.success(res);
                              });
                              this.closeDialog();



                        } else {
                              this.translate.get(data.description).subscribe((res: string) => {
                                    this.notification.warn(res);
                              });

                        }

                  })


            }

      }

}

export class FileUploadModel {
      data: File;
      state: string;
      inProgress: boolean;
      progress: number;
      canRetry: boolean;
      canCancel: boolean;
      sub?: Subscription;
}
