import { Injectable } from '@angular/core';
import { AngularFirestore, AngularFirestoreCollection } from '@angular/fire/firestore';
import { CompteInterface, QueryCompte } from '../interface/firebdd';
import {Guid} from 'guid-typescript';

/**
 * @public 
 * @service Service permettant l'accès à la base de données Firebase
 * @author Avanzino Aurélien
 * @since 25/01/2021
 * @version 1.0
 */
@Injectable({
  providedIn: 'root'
})
export class FireBddService {
  private date: Date = new Date();
  private firestoreComptes: AngularFirestoreCollection<CompteInterface>;

  /**
   * @constructor Initialise l'accès à la base de données firebase
   * @param firestore - Permet la manipulation du cloud firestore
   * @since 27/01/2021
   */
  constructor(private firestore: AngularFirestore) {this.firestoreComptes = this.firestore.collection('/Comptes');}

  /**********************FONCTION D'ENREGISTREMENT EN BDD*********************/
  //#region Ancienne Implementation
  /*public saveClient(mail: string, prenom?: string, nom?: string): Promise<boolean>{
    console.log("Le client n'existe pas. Enregistrement dans la BDD !");
    var idclient = this.firestore.createId();
    var clientBdd = {
      id: idclient,
      prenom: prenom,
      nom: nom,
      mail: mail,
      telephone: null,
      verified: false,
      profilePicture: null,
      dateCreation: this.dateNowToString()
    }
    var client = this.firestoreComptes.doc<Compte>(idclient);
    return client.set(clientBdd).then((sucess)=>{return true}, (rejected)=>{return false});
  }*/
  //#endregion

  //#region Nouvelle Implémentation
  /**
   * @public @async
   * @method Sauvegarde le compte dans le cloud
   * @param compte Le compte a sauvegardé dans le cloud Firestore
   * @return Une promesse de booleen qui indiquera si l'enregistrement
   * c'est correctement effectué ou non
   * @since 28/01/2021
   */
  public async saveClient(compte: CompteInterface): Promise<boolean>{
    console.log("Le client n'existe pas. Enregistrement dans la BDD" !);
    var idCompte = this.createRawId();
    compte.id = idCompte;
    compte.dateCreation = this.dateNowToString();
    var bdd = this.firestoreComptes.doc<CompteInterface>(idCompte);
    return bdd.set(compte).then((sucess) => {return true}, (rejected) => {return false});
  }
  //#endregion

  /**
   * @public @async
   * @method Modifie le compte demandé en fonction des données en paramètre
   * @param compte Le compte a sauvegardé dans le cloud Firestore
   * @return Une promesse de booleen qui indiquera si l'enregistrement
   * c'est correctement effectué ou non
   * @since 28/01/2021
   * @todo Envoyer les informations au serveur JAVA quand le compte a été validé par un personnel
   */
  public async updateClient(query:QueryCompte, compte: CompteInterface): Promise<void>{
    var oldCompte;
    if(query.id !== null){ await (this.firestoreComptes.doc(query.id).update(compte)); }
    else
    {
      oldCompte = await (this.getdbClient(query));
      if(oldCompte !== null){await (this.firestoreComptes.doc(query.id).update(compte));}
      //else{return false;}
    }
    //return this.updateSucceed(query.id, compte);
  }

  public async deletedbClient(query: QueryCompte) {
    if(query?.id) {
      await (this.firestoreComptes.doc(query.id).delete())
    }
  }

  /**
   * @public @async
   * @method Recupère les données d'un compte dans la base de données 
   * @param query La requete de récupération du compte
   * @
   * @returns Null si aucune valeur correspondante est trouvée
   * sinon renvoie le compte correspondant au mieux à la requête
   */
  public async getdbClient(query: QueryCompte): Promise<CompteInterface>{
  //#region Ancienne Implementation
    /*var result: Compte;
      var collection = (await this.firestoreComptes.ref.get()).docs;
      collection.forEach(compte => {
      if(compte.get('id') === query.id || compte.get('email') === query.mail){
        result = 
        {
          id: compte.get('id'),prenom: compte.get('prenom'),nom: compte.get('nom'),
          mail: compte.get('mail'),telephone: compte.get('telephone'),profilePicture: compte.get('profilePicture'),
          verified: compte.get('verified')
        };
        return;
      }
    });
    return result;*/
  //#endregion    
  //#region Implémentation actuelle
    const listCli = await this.getdbListClient(query);
    return listCli[0];
     //?. retourne null si Compte[] est null 
  //#endregion
  }

  public async getdbListClient(query: QueryCompte): Promise<CompteInterface[]>{
    if(query.id != null) //Separer car identifiants unique
    {
      return this.firestoreComptes.ref.doc(query.id).get().then((document) =>{
        if(document.exists){return [document.data()];}
        else{ return null; }
      });
    }
    else if(query.mail != null) //Separer car identifiants unique
    {
      var mailResult = this.firestoreComptes.ref.where('mail', '==', query.mail);
      return mailResult.get().then((documents) => {
        return [documents?.docs[0]?.data()];
      });
    }
    else //Pour les autres critères de recherches
    {      
      var result = null;
      if(query?.dateCreation){result = this.firestoreComptes.ref.where('dateCreation', '==', query.dateCreation);}
      if(query?.telephone){ console.log(query);result = this.firestoreComptes.ref.where('telephone', '==', query.telephone);}
      if(query?.nom){result = this.firestoreComptes.ref.where('prenom', '==', query.nom);}
      if(query?.prenom){result = this.firestoreComptes.ref.where('nom', '==', query.prenom);}
      if(query?.verified != undefined){result = this.firestoreComptes.ref.where('verified', '==', query.verified);}
      
      
      return result?.get().then((documents) => { //?. permet de retourner null si result == null
        if(documents.size >= 1){
          var resultat: CompteInterface[] = [];
          documents.docs.forEach((document) => {
            resultat.push(document.data());
          });
          return resultat;
        }
        else{return null;}
      });
    }
  }

  /**************FUNCTION OUTILS POUR BDD******************/
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

  private createRawId(): string{
    var id: string = Guid.create().toString();
    return id.replace('-', '');
  }

  private async updateSucceed(idCompte: string, modif: CompteInterface): Promise<boolean>{
    var result = false;
    var bddCompte = await (this.getdbClient({id: idCompte, mail: null}));
    //TODO Un jour..........
    return result;
  }
}
                                                                                                                    