import { Component, OnInit, NgZone } from '@angular/core';
//import { EmployeeService } from '../../services/employee.service';
import { Observable } from 'rxjs';
//import { Employee } from '../../models/employee';
import { Router } from '@angular/router';
import { RoleService } from 'src/app/utilisateur/services/role.service';
import { AuthService } from 'src/app/utilisateur/services/auth.service';
import { NavItem } from './nav-item';
import { Menu } from 'src/app/utilisateur/models/menu';
const SMALL_WIDTH_BREAKPOINT = 720;

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.scss']
})
export class SidenavComponent implements OnInit {
  private mediaMatcher: MediaQueryList = matchMedia(`(max-width: ${SMALL_WIDTH_BREAKPOINT}px)`);
  //employees: Observable<Employee[]>;
  isIndigoTheme: boolean = false;
  menus:any;
  retreivedLogo:any;
  dir: string = sessionStorage.getItem("dir") ? sessionStorage.getItem("dir"):'ltr';
  constructor(zone: NgZone, private router: Router, private authService:AuthService) {
    this.mediaMatcher.addListener(mql =>
      zone.run(() => this.mediaMatcher = matchMedia(`(max-width: ${SMALL_WIDTH_BREAKPOINT}px)`)));              
   }


  ngOnInit() {
    this.retreivedLogo = localStorage.getItem('logo');  
    if(!sessionStorage.getItem('menus')){
      this.authService.getMenusProfil(localStorage.getItem('profile')).subscribe( data => {      
      this.menus = data.data;

     // alert(JSON.stringify(this.menus));
      //console.log(JSON.stringify(this.menus));
      sessionStorage.setItem('menus',JSON.stringify(this.menus));      
    }); 
    }else{     
       this.menus = JSON.parse(sessionStorage.getItem('menus'));
    } 
    
  }

  ngAfterViewInit(){    
  }   

  isScreenSmall(): boolean{
    return this.mediaMatcher.matches;
  }

  toggleDir(){
    this.dir = this.dir == 'ltr' ? 'rtl' : 'ltr';
    sessionStorage.setItem("dir", this.dir);
  }
  }
