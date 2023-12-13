import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { VisioPadAPI } from 'src/app/service/visioPadAPI.service';
import { IMailContact } from '../../interface/Mail';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.scss']
})
export class ContactComponent implements OnInit {
  date: Date = new Date();
  mail = new FormControl('', [Validators.required, Validators.email]);
  nom = new FormControl('', [Validators.required]);
  prenom = new FormControl('', [Validators.required]);
  message = new FormControl('', [Validators.required]);
  sujet = new FormControl('', [Validators.nullValidator]);

  constructor(private router: Router, private api: VisioPadAPI) { }

  ngOnInit(): void {
  }

  requiredError(controller: FormControl){
    if(controller.hasError('required')){
        return 'You must enter a value';
    }
  }

  validMailError(){
    if(this.mail.hasError('email')){
      return 'Insert a correct adress';
    }
  }

  async envoieMail() : Promise<void>{
    var mail: IMailContact = {
      nom: this.nom.value,
      prenom: this.prenom.value,
      mail: this.mail.value,
      date: this.dateNowToString(),
      sujet: this.sujet.value,
      message: this.message.value
    }
    console.log(mail);
    let result = await this.api.mailContact(mail);
    console.log(result);
  }

  /**
   * @public @method
   * @description Retourne à la page d'accueil
   * @since 03/02/2021
   */
  public retourAccueil() : void{ this.router.navigate(['/']); }

  /**
   * @private @method
   * @description Give the date at the instant t in the format
   * YYYY-MM-DD HH:mm:ss
   * @returns The Date at the moment where the method is called
   * @since 09/01/2021
   */
  private dateNowToString(): string{
    var day = this.date.getDate();
    var month = this.date.getMonth()+1; var hour = this.date.getHours();
    var minute = this.date.getMinutes(); var second = this.date.getSeconds();
    var result = ""+this.date.getFullYear()+"/";
    result += (day < 10) ? "0"+day : day;
    result += (month < 10) ? "/0"+month : "/"+month;
    result += (hour < 10) ? " 0"+hour : " "+hour;
    result += (minute < 10) ? ":0"+minute : ":"+minute;
    result += (second < 10) ? ":0"+second : ":"+second;
    return result;
  }

}
