import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
/*import firebase*/
import { AngularFireModule } from '@angular/fire';
import { AngularFireAnalyticsModule } from '@angular/fire/analytics';
import { AngularFirestoreModule } from '@angular/fire/firestore';
import { AngularFireAuthModule } from '@angular/fire/auth';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { environment } from 'src/environments/environment';
import { MenuComponent } from './component/menu/menu.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule } from '@angular/common/http';

import { AccueilComponent } from './component/accueil/accueil.component';
//import { MentionslegalesComponent } from './component/reglement/mentionslegales/mentionslegales.component';
import { DonneespersoComponent } from './component/reglement/donneesperso/donneesperso.component';
import { ContactComponent } from './component/contact/contact.component';
import { RendezVousComponent } from './component/rendezvous/rendezvous.component';
import { MesRendezVousComponent } from './component/mesrdv/mesrdv.component';
import { AideComponent } from './component/aide/aide.component';
import { ModifProfilComponent } from './component/modifProfil/modifProfil.component';
import { PlanningPersonnelComponent } from './component/planningPersonnel/planningPersonnel.component';
import { ChoisirPortailComponent } from './component/choisir-portail/choisir-portail.component';
import { PortailPersonnelSignInComponent } from './component/portail-personnel-sign-in/portail-personnel-sign-in.component';
import { PortailFamilleSignInComponent } from './component/portail-famille-sign-in/portail-famille-sign-in.component';
import { PortailFamilleLoginComponent } from './component/portail-famille-login/portail-famille-login.component';
import { PortailPersonnelLoginComponent } from './component/portail-personnel-login/portail-personnel-login.component';
import { ResidentPersonnelComponent } from './component/residentPersonnel/residentPersonnel.component';
import { ListContactsComponent } from './component/list-contacts/list-contacts.component';

/*import matérial angular*/
import { MatCardModule } from '@angular/material/card';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatStepperModule } from '@angular/material/stepper';
import { MatFormFieldModule } from '@angular/material/form-field';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule, MAT_DATE_LOCALE } from '@angular/material/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatDividerModule } from '@angular/material/divider';
import { MatListModule } from '@angular/material/list';
import { MatTableModule } from '@angular/material/table';
import { registerLocaleData } from '@angular/common';
import localeFr from '@angular/common/locales/fr';

registerLocaleData(localeFr, 'fr');
@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    AccueilComponent,
    //MentionslegalesComponent,
    DonneespersoComponent,
    ContactComponent,
    RendezVousComponent,
    MesRendezVousComponent,
    AideComponent,
    ModifProfilComponent,
    PlanningPersonnelComponent,
    ChoisirPortailComponent,
    PortailPersonnelSignInComponent,
    PortailFamilleSignInComponent,
    PortailFamilleLoginComponent,
    PortailPersonnelLoginComponent,
    ResidentPersonnelComponent,
    ListContactsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AngularFireModule.initializeApp(environment.firebaseConfig, 'BDDIntegrateurASRM'),
    AngularFireAuthModule,
    AngularFireAnalyticsModule,
    AngularFirestoreModule,
    BrowserAnimationsModule,
    NgbModule,
    MatCardModule,
    MatGridListModule,
    MatStepperModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatInputModule,
    FormsModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatButtonModule,
    MatIconModule,
    MatSlideToggleModule,
    MatDividerModule,
    MatListModule,
    HttpClientModule,
    MatTableModule
  ],
  providers: [{ provide: MAT_DATE_LOCALE, useValue: 'fr-FR' }],
  bootstrap: [AppComponent]
})
export class AppModule { }
