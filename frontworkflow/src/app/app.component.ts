import { Component } from '@angular/core';
import { ApiService } from './api/api.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  displayedColumns: string[] = ['container-id', 'container-name', 'status','action'];
    result:any=[];
    dataSource:any;
    public constructor(private servie:ApiService){
        this.getContainerId()
    }

    getContainerId(){
      this.servie.getContainer().subscribe(res=>{
        this.result=res
        this.dataSource=res;
          console.log(this.result)
      })
    }

   /* generer(data){
      this.servie.genererProjet(data).subscribe(res=>{
        console.log(res)
      })
    }*/
    genererProjet(data1,data2){
      this.servie.genererProjet(data1,data2).subscribe(res=>{
        console.log(res)
      })
    }
}
