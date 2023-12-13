import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { first } from 'rxjs/operators';
import { AppRoutingEnum } from 'src/app/app-routing.enum';
import { CompteInterface } from 'src/app/interface/firebdd';
import { CreneauGenInterface, CreneauInterface } from 'src/app/interface/rendezVous';
import { AuthService } from 'src/app/service/auth.service';
import { VisioPadAPI } from 'src/app/service/visioPadAPI.service';

@Component({
  selector: 'app-planningPersonnel',
  templateUrl: './planningPersonnel.component.html',
  styleUrls: ['./planningPersonnel.component.scss']
})
export class PlanningPersonnelComponent implements OnInit {

  AppRoutingEnum = AppRoutingEnum;
  heures = ["00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"];
  minutes = ["00", "30"];
  invites = true;
  afficherCreerCreneau = false;
  date: Date;
  heure: Date;
  selectedHeure: string;
  selectedMin: string;
  dateMin: Date;
  dateMax: Date;
  FormGroup1: FormGroup;
  FormGroup2: FormGroup;
  dateDebutSemaine: Date;
  dateFinSemaine: Date;
  userConnected: CompteInterface;
  creneauRDV: CreneauGenInterface[] = [];
  creneauRDVSemaine: CreneauGenInterface[] = [];
  presenceCr: boolean = false;

  constructor(public authService: AuthService, private _formBuilder: FormBuilder, private visioPadAPI: VisioPadAPI, private router: Router) {
    this.LoadUser();
    this.dateMin = new Date();
    this.dateMax = new Date();
    this.dateMax.setDate(this.dateMin.getDate() + 70);

    this.dateDebutSemaine = new Date();
    this.dateFinSemaine = new Date();
    this.dateFinSemaine.setDate(this.dateDebutSemaine.getDate() + 7);

    this.LoadRDV();
    
    this.FormGroup1 = this._formBuilder.group({
      date: ['', Validators.required]
    });
    this.FormGroup2 = this._formBuilder.group({
      heure: ['', Validators.required],
      minute: ['', Validators.required]
    });
  }

  ngOnInit(): void {
  }

  private LoadRDV = async () => {
    this.authService.user$.pipe(first()).subscribe(async u => {
      if (u?.mail) {
        this.userConnected = u;
        let reponse = await this.visioPadAPI.getCreneauRDV({ mailPersonnel: u.mail });
        let creneaux = JSON.parse(reponse);
        this.creneauRDV = this.formatResponse(creneaux);
      }
      this.creneauSemaine();
    });
  };

  private formatResponse(creneaux : any) : CreneauGenInterface[] {
    let creneauxForm : CreneauGenInterface[] = creneaux;
    creneaux.forEach((c,index) => { console.log(c.creneau);
      let dateResponse = c.creneau.dateCreneau.split("-");
      console.log("resp : " + dateResponse);
      creneauxForm[index].creneau.dateCreneau = new Date(dateResponse[2], dateResponse[1]-1, dateResponse[0]);
      creneauxForm[index].creneau.dureeCreneau = Number(c.creneau.dureeCreneau);
      creneauxForm[index].creneau.heureCreneau = c.creneau.heureCreneau;
      creneauxForm[index].creneau.idCreneau = c.creneau.idCreneau;
      creneauxForm[index].creneau.idPersonnel = c.creneau.idPersonnel;
      creneauxForm[index].creneau.dateCreneau = c.creneau.dateCreneau;
      creneauxForm[index].creneau.disponisble = Number(c.creneau.disponisble);
    }); 
    return creneauxForm;
  }

  private LoadUser = async () => {
    this.authService.user$.pipe(first()).subscribe(async u => {
      if (u?.mail) {
        this.userConnected = u;
      }
    });
  };

  presenceCreneau() {
    if (this.creneauRDV.length > 0) {
      return true;
    } else {
      return false;
    }
  }

  creneauDisponible(i: number) {
    return this.creneauRDV[i].creneau.disponisble === 1;
  }

  creerCreneau() {
    this.afficherCreerCreneau = !this.afficherCreerCreneau;
  }

  semainePrecedente() {
    if (this.dateDebutSemaine > this.dateMin) {
      this.dateDebutSemaine = new Date(this.dateDebutSemaine.getFullYear(), this.dateDebutSemaine.getMonth(), this.dateDebutSemaine.getDate() - 8);
      this.dateFinSemaine = new Date(this.dateFinSemaine.getFullYear(), this.dateFinSemaine.getMonth(), this.dateFinSemaine.getDate() - 8);
    }
    this.creneauSemaine();
  }

  semaineSuivante() {
    if (this.dateFinSemaine < this.dateMax) {
      this.dateDebutSemaine = new Date(this.dateDebutSemaine.getFullYear(), this.dateDebutSemaine.getMonth(), this.dateDebutSemaine.getDate() + 8);
      this.dateFinSemaine = new Date(this.dateFinSemaine.getFullYear(), this.dateFinSemaine.getMonth(), this.dateFinSemaine.getDate() + 8);
    }
    this.creneauSemaine();
  }

  creneauSemaine() {
    this.creneauRDVSemaine = [];
    this.creneauRDV.forEach((creneauRDV) => {
      if ((creneauRDV.creneau.dateCreneau.getTime() >= this.dateDebutSemaine.getTime()) && (creneauRDV.creneau.dateCreneau.getTime() <= this.dateFinSemaine.getTime())) {
        this.creneauRDVSemaine.push(creneauRDV);
      }
    });
    console.log("rdv semaine : " + this.creneauRDVSemaine);
  }

  selectHeure(event) {
    this.selectedHeure = event.value;
  }

  selectMin(event) {
    this.selectedMin = event.value;
  }

  validerCreneau() {
    console.log(this.FormGroup1.value.date);
    let dateSelected = this.FormGroup1.value.date.split('-');
    let year = dateSelected[0];
    let month = dateSelected[1]
    let day = dateSelected[2]
    let datePicked = day + '-' + month + '-' + year;
    let heurePicked = this.FormGroup2.value.heure
    let minPicked = this.FormGroup2.value.minute + ':00'
    let dateCreneau = datePicked + ' ' + heurePicked + ':' + minPicked;
    this.visioPadAPI.creerCreneau({ mailPersonnel: this.userConnected.mail, dateCreneau: dateCreneau })
      .then(() => this.router.navigate([AppRoutingEnum.Home]))
      .catch(() => { alert("échec création creneau, veuillez essayer de nouveau s'il vous plait"); this.router.navigate([AppRoutingEnum.Home]) });
  }

  validerRendezVous(idCreneau) {
    this.visioPadAPI.validerRendezVous({idCreneau: idCreneau}).then(() => this.router.navigate([AppRoutingEnum.Home]));
  }

  annulerRendezVous(idCreneau) {
    this.visioPadAPI.annulerRendezVous({idCreneau: idCreneau}).then(() => this.router.navigate([AppRoutingEnum.Home]));
  }
}