import { HttpClient, HttpHeaders, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ResidentInterface } from '../interface/personne';

@Injectable({
  providedIn: 'root'
})
export class VisioPadAPI {

  constructor(private http : HttpClient) { }

  async mailCreateAccount(params: {[key: string]: any}) : Promise<string> {
    let response = await this.POST('/api/mail/create', params);
    console.log(response);
    return response.body;
  }

  async mailContact(params: {[key: string]: any}) : Promise<string> {
    let response = await this.POST('/api/mail/contact', params);
    console.log(response);
    return response.body;
  }

  async getResidentsContact(params: {[key: string]: any}) : Promise<string> {
    let response = await this.POST('/api/residentsContact', params);
    console.log(response);
    return response.body;
  }

  async getCreneauRDV(params: {[key: string]: any}) : Promise<string> {
    let response = await this.POST('/api/getCreneauRDV', params);
    console.log(response);
    return response.body;
  }

  async enregistrerRendezVous(params: {[key: string]: any}) : Promise<string> {
    let response = await this.POST('/api/enregistrerRdv', params);
    console.log(response);
    return response.body;
  }

  async validerRendezVous(params: {[key: string]: any}) : Promise<string> {
    let response = await this.POST('/api/validerRendezVous', params);
    console.log(response);
    return response.body;
  }

  async annulerRendezVous(params: {[key: string]: any}) : Promise<string> {
    let response = await this.POST('/api/annulerRendezVous', params);
    console.log(response);
    return response.body;
  }

  async creerPersonnel(params: {[key: string]: any}) : Promise<string> {
    let response = await this.POST('/api/creerPersonnel', params);
    console.log(response);
    return response.body;
  }

  async creerContact(params: {[key: string]: any}) : Promise<string> {
    let response = await this.POST('/api/creerContact', params);
    console.log(response);
    return response.body;
  }

  async getPlanningPersonnel(params: {[key: string]: any}) : Promise<string> {
    let response = await this.POST('/api/planningPersonnel', params);
    console.log(response);
    return response.body;
  }

  async getRDVContact(params: {[key: string]: any}) : Promise<string> {
    let response = await this.POST('/api/rdvContactServlet', params);
    console.log(response);
    return response.body;
  }

  async getCreneauPersonnel(params: {[key: string]: any}) : Promise<string> {
    let response = await this.POST('/api/creneauxPersonnel', params);
    console.log(response);
    return response.body;
  }

  async creerCreneau(params: {[key: string]: any}) : Promise<string> {
    let response = await this.POST('/api/creerCreneau', params);
    console.log(response);
    return response.body;
  }

  async getResidentsPersonnel(params: {[key: string]: any}) : Promise<string> {
    let response = await this.POST('/api/residentsPersonnel', params);
    console.log(response);
    return response.body;
  }

  async existeResident(params: {[key: string]: any}) : Promise<string> {
    let response = await this.POST('/api/resident', params);
    console.log(response);
    return response.body;
  }

  private POST(url: string, params: {[key: string]: any}): Promise<HttpResponse<string>> {
    const P = new HttpParams( {fromObject: params} );
    return this.http.post( url, P, {
      observe: 'response',
      responseType: 'text',
      headers: {'content-type': 'application/x-www-form-urlencoded'}
    }).toPromise();
  }

  private GET(url: string, params: {[key: string]: any}): Promise<HttpResponse<string>> {
    const headers = new HttpHeaders().set('Content-Type', 'application/json ; charset=UTF-8');
    return this.http.get<any>(url, { responseType: 'json', headers: headers, observe: 'response' }).toPromise();
  }

}
