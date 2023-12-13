import { AppRoutingEnum } from './../../app-routing.enum';
import { Component } from '@angular/core';
import { AuthService } from 'src/app/service/auth.service';
import { FireBddService } from 'src/app/service/fire-bdd.service';
import { CompteInterface } from 'src/app/interface/firebdd';
import { Router } from '@angular/router';

@Component({
  selector: 'app-choisir-portail',
  templateUrl: './choisir-portail.component.html',
  styleUrls: ['./choisir-portail.component.scss']
})
export class ChoisirPortailComponent {

  AppRoutingEnum = AppRoutingEnum;
  user: CompteInterface = null;
  constructor(public authService: AuthService, private fireBddService: FireBddService, private router: Router) {
    this.authService.user$.subscribe( async (u) => {
      if (u != null){
        this.user = await this.fireBddService.getdbClient({mail: u.mail});
      }
    })
  }

}
