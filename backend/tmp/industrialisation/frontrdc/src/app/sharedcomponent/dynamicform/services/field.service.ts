import { Injectable }       from '@angular/core';
import { HttpClient } from '@angular/common/http'; 
import { Observable } from 'rxjs';
import { DropdownField } from '../models/field-dropdown';
import { FieldBase }     from '../models/field-base';
import { TextboxField }  from '../models/field-textbox';
import { DateField }  from '../models/field-date';
import { of } from 'rxjs';

@Injectable()
export class FieldService {
  constructor(private http: HttpClient) {
        
    }
  buildField(field:FieldBase<string>):any{
     switch(field.type) {
                case "dropdown": {                                 
                    return new DropdownField({
                            key: field.key,
                            label: field.label,
                            type: field.type,
                            options: [
                            {key: 'solid',  value: 'Solid'},
                            {key: 'great',  value: 'Great'},
                            {key: 'good',   value: 'Good'},
                            {key: 'unproven', value: 'Unproven'}
                            ],
                            order: 3
                        })                  
                } 
                case "date": {                   
                     return   new DateField({
                        key: field.key,
                        label: field.label,
                        type: field.type,
                        order: 4
                    }) ;
                   
                } 
                case "email": {                     
                    return new TextboxField({
                        key: field.key,
                        label: field.label,
                        type: field.type,
                        order: 2
                    }) ;
                } 
                case "text": {                     
                     return  new TextboxField({
                        key: field.key,
                        label: field.label,
                        type: field.type,
                        value: '',
                        required: field.required,
                        order: 1
                    }) ;
                } 
                default: { 
                    //statements; 
                    break; 
                } 
     }
  }
 /* getJSON(): Observable<any> {
        return this.http.get("./assets/formfields.json");
  }
  // TODO: get from a remote source of field metadata
  public getFields():any {
    return this.getJSON();
    
}*/
}