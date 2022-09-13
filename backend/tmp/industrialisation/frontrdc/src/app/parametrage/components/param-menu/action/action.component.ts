import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { MatTableDataSource } from '@angular/material';
import { MenuService } from '../../../services/menu.service';
import { Action } from '../../../models/action';

@Component({
  selector: 'app-action',
  templateUrl: './action.component.html',
  styleUrls: ['./action.component.scss']
})
export class ActionComponent implements OnInit {
 actions:Action[];
 dataSource: MatTableDataSource<Action>;
 displayedColumns: string[] = ['actCode', 'actNom', 'actDescription'];
  constructor(private readonly translate: TranslateService, private menuService: MenuService) { }

  ngOnInit() {
    this.menuService.currentActions.subscribe(actions=>this.actions = actions);
    alert("--------------------message reçu------------"+JSON.stringify(this.actions));
    this.dataSource = new MatTableDataSource<Action>(this.actions);
  }

}
