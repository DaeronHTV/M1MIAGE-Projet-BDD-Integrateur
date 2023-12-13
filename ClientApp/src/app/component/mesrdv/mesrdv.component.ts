import { Component, OnInit } from '@angular/core';
import { first } from 'rxjs/operators';
import { AuthService } from 'src/app/service/auth.service';
import { AppRoutingEnum } from './../../app-routing.enum';
import { VisioPadAPI } from 'src/app/service/visioPadAPI.service';
import { rendezVousInterface, InvitesInterface } from 'src/app/interface/rendezVous';

@Component({
  selector: 'app-mesrdv',
  templateUrl: './mesrdv.component.html',
  styleUrls: ['./mesrdv.component.scss']
})
export class MesRendezVousComponent implements OnInit {

  rendezvous: rendezVousInterface[] = [];
  invites = true;
  AppRoutingEnum = AppRoutingEnum;
  presencerdv: boolean = false;
  presinviteRDV: boolean = false;

  constructor(public authService: AuthService, private visioPadAPI: VisioPadAPI) {
    this.LoadRendezVous();
    this.presenceRDV();
  }

  ngOnInit(): void {
  }

  private LoadRendezVous = async () => {
    this.authService.user$.pipe(first()).subscribe(async u => {
      if (u?.mail) {
        let reponse = await this.visioPadAPI.getRDVContact({ mailContact: u.mail });
        this.rendezvous = JSON.parse(reponse);
        
      }
      console.log("rdv : " + this.rendezvous);
    });
  };

  private presenceRDV() {
    if (this.rendezvous.length > 0) {
      this.presencerdv = true;
      console.log("présence rdv? " + this.presencerdv);
    } else {
      this.presencerdv = false;
      console.log("présence rdv? " + this.presencerdv);
    }
  }

  private invitesRDV(i: number) {
    if (this.rendezvous[i]?.Invites?.length > 0) {
      this.presinviteRDV = true;
      console.log("présence invite rdv? " + this.presinviteRDV);
    } else {
      this.presinviteRDV = false;
      console.log("présence invite rdv? " + this.presinviteRDV);
    }
  }

  etatProgramme(i: number) {
    return this.rendezvous[i].etatRDV === "PROGRAMME";
  }

  etatValide(i: number) {
    return this.rendezvous[i].etatRDV === "VALIDE";
  }
  etatRefuse(i: number) {
    return this.rendezvous[i].etatRDV === "REFUSE";
  }
  etatTermine(i: number) {
    return this.rendezvous[i].etatRDV === "TERMINE";
  }

}

