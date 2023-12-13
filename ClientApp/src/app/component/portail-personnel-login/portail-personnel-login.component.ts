import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AppRoutingEnum } from 'src/app/app-routing.enum';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-portail-personnel-login',
  templateUrl: './portail-personnel-login.component.html',
  styleUrls: ['./portail-personnel-login.component.scss']
})
export class PortailPersonnelLoginComponent {

  constructor(private authService: AuthService, private router: Router) { }

  async submit(loginForm: NgForm) {
    if (loginForm.valid) {
      const u = await this.authService.loginEmail(loginForm.control.value.id, loginForm.control.value.password);
      if (u){
        this.router.navigate([AppRoutingEnum.Home]);
      }
    }
  }
}
