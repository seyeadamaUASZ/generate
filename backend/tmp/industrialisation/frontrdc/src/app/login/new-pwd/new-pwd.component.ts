import { Component, OnInit } from '@angular/core';
import { Validators, FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ResetpwdService } from '../services/resetpwd.service';

@Component({
  selector: 'app-new-pwd',
  templateUrl: './new-pwd.component.html',
  styleUrls: ['./new-pwd.component.scss']
})
export class NewPwdComponent implements OnInit {
 message:any="";

  constructor(private route: ActivatedRoute, private router: Router,private resetpwd: ResetpwdService) {
      
  }

  ngOnInit() {
  let reset = this.route.snapshot.params.reset;
    console.log(reset);
    
  this.resetpwd.verifToken(reset).subscribe( data => {


          //window.alert(data.description);        
         //this.message = data.description;                 
    //window.alert(data.description);        



          this.message = "";


        }); 
    
  }

 


  
}
