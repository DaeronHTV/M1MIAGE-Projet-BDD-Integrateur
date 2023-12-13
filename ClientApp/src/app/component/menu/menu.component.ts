import { CompteInterface } from 'src/app/interface/firebdd';
import { FireBddService } from './../../service/fire-bdd.service';
import { AppRoutingEnum } from './../../app-routing.enum';
import { Component } from '@angular/core';
import { AuthService } from 'src/app/service/auth.service';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent {

  AppRoutingEnum = AppRoutingEnum;
  contact: CompteInterface = null;

  constructor(public authService: AuthService, private fireBddService: FireBddService) { 
    this.authService.user$.pipe(first()).subscribe(async u => {
      if(u?.mail){
        this.contact = await this.fireBddService.getdbClient({mail: u.mail});
      }
    })
    
  }

}
