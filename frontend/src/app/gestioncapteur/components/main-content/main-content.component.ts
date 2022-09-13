import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatDialog, MatTableDataSource } from '@angular/material';
import { ActivatedRoute, Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { NotificationService } from 'src/app/shared/services/notification.service';
import { Capteur } from '../../models/capteur.model';
import { CapteurSService } from '../../services/capteur-s.service';
import {  merge, Observable } from 'rxjs';
import { AddcapteurComponent } from '../addcapteur/addcapteur.component';
import { ConfirmDialogComponent, ConfirmDialogModel } from 'src/app/sharedcomponent/confirm-dialog/confirm-dialog.component';
import { EditcapteurComponent } from '../editcapteur/editcapteur.component';

@Component({
  selector: 'app-main-content',
  templateUrl: './main-content.component.html',
  styleUrls: ['./main-content.component.scss']
})
export class MainContentComponent implements OnInit {
  displayedColumns: string[] = ['libelle', 'description','action'];
  capteurs:any;
  loading: boolean;
  code:any;
  donnees:any;
  form: FormGroup = new FormGroup({
    libelle: new FormControl(false),
    description: new FormControl(false),
    action: new FormControl(false),
  });


  donneeCapteurRecuperee(){
    this.capS.donneesRecupereesCapteurs()
    .subscribe(data=>{
      this.donnees=data;
      console.log(this.donnees);
    },err=>{
      console.log(err);
    })
  }



  libelle = this.form.get('libelle');
  description = this.form.get('description');
  action = this.form.get('action');


  columnDefinitions = [
    { def: 'libelle', label: 'Libelle', hide: false },
    { def: 'description', label: 'Evenement', hide: false },
    { def: 'action', label: 'Action', hide: false }
  ]


  cbValues;
  dataSource: MatTableDataSource<Capteur>;
  constructor(private capS:CapteurSService, private router: Router,
    private route: ActivatedRoute,
    private dialog: MatDialog, private translate:TranslateService, private notification: NotificationService) {
      this.loading=false;
    }

  ngOnInit() {
    this.getAllCapteurs()
    this.donneeCapteurRecuperee();
  }


  openDonnees(){
    this.router.navigate(['/gestioncapteur/donneesCapturess']);
  }


  ngAfterViewInit() {
    let o1: Observable<boolean> = this.libelle.valueChanges;
    let o2: Observable<boolean> = this.description.valueChanges;
    let o3:Observable<boolean> = this.action.valueChanges;
    merge(o1, o2, o3).subscribe(v => {
      this.columnDefinitions[0].hide = this.libelle.value;
      this.columnDefinitions[1].hide = this.description.value;
      this.columnDefinitions[2].hide = this.action.value;
      console.log(this.columnDefinitions);
    });

  }

  openDialogEditModule(element){
    console.log("mounted element ")
    const dialog1 = this.dialog.open(EditcapteurComponent, {
      disableClose: true,
      data: element,
      width:'500px'

    }).afterClosed().subscribe(data => {
      this.getAllCapteurs();
    });
  }


  applyFilterModule(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  openAddCapteur(){
    const dialog1 = this.dialog.open(AddcapteurComponent, {
      //disableClose: true,
      disableClose: true,
      width:'500px'
      //height:'500px'

    }).afterClosed().subscribe(data => {
      this.getAllCapteurs();
    });
  }

  getDisplayedColumns(): string[] {
    return this.columnDefinitions.filter(cd => !cd.hide).map(cd => cd.def);
  }


  supprimerModule(element){
    let alertSupp;
    this.translate.get('application.module.confirm-suppression').subscribe((res: string) => {
      alertSupp = res;
    });
    const message = "Alert.confirm-action";
    const dialogData = new ConfirmDialogModel("application.module.alert-suppression", message);
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      disableClose: true,
      maxWidth: "400px",
      data: dialogData
    });
    dialogRef.afterClosed().subscribe(dialogResult => {
      if (dialogResult === true) {
        this.loading = true;
        this.capS.deleteCapteur(element.idCapteur).subscribe((data) => {
          this.loading = false;
          this.notification.info(alertSupp);
          this.getAllCapteurs();
        });
      }
    });
  }

  getAllCapteurs(){
    this.capS.allCapteurs()
    .subscribe(data=>{
      if(data.statut){
        this.capteurs=data.data
        this.dataSource = new MatTableDataSource<Capteur>(this.capteurs);
        console.log(this.dataSource);
      }else{
        //console.log("parametre otp nom récupéré !!!");
      }

    })
  }



}
