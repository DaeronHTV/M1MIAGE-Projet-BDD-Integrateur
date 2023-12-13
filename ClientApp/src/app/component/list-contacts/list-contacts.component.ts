import { VisioPadAPI } from 'src/app/service/visioPadAPI.service';
import { CompteInterface } from 'src/app/interface/firebdd';
import { FireBddService } from 'src/app/service/fire-bdd.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-list-contacts',
  templateUrl: './list-contacts.component.html',
  styleUrls: ['./list-contacts.component.scss']
})
export class ListContactsComponent {

  listUsers: CompteInterface[] = [];
  users: any;

  constructor(private fireBddService: FireBddService, private visioPadAPI: VisioPadAPI) { 
    this.loadUsers();
  }

  async loadUsers(){
    this.users = await this.fireBddService.getdbListClient({ mail: null, verified: false });
    let result : CompteInterface[] = await Promise.all(this.users.map(async (u): Promise<CompteInterface> => {
      let exist = await this.visioPadAPI.existeResident(u.resident);
      if(exist == "false"){
        u.resident = null;
      }
      return u;
    }));
    this.listUsers = result;
  }
  
  valider(i: number) {
    const u = this.listUsers[i];
    u.verified = true;
    this.fireBddService.updateClient(u, this.listUsers[i]);
    this.visioPadAPI.creerContact({
      mail: u.mail,
      nom: u.nom,
      prenom: u.prenom,
      telephone: u.telephone,
      verified: u.verified,
      resident: u?.resident != null ? JSON.stringify(u.resident) : null
    });
    this.listUsers.splice(i, 1);
  }

  supprimer(i: number) {
    this.fireBddService.deletedbClient(this.listUsers[i]);
    this.listUsers.splice(i, 1);
  }

}
