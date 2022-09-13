import { Component, OnInit, Inject } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { MatDialog, MatSnackBar, MAT_DIALOG_DATA } from '@angular/material';
import { AjouterFormComponent } from '../ajouter-form/ajouter-form.component';
import { UserService } from 'src/app/utilisateur/services/user.service';
import { TranslateService } from '@ngx-translate/core';
import { DndDropEvent,DropEffect} from 'ngx-drag-drop';
import swal from 'sweetalert2';
import { NotificationService } from '../../../shared/services/notification.service';
import { field, value} from 'src/app/global.model';
import { Champs } from 'src/app/utilisateur/models/champs';
import { Formulaire } from 'src/app/utilisateur/models/formulaire';
import { ViewFormComponent } from '../view-form/view-form.component';
import { EditFormComponent } from '../edit-form/edit-form.component';
@Component({
  selector: 'app-modeliser-form',
  templateUrl: './modeliser-form.component.html',
  styleUrls: ['./modeliser-form.component.scss']
})
export class ModeliserFormComponent implements OnInit {
  value:value={
    label:"",
    value:""
  };
  success = false;
  fieldModels:Array<field>=[
    {
      "type": "text",
      "icon": "fa-font",
      "label": "Text",
      "description": "Enter your name",
      "placeholder": "Enter your name",
      "className": "form-control",
      "subtype": "text",
      "regex" : "",
      "handle":true
    },
    {
      "type": "email",
      "icon": "fa-envelope",
      "required": true,
      "label": "Email",
      "description": "Enter your email",
      "placeholder": "Enter your email",
      "className": "form-control",
      "subtype": "text",
      "regex" : "^([a-zA-Z0-9_.-]+)@([a-zA-Z0-9_.-]+)\.([a-zA-Z]{2,5})$",
      "errorText": "Please enter a valid email",
      "handle":true
    },
    {
      "type": "phone",
      "icon": "fa-phone",
      "label": "Phone",
      "description": "Enter your phone",
      "placeholder": "Enter your phone",
      "className": "form-control",
      "subtype": "text",
      "regex" : "^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$",
      "errorText": "Please enter a valid phone number",
      "handle":true
    },
    {
      "type": "number",
      "label": "Number",
      "icon": "fa-html5",
      "description": "Age",
      "placeholder": "Enter your age",
      "className": "form-control",
      "value": "20",
      "min": 12,
      "max": 90
    },
    {
      "type": "date",
      "icon":"fa-calendar",
      "label": "Date",
      "placeholder": "Date",
      "className": "form-control"
    },
    {
      "type": "datetime-local",
      "icon":"fa-calendar",
      "label": "DateTime",
      "placeholder": "Date Time",
      "className": "form-control"
    },
    {
      "type": "textarea",
      "icon":"fa-text-width",
      "label": "Textarea"
    },
    {
      "type": "paragraph",
      "icon": "fa-paragraph",
      "label": "Paragraph",
      "placeholder": "Type your text to display here only"
    },
    {
      "type": "checkbox",
      "required": true,
      "label": "Checkbox",
      "icon":"fa-list",
      "description": "Checkbox",
      "inline": true,
      "values": [
        {
          "label": "Option 1",
          "value": "option-1"
        },
        {
          "label": "Option 2",
          "value": "option-2"
        }
      ]
    },
    {
      "type": "radio",
      "icon":"fa-list-ul",
      "label": "Radio",
      "description": "Radio boxes",
      "values": [
        {
          "label": "Option 1",
          "value": "option-1"
        },
        {
          "label": "Option 2",
          "value": "option-2"
        }
      ]
    },
    {
      "type": "autocomplete",
      "icon":"fa-bars",
      "label": "Select",
      "description": "Select",
      "placeholder": "Select",
      "className": "form-control",
      "values": [
        {
          "label": "Option 1",
          "value": "option-1"
        },
        {
          "label": "Option 2",
          "value": "option-2"
        },
        {
          "label": "Option 3",
          "value": "option-3"
        }
      ]
    },
    {
      "type": "file",
      "icon":"fa-file",
      "label": "File Upload",
      "className": "form-control",
      "subtype": "file"
    },
    {
      "type": "button",
      "icon":"fa-paper-plane",
      "subtype": "submit",
      "label": "Submit"
    }
  ];
  loading: boolean;
  modelFields:Array<field>=[];
  model:any = {
    name:'App name...',
    description:'App Description...',
    theme:{
      bgColor:"ffffff",
      textColor:"555555",
      bannerImage:""
    },
    attributes:this.modelFields
  };
  breakpoint:any;
  report = false;
  reports:any = [];
formulaire:any;
formulairegenere:any;
formulairenotgenere:any;
fields:any;
champs:any={chpClass:"",chpLabel:"",chpNom:"",chpObligatoire:"",chpPlaceholder:"",chpTaille:"",chpType:"",chpRegex:"",chpIcon:"",chFrmId:0,valueChp:null};
valueChamps:any={label:""}
  constructor( private router: Router,
    private route: ActivatedRoute,
    private dialog: MatDialog,
    private dialogRef: MatDialog,
    @Inject(MAT_DIALOG_DATA) public donnee: any,
   // private dialogRef: MatDialogRef<AjoutAppComponent>,
    private notification: NotificationService,private userService:UserService, private translate: TranslateService) {
      this.loading=false;

     }

  ngOnInit() {
    this.breakpoint = (window.innerWidth <= 400) ? 1 : 5;
    this.listeFormulaire();
    this.listeFormulairegenerer();
    this.listeFormulairenotgenerer();
    this.listChampsFrm(this.donnee)
  }

  listChampsFrm(id){
    this.userService.fieldChampsByForm(id).subscribe(res=>{
      this.fields= res.data;
      console.log(this.fields)
    })
  }
  openDialogAddForm(): void {
    const dialog1 = this.dialog.open(AjouterFormComponent, {
      width: '700px',

    }).afterClosed().subscribe(result => {
			//location.reload();
			this.listeFormulairenotgenerer();
		});

  }
  openDialogModeliserForm(data){
    const dialog1 = this.dialog.open(ModeliserFormComponent, {
      disableClose: true,
      width: '700px',
      data: data
    });
  }
  listeFormulaire(){
    this.userService.listeFormulaire().subscribe(res=>{
      this.formulaire=res.data;
    })
  }
  listeFormulairegenerer(){
    this.userService.listeFormulairegenerer().subscribe(res=>{
      this.formulairegenere=res.data;
    })
  }

  listeFormulairenotgenerer(){
    this.userService.listeFormulaireNotgenerer().subscribe(res=>{
      this.formulairenotgenere=res.data;
    })
  }
  onDragStart(event:DragEvent) {
    console.log("drag started", JSON.stringify(event, null, 2));
  }

  onDragEnd(event:DragEvent) {
    console.log("drag ended", JSON.stringify(event, null, 2));
  }

  onDraggableCopied(event:DragEvent) {
    console.log("draggable copied", JSON.stringify(event, null, 2));
  }

  onDraggableLinked(event:DragEvent) {
    console.log("draggable linked", JSON.stringify(event, null, 2));
  }

   onDragged( item:any, list:any[], effect:DropEffect ) {
    if( effect === "move" ) {
      const index = list.indexOf( item );
      list.splice( index, 1 );
    }
  }

  onDragCanceled(event:DragEvent) {
    console.log("drag cancelled", JSON.stringify(event, null, 2));
  }

  onDragover(event:DragEvent) {
    console.log("dragover", JSON.stringify(event, null, 2));
  }

  onDrop( event:DndDropEvent, list?:any[] ) {
    if( list && (event.dropEffect === "copy" || event.dropEffect === "move") ) {

      if(event.dropEffect === "copy")
      event.data.name = event.data.type+'-'+new Date().getTime();
      let index = event.index;
      if( typeof index === "undefined" ) {
        index = list.length;
      }
      list.splice( index, 0, event.data );
    }
  }

  addValue(values){
    values.push(this.value);
    this.value={label:"",value:""};
  }

  removeField(i){
    swal({
      title: 'Are you sure?',
      text: "Do you want to remove this field?",
      type: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#00B96F',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, remove!'
    }).then((result) => {
      if (result.value) {
        this.model.attributes.splice(i,1);
      }
    });

  }

  updateForm(){
    let input = new FormData;
    input.append('id',this.model._id);
    input.append('name',this.model.name);
    input.append('description',this.model.description);
    input.append('bannerImage',this.model.theme.bannerImage);
    input.append('bgColor',this.model.theme.bgColor);
    input.append('textColor',this.model.theme.textColor);
    input.append('attributes',JSON.stringify(this.model.attributes));

    // this.us.putDataApi('/admin/updateForm',input).subscribe(r=>{
    //   console.log(r);
    //   swal('Success','App updated successfully','success');
    // });
  }


  initReport(){
    this.report = true;
    let input = {
      id:this.model._id
    }

    // this.us.getDataApi('/admin/allFilledForms',input).subscribe(r=>{
    //   this.reports = r.data;
    //   console.log('reports',this.reports);
    //   this.reports.map(records=>{
    //     return records.attributes.map(record=>{
    //       if(record.type=='checkbox'){
    //         record.value = record.values.filter(r=>r.selected).map(i=>i.value).join(',');
    //       }
    //     })
    //   });
    // });
  }



  toggleValue(item){
    item.selected = !item.selected;
  }

  submit(){
    let valid = true;
    let validationArray = JSON.parse(JSON.stringify(this.model.attributes));
    validationArray.reverse().forEach(field => {
      console.log(field.label+'=>'+field.required+"=>"+field.value);
      if(field.required && !field.value && field.type != 'checkbox'){
        swal('Error','Please enter '+field.label,'error');
        valid = false;
        return false;
      }
      if(field.required && field.regex){
        let regex = new RegExp(field.regex);
        if(regex.test(field.value) == false){
          swal('Error',field.errorText,'error');
          valid = false;
          return false;
        }
      }
      if(field.required && field.type == 'checkbox'){
        if(field.values.filter(r=>r.selected).length == 0){
          swal('Error','Please enterrr '+field.label,'error');
          valid = false;
          return false;
        }

      }
    });
    if(!valid){
      return false;
    }
    console.log('Save',this.model);
    let input = new FormData;
    input.append('formId',this.model._id);
    input.append('attributes',JSON.stringify(this.model.attributes))
    // this.us.postDataApi('/user/formFill',input).subscribe(r=>{
    //   console.log(r);
    //   swal('Success','You have contact sucessfully','success');
    //   this.success = true;
    // },error=>{
    //   swal('Error',error.message,'error');
    // });

  }
  ch_frm_id:any;
 addChamps(){   
   for(let i=0; i<this.model.attributes.length; i++){
    this.champs.chpLabel=this.model.attributes[i]['label'];
    this.champs.chpNom=this.model.attributes[i]['name'];
    this.champs.chpClass=this.model.attributes[i]['className'];
    this.champs.chpType=this.model.attributes[i]['type'];
    this.champs.chpObligatoire=this.model.attributes[i]['handle'];
    this.champs.chpTaille=this.model.attributes[i]['max'];
    this.champs.chpPlaceholder=this.model.attributes[i]['placeholder'];
    this.champs.chpRegex=this.model.attributes[i]['regex'];
    this.champs.chpIcon=this.model.attributes[i]['icon'];
    this.champs.chFrmId=this.donnee;
    if(this.model.attributes[i]['type']=='checkbox' || this.model.attributes[i]['type']=='radio' || this.model.attributes[i]['type']=='autocomplete'){

        this.valueChamps.label=this.model.attributes[i]['name']
        this.userService.addValue(this.valueChamps).subscribe(data => {

        })

    }
    this.champs.valueChp=this.valueChamps
    this.userService.addChamps(this.champs).subscribe(data => {     
      this.notification.info(data.description);
    })

   }

 }
 genererFormulaire(id){
  this.loading=true;
   this.userService.genererFormulaire("frontrdc",id).subscribe(data=>{
    this.loading = false;
    this.notification.info(data.description);
    this.listeFormulairenotgenerer();
    this.listeFormulairegenerer();
   })
 }

 openDialogViewForm(idFormulaire): void {
  const dialog = this.dialogRef.open(ViewFormComponent, {
    disableClose: true,
    width: '700px',
    data: idFormulaire
  }).afterClosed().subscribe(result => {
    //location.reload();

  });

}
openDialogEditForm(idFormulaire): void {
  const dialog = this.dialogRef.open(EditFormComponent, {
    disableClose: true,
    width: '700px',
    data: idFormulaire
  }).afterClosed().subscribe(result => {
    //location.reload();

  });

}

}
