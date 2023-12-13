import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AppRoutingEnum } from 'src/app/app-routing.enum';
import { PersonnelInterface } from 'src/app/interface/personne';
import { AuthService } from 'src/app/service/auth.service';
import { VisioPadAPI } from 'src/app/service/visioPadAPI.service';

@Component({
  selector: 'app-portail-personnel-sign-in',
  templateUrl: './portail-personnel-sign-in.component.html',
  styleUrls: ['./portail-personnel-sign-in.component.scss']
})
export class PortailPersonnelSignInComponent {

  messageError: string = null;

  constructor(private authService: AuthService, private router: Router, private visioPadAPI: VisioPadAPI) {
  }
  
  async submit(registerForm: NgForm) {
    let values = registerForm.control.value;
    this.authService.createLoginEmail(values?.email, values?.password).then((message)=> {
      this.messageError = message;
      if (registerForm.valid && this.messageError == null){
        let personnel: PersonnelInterface = {
          nom: values.name,
          prenom: values.firstName,
          adresseMail: values.email,
          numeroTelephone: values.phone,
          fonction: values?.job
        }
        this.visioPadAPI.creerPersonnel(personnel)
          .then(() =>  this.router.navigate([AppRoutingEnum.Home]))
            .catch(()=> {alert("échec création compte, veuillez vérifier les informations saisies"); registerForm.reset()});
      }
    })
  }

}
