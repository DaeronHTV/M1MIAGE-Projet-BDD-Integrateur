import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AccueilComponent } from './component/accueil/accueil.component';
import { DonneespersoComponent } from './component/reglement/donneesperso/donneesperso.component';
import { ContactComponent } from './component/contact/contact.component';
import { RendezVousComponent } from './component/rendezvous/rendezvous.component';
import { MesRendezVousComponent } from './component/mesrdv/mesrdv.component';
import { AideComponent } from './component/aide/aide.component';
import { ModifProfilComponent } from './component/modifProfil/modifProfil.component';
import { PlanningPersonnelComponent } from './component/planningPersonnel/planningPersonnel.component';
import { AppRoutingEnum } from './app-routing.enum';
import { ChoisirPortailComponent } from './component/choisir-portail/choisir-portail.component';
import { PortailPersonnelSignInComponent } from './component/portail-personnel-sign-in/portail-personnel-sign-in.component';
import { PortailPersonnelLoginComponent } from './component/portail-personnel-login/portail-personnel-login.component';
import { PortailFamilleSignInComponent } from './component/portail-famille-sign-in/portail-famille-sign-in.component';
import { PortailFamilleLoginComponent } from './component/portail-famille-login/portail-famille-login.component';
import { ResidentPersonnelComponent } from './component/residentPersonnel/residentPersonnel.component';
import { ListContactsComponent } from './component/list-contacts/list-contacts.component';
import { MentionslegalesComponent } from './component/reglement/mentionslegales/mentionslegales.component';

const routes: Routes = [
  {path: AppRoutingEnum.Home, component: AccueilComponent},
  //{path: 'mentions_legales', component: MentionslegalesComponent},
  {path: AppRoutingEnum.MyRendezvous, component:MesRendezVousComponent},
  {path: AppRoutingEnum.EditProfil, component:ModifProfilComponent},
  {path: AppRoutingEnum.PersonalData, component: DonneespersoComponent},
  {path: AppRoutingEnum.Contact, component:ContactComponent},
  {path: AppRoutingEnum.Rendezvous, component:RendezVousComponent},
  {path: AppRoutingEnum.Help, component:AideComponent},
  {path: AppRoutingEnum.PlanningPersonnel, component:PlanningPersonnelComponent},
  {path: AppRoutingEnum.ChoosePortal, component:ChoisirPortailComponent},
  {path: AppRoutingEnum.PortalPersonnelSignIn, component:PortailPersonnelSignInComponent},
  {path: AppRoutingEnum.PortalPersonnelLogin, component:PortailPersonnelLoginComponent},
  {path: AppRoutingEnum.PortalFamilleSignIn, component:PortailFamilleSignInComponent},
  {path: AppRoutingEnum.PortalFamilleLogin, component:PortailFamilleLoginComponent},
  {path: AppRoutingEnum.ResidentPersonnel, component:ResidentPersonnelComponent},
  {path: AppRoutingEnum.ListContacts, component:ListContactsComponent},
  {path: AppRoutingEnum.MentionsLegales, component: MentionslegalesComponent},
  {path: AppRoutingEnum.DonneesPerso, component: DonneespersoComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
