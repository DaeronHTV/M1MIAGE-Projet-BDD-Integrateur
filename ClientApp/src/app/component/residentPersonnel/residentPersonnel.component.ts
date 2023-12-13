import { Component, OnInit } from '@angular/core';
import {animate, state, style, transition, trigger} from '@angular/animations';
import { first } from 'rxjs/operators';
import { AuthService } from 'src/app/service/auth.service';
import { VisioPadAPI } from 'src/app/service/visioPadAPI.service';

@Component({
  selector: 'app-residentPersonnel',
  templateUrl: './residentPersonnel.component.html',
  styleUrls: ['./residentPersonnel.component.scss'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})

export class ResidentPersonnelComponent implements OnInit {

  columnsToDisplay : string[] = ['nom', 'prenom', 'dateNaissance', 'statut', 'uniteAff'];
  residents: any;

  constructor(public authService: AuthService, private visioPadAPI: VisioPadAPI) { 
    this.LoadResidents();
  }

  ngOnInit(): void {
  }

  private LoadResidents = async () => {
    this.authService.user$.pipe(first()).subscribe(async u => {
      console.log("user : " + u.mail);
      if (u?.mail) {
        let reponse = await this.visioPadAPI.getResidentsPersonnel({ contactMail: u.mail });
        this.residents = JSON.parse(reponse);
        console.log("res :" + this.residents);
      }
    });
  };
}
