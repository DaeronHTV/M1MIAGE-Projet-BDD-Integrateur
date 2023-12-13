import { AppRoutingEnum } from './../../app-routing.enum';
import { Router } from '@angular/router';
import { CompteInterface } from './../../interface/firebdd';
import { FireBddService } from './../../service/fire-bdd.service';
import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AuthService } from 'src/app/service/auth.service';
import { VisioPadAPI } from 'src/app/service/visioPadAPI.service';
import { formatDate } from '@angular/common';

@Component({
  selector: 'app-portail-famille-sign-in',
  templateUrl: './portail-famille-sign-in.component.html',
  styleUrls: ['./portail-famille-sign-in.component.scss']
})
export class PortailFamilleSignInComponent {

  user: CompteInterface = null;
  constructor(public authService: AuthService, private fireBddService: FireBddService, private router: Router, private api: VisioPadAPI) { 
    this.authService.user$.subscribe( async (u) => {
      if (u != null){
        this.user = await this.fireBddService.getdbClient({mail: u.mail});
        if(this.user?.mail != null ) {
          const message = "Vous êtes déjà inscrit" + (this.user?.verified ? "" : ", attendez d'être valider par un membre du personnel :)");
          alert(message); 
          this.router.navigate([AppRoutingEnum.Home]);
        }
      }
    });
  }

  submit(loginForm: NgForm) {
    if(loginForm.valid) { 
      const value = loginForm.control.getRawValue();

      this.fireBddService.saveClient({ 
        mail: value?.email, 
        nom: value?.name, 
        prenom: value?.firstName, 
        telephone: value?.phone,
        verified: false,
        resident: {
          nom: value?.nomResident,
          prenom: value?.prenomResident,
          dateNaissance: formatDate(value?.dateNaissance, "yyyy-MM-dd", "fr"),
          etablissement: value?.etablissementResident,
        }
      });
      
      this.api.mailCreateAccount({
        mail: value.email,
        nom: value.name,
        prenom: value.name
      });
      this.router.navigate([AppRoutingEnum.Home]);
    }
  }

  loginTwitter() {
    this.authService.loginTwitter();
  }
  
  loginGoogle() {
    this.authService.loginGoogle();
  }

}
