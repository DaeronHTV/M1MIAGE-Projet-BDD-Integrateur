import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AppRoutingEnum } from 'src/app/app-routing.enum';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-portail-famille-login',
  templateUrl: './portail-famille-login.component.html',
  styleUrls: ['./portail-famille-login.component.scss']
})
export class PortailFamilleLoginComponent {

  constructor(private authService: AuthService, private router: Router) { }

  async loginTwitter() {
    const u = await this.authService.loginTwitter();
    if (u){
      this.router.navigate([AppRoutingEnum.Home]);
    }
  }
  
  async loginGoogle() {
    const u = await this.authService.loginGoogle();
    if (u){
      this.router.navigate([AppRoutingEnum.Home]);
    }
  }
}
