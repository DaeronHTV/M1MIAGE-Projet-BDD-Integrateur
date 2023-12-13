import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { AngularFireAuth } from '@angular/fire/auth';
import { Injectable } from "@angular/core";
import * as firebase from 'firebase';
import { CompteInterface } from '../interface/firebdd';

@Injectable({
    providedIn: "root",
})
export class AuthService {

user$: Observable<CompteInterface>;
personnel: boolean = false;

    constructor(private afAuth: AngularFireAuth){
      this.user$ = this.afAuth.user.pipe(
        map(user => {
          this.personnel = user?.displayName ? false : true;
          return user?.email 
          ? ({
            mail: user?.email, 
            telephone: user?.phoneNumber,
            profilePicture: user?.photoURL,
            nom: null,
            prenom: null,
            } as CompteInterface)
          :
            null
          ;
        }
            
        )
      );
      this.user$.subscribe(u => console.log(u))
    }

    async createLoginEmail(email: string, mdp: string): Promise<string>{
      let err = await this.afAuth.createUserWithEmailAndPassword(email, mdp).catch(err => err); 
      return err?.message;
    }

    loginEmail(email: string, mdp: string){
      return this.afAuth.signInWithEmailAndPassword(email, mdp);
    }

    loginGoogle() {
      return this.afAuth.signInWithPopup(new firebase.default.auth.GoogleAuthProvider())
    }

    loginTwitter() {
      return this.afAuth.signInWithPopup(new firebase.default.auth.TwitterAuthProvider());
    }  
    
    logout(){
      this.afAuth.signOut();
    }
}