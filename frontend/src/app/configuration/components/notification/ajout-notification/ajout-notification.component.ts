import { Component, OnInit, Inject, ViewChild, Input } from '@angular/core';
import { SelectionModel } from '@angular/cdk/collections';
import { MatTableDataSource, MatDialog, MatSnackBar, MAT_DIALOG_DATA, MatDialogRef, MatPaginator, MatSort } from '@angular/material';
import { Router } from '@angular/router';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { TranslateService } from '@ngx-translate/core';

import { NotificationService } from '../../../../shared/services/notification.service';
import { Profil } from 'src/app/parametrage/models/profil';
import { TypeNotification } from 'src/app/parametrage/models/type-notification';
import { DomSanitizer, SafeResourceUrl, SafeUrl} from '@angular/platform-browser';
import { NotificationMessage } from 'src/app/parametrage/models/notification';
import { UserService } from 'src/app/parametrage/services/user.service';
import { NotificationServiceMessage } from 'src/app/parametrage/services/notification.service';

@Component({
  selector: 'app-ajout-notification',
  templateUrl: './ajout-notification.component.html',
  styleUrls: ['./ajout-notification.component.scss']
})
export class AjoutNotificationComponent implements OnInit {
	@ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @Input() accept = '.mp3, .ogg, .aac, .wav, .wma, .m4a, .3gpp, .amr';
  public audio: File;
  audioSource:any;
  audioName = "";
  public audioBase64data: any;
  audioAction = "";
  BASE64_MARKER = ';base64,';
  public profil: Profil;
  public typeNotification: TypeNotification;
  public profilSelected: Profil;
  panelOpenState = false;
  isAudio = false;
  isAudioSelect = false;
  destinataires = [];
  addedDestinataires = [];
  typeNotSelected:boolean;
  public notification: NotificationMessage;
  removedDestinataires = [];
	dataSource: MatTableDataSource<NotificationMessage>;
  profilControl = new FormControl('', Validators.required);
  typeNotControl = new FormControl('', Validators.required);
  selectFormControl = new FormControl('', Validators.required);
  profils: Profil[];
  typeNotifications: TypeNotification[];
  public NotificationForm = this.formbuild.group({
    ntfObjet: ['', Validators.required],
    ntfSignature: ['', Validators.required],
    ntfText: [''],
    ntfAudio: ['']
});
  constructor(private formbuild: FormBuilder, private router: Router,
    private userService: UserService,
    private notificationService: NotificationServiceMessage,
    private notificationAlert: NotificationService,
    private ref: MatDialog,
    private readonly translate: TranslateService,
    @Inject(MAT_DIALOG_DATA) public donnee,
    public dialogRef: MatDialogRef<AjoutNotificationComponent>,
    private sanitizer: DomSanitizer,
  ) {
    if (this.router.getCurrentNavigation() && this.router.getCurrentNavigation().extras) {
      this.profil = this.router.getCurrentNavigation().extras.state["profil"];
      this.typeNotification = this.router.getCurrentNavigation().extras.state['typeNotification'];
    }
  }

  ngOnInit() {
    this.notification = this.donnee;
    this.translate.get('notification.no-file').subscribe((res: string) => {
      this.audioName = res;
    });
    this.translate.get('notification.add-audio').subscribe((res: string) => {
      this.audioAction = res;
    });
    let p = new Profil();
    p.proId = this.profil?.proId;
    this.addedDestinataires = [];
    this.removedDestinataires = [];
    this.notificationService.listeProfils().subscribe(data => {
      this.profils = data.data;
    });
    this.notificationService.listeTypeNotification().subscribe(data => {
      this.typeNotifications = data.data;
    });
    this.listeDestinataire();
  }

  applyFilter(event: Event) {
		const filterValue = (event.target as HTMLInputElement).value;
		this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  listeDestinataire() {
    this.notificationService.listeDestinataires().subscribe(data => {
      this.destinataires = data.data;
      this.dataSource = new MatTableDataSource<NotificationMessage>(data.data);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sortingDataAccessor = this.sortingCaseInsentive(); 
      this.dataSource.sort = this.sort;
    });
  }
  
  ngAfterViewInit() {
    this.profilControl.setValue(this.profil);
    this.typeNotControl.setValue(this.typeNotification)
  }

  onSubmit() {
    if(!this.NotificationForm.valid && !this.typeNotControl.valid && !this.profilControl.valid){
      this.translate.get('fonctionnalite.invalid-form').subscribe((res: string) => {
        this.notificationAlert.error(res);
       });
    }else{
      this.NotificationForm.value.ntfTntId = this.typeNotControl.value;
      let p = this.profilControl.value;
      let n = this.NotificationForm.value;
      if (this.typeNotControl.value.tntNom != "Vocal") {
        this.notificationService.addNotificationParProfil(p,n).subscribe(data => {
          if (data.statut) {
            this.translate.get(data.description).subscribe((res: string) => {
              this.notificationAlert.success(res);
            });
            this.NotificationForm.reset();
            this.closeDialog();
  
          }
        }, error => {
         this.translate.get('notification.error').subscribe((res: string) => {
            this.notificationAlert.error(res);
           });
        });
      } else {
        if (this.audio == null) {
          this.translate.get('notification.no-file').subscribe((res: string) => {          
            this.notificationAlert.error(res);
          });
        } else {
          const file = new File([this.audio], "voice.mp3");
          var reader = new FileReader();
          reader.readAsDataURL(file);
          reader.onloadend = () => {
            var base64data = reader.result;
            let audioByte = this.base64ToArrayBuffer (JSON.stringify(base64data).split(',')[1]);
            this.notificationService.addNotificationAudioParProfil(p,n,audioByte).subscribe(data => {
              if (data.statut) {
                this.translate.get(data.description).subscribe((res: string) => {
                  this.notificationAlert.success(res);
                });
                console.log(data);
                this.NotificationForm.reset();
                this.closeDialog();
      
              }
            }, error => {
             this.translate.get('notification.error').subscribe((res: string) => {
                this.notificationAlert.error(res);
               });
            });
          }
          // console.log(this.audioBase64data)
          // this.notificationService.addNotificationAudioParProfil(p,n,this.audio).subscribe(data => {
          //   if (data.statut) {
          //     this.translate.get(data.description).subscribe((res: string) => {
          //       this.notificationAlert.success(res);
          //     });
          //     console.log(data);
          //     this.NotificationForm.reset();
          //     this.closeDialog();
    
          //   }
          // }, error => {
          //  this.translate.get('notification.error').subscribe((res: string) => {
          //     this.notificationAlert.error(res);
          //    });
          // });
        }
      }
    }
      
  }

  getBase64data (base64data) {
    console.log(base64data);
  }

  sortingCaseInsentive() {      
    return (data, sortHeaderId) => data[sortHeaderId].toLocaleLowerCase();
  }

  profilSelection() {
    this.profil = this.profilControl.value;
    if (this.profil) {
      this.notificationService.listeDestinataireParProfils(this.profil).subscribe(data => {
        this.destinataires = data.data;
        this.dataSource = new MatTableDataSource<NotificationMessage>(data.data);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sortingDataAccessor = this.sortingCaseInsentive(); 
        this.dataSource.sort = this.sort;
      });
    } else {
      this.listeDestinataire();
    }
  }

  typeNotSelection() {
    this.typeNotification = this.typeNotControl.value;
    if (this.typeNotification.tntNom == "Vocal") {
      this.isAudio = true;
    }
    else {
      this.isAudio = false;
    }
  }

  onSelectAudioFile(event) {
    if (event.target.files[0]) {
      this.audio = event.target.files[0];
      if (this.audio.size > 2000000) {
        this.translate.get('notification.error-audio-size').subscribe((res: string) => {          
          this.notificationAlert.error(res);
        });
      } else {
        const file = new File([this.audio], "voice.mp3");
        this.audioSource = this.sanitizer.bypassSecurityTrustResourceUrl(URL.createObjectURL(file));
        this.audioName = this.audio.name.split('.')[0].toLowerCase();
        this.isAudioSelect = true;
        this.translate.get('notification.edit-audio').subscribe((res: string) => {
          this.audioAction = res;
        });
      }
    }
  }

  playAudio(){
    let a = new Audio();
    a.src = "../../../../assets/test1.wav";
    a.load();
    a.play();
  }

  compareProfil(p1: Profil, p2: Profil): boolean {
    return p1 && p2 ? p1.proId === p2.proId : false;
  }

  compareTypeNot(tn1: TypeNotification, tn2: TypeNotification): boolean {
    return tn1 && tn2 ? tn1.tntId === tn2.tntId : false;
  }

  closeDialog() {
    this.dialogRef.close();
  }

  // convertDataURIToBinary(dataURI) {
  //   var base64Index = dataURI.indexOf(this.BASE64_MARKER) + this.BASE64_MARKER.length;
  //   var base64 = dataURI.substring(base64Index);
  //   var raw = window.atob(base64);
  //   var rawLength = raw.length;
  //   var array = new Uint8Array(new ArrayBuffer(rawLength));
  
  //   for(let i = 0; i < rawLength; i++) {
  //     array[i] = raw.charCodeAt(i);
  //   }
  //   return array;
  // }

  base64ToArrayBuffer(base64) {
    var binary_string = window.atob(base64);
    var len = binary_string.length;
    var bytes = new Uint8Array(len);
    for (var i = 0; i < len; i++) {
        bytes[i] = binary_string.charCodeAt(i);
    }
    return bytes.buffer;
  }

}
