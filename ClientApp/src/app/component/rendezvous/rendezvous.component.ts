import { Component, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, Validators, FormControl, NgForm } from '@angular/forms';
import { DateAdapter } from '@angular/material/core';
import { AuthService } from 'src/app/service/auth.service';
import { Router } from '@angular/router';
import { AppRoutingEnum } from './../../app-routing.enum'
import { VisioPadAPI } from 'src/app/service/visioPadAPI.service';
import { MatStepper } from '@angular/material/stepper';
import { first } from 'rxjs/operators';
import { CompteInterface } from 'src/app/interface/firebdd';

@Component({
  selector: 'app-rendezvous',
  templateUrl: './rendezvous.component.html',
  styleUrls: ['./rendezvous.component.scss']
})
export class RendezVousComponent {

  AppRoutingEnum = AppRoutingEnum;
  date: Date;
  dateMin: Date;
  dateMax: Date;
  FormGroup1: FormGroup;
  FormGroup2: FormGroup;
  residents: any;
  creneauPersonnelResident: any = [];
  selectedResident: string;
  selectedDate: string;
  selectedHeure: string;
  heureDispoDate: any = [];
  userConnected: CompteInterface;
  selectedCreneau: any;
  checked = false;
  
user = new FormGroup({
  invite: new FormArray([])
});

invite = this.user.get('invite') as FormArray;
@ViewChild("stepper") monStepper : MatStepper;

  constructor(public authService: AuthService, private _formBuilder: FormBuilder,private _adapter: DateAdapter<any>, private visioPadAPI : VisioPadAPI,  private router: Router) {
    this.LoadResidents();
    this.dateMin = new Date();
    this.dateMax = new Date();
    this.dateMax.setDate(this.dateMin.getDate() + 70);

    this.FormGroup1 = this._formBuilder.group({
      nomResident: ['', Validators.required]
    });
    this.FormGroup2 = this._formBuilder.group({
      date: ['', Validators.required],
      heure: ['', Validators.required]
    });
  }

  private LoadResidents = async () => {
    this.authService.user$.pipe(first()).subscribe(async u => {
      if (u?.mail) {
        this.userConnected = u;        
        console.log(this.userConnected.mail);
        let reponse = await this.visioPadAPI.getResidentsContact({contactMail : this.userConnected.mail});
        this.residents = JSON.parse(reponse);
      }
    });
    console.log("res : " + this.residents);
  };

  addInvite(){
    this.invite.push(new FormGroup({
      nom: new FormControl('', Validators.required),
      prenom: new FormControl('', Validators.required),
      email: new FormControl('', [Validators.email, Validators.required])
    }));
  }

  deleteInvite(i: number): void{
    this.invite.removeAt(i);
  }

  submit(): void{
    console.log(this.user.valid);
    if(this.user.valid){
      console.log(this.user.value);
    }
  }

  async selectResident(event) {
    this.selectedResident = event.value;
    if(this.selectedResident){
      let response = await this.visioPadAPI.getCreneauPersonnel({idResident : this.selectedResident});
      let creneauPersonnel = JSON.parse(response);
      console.log("cr per : " + creneauPersonnel);
      creneauPersonnel.forEach(data => {
        if(data.creneau.disponible == 1){
          let dateHeure = data.creneau.dateCreneau.split(" ");
          data.creneau.dateCreneau = {date: dateHeure[0], heure: dateHeure[1]}
          this.creneauPersonnelResident.push(data.creneau);
        }
      });
      console.log(this.creneauPersonnelResident[0].dateCreneau); // date du premier creneau sur le tableau
    }
  }

  getHeuresDispoDate() {
    this.creneauPersonnelResident.forEach(creneau => {
      if(creneau.dateCreneau.date == this.selectedDate) {
        this.heureDispoDate.push(creneau.dateCreneau.heure);
      }
      this.selectedHeure = this.heureDispoDate[0];
    });
    
  }

  selectDate(event) {
    this.heureDispoDate = [];
    this.selectedDate = event.value;
    this.getHeuresDispoDate();
  }

  selectHeure(event) {
    this.selectedHeure = event.value;
  }

  validerRdv() {
    this.selectedCreneau;
    this.creneauPersonnelResident.forEach(creneau => {
      if(creneau.dateCreneau.date === this.selectedDate && creneau.dateCreneau.heure === this.selectedHeure) {
        this.selectedCreneau = creneau;
      }
    });
    let invited = JSON.stringify(this.user.value.invite);
    this.visioPadAPI.enregistrerRendezVous({idResident: this.selectedResident, invites: invited, contactMail: this.userConnected.mail,
    idPersonnel: this.selectedCreneau.idPersonnel, dateCreneau: this.selectedCreneau.dateCreneau.date + ' ' + this.selectedCreneau.dateCreneau.heure, idCreneau: this.selectedCreneau.idCreneau})
    .then(() =>  this.router.navigate([AppRoutingEnum.MyRendezvous])).catch(()=> {alert("échec réservation rendez-vous, veuillez vérifier les informations saisies"); this.monStepper.reset()});
  }
}