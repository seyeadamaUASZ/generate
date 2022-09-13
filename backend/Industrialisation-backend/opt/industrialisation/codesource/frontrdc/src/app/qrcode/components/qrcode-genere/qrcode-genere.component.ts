import { Component, Input, OnInit, SimpleChanges, OnChanges, ViewChild } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Router, ActivatedRoute, NavigationEnd } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { GeneratedFile } from '@angular/compiler';
import { filter } from 'rxjs/operators';
import { QrcodeComponent } from '@techiediaries/ngx-qrcode';
import { FieldBase } from 'src/app/sharedcomponent/dynamicform/models/field-base';
import { FieldControlService } from 'src/app/sharedcomponent/dynamicform/services/field-control.service';
import { FieldService } from 'src/app/sharedcomponent/dynamicform/services/field.service';
import { QrcodeService } from '../../services/qrcode.service';

@Component({
  selector: 'app-qrcode-genere',
  templateUrl: './qrcode-genere.component.html',
  styleUrls: ['./qrcode-genere.component.css'],
  providers: [ FieldControlService, FieldService ]
})
export class QrcodeGenereComponent implements OnInit {
  @Input() fields: FieldBase<string>[] = [];
   @Input() data: any[];
  form: FormGroup;
  payLoad = '';
  exportFileId:any;
  previousUrl: String;
  dataValue;
  href;
  nomQrcode:any;
  display = false;
  @ViewChild(QrcodeComponent) qrcodeChild: QrcodeComponent
  constructor(private router: Router, private translate: TranslateService,private qrcodeService: QrcodeService, private qcs: FieldControlService, private service: FieldService) {    
    if(this.router.getCurrentNavigation()&&this.router.getCurrentNavigation().extras&&this.router.getCurrentNavigation().extras.state){         
       sessionStorage.setItem('fileid', this.router.getCurrentNavigation().extras.state['data']);
       sessionStorage.setItem('previousUrl', this.router.getCurrentNavigation().extras.state['data']); 
       this.exportFileId  = this.router.getCurrentNavigation().extras.state['data'];
       this.previousUrl = this.router.getCurrentNavigation().extras.state['previousUrl'];  
     } else {
       this.exportFileId = sessionStorage.getItem('fileid');
       this.previousUrl = sessionStorage.getItem('previousUrl');
     } 
  }

  ngOnInit(): void {
     this.form = new FormGroup({});     
     this.qrcodeService.fieldChampsByQrcode(this.exportFileId).subscribe(data => {            
     for(var i=0; i < data.data.length; i++){                                           
      this.fields.push(this.service.buildField(this.qrcodeService.convertChampsToField(data.data[i])));                                                             
     }                                         
      if (this.fields) {
        this.fields = this.fields.sort((a, b) => a.order - b.order).slice(0); 
        this.form = this.qcs.toFormGroup(this.fields);        
      }
      });
     
  }
  
  onSubmit() { 
 
  }

  gotoPreviousPage(): void{   
    this.router.navigate([this.previousUrl]);
  }

  genererQrcode() {
    this.dataValue = JSON.stringify(this.form.value);
    this.display = true
  }
  downloadQrcode() {
    this.href = this.qrcodeChild.qrcElement.nativeElement.firstChild.src;

   }

}
