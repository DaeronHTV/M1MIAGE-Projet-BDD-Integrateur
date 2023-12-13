import { Injectable } from '@angular/core';
import { AngularFireAuth } from '@angular/fire/auth';
import * as firebase from 'firebase/app';
import { BehaviorSubject, Subscription } from 'rxjs';
import { FireBddService } from './fire-bdd.service';


@Injectable({
  providedIn: 'root'
})
export class LoginService {
  isConnected: BehaviorSubject<boolean> = new BehaviorSubject(false);
  profilePicture: string;
  userSubscription: Subscription;

  constructor(private auth: AngularFireAuth, private bdd: FireBddService) { }

  public authentificate(){
    this.userSubscription = this.auth.user.subscribe( async (u: firebase.default.User) => {
      // On contacte le serveur métier pour l'informer si un nouvel utilisateur existe :
      if (u !== null) {
        this.profilePicture = u.photoURL;
        console.log('L’utilisateur Firebase est ', u.displayName);
        // On broadcast l'evenement quand l'utilisateur est connecté
        this.isConnected.next(true);
      }
    });
  }

  async logOut() {
    this.auth.signOut().then( () => {
      console.log('Sign-out successful');
      this.userSubscription.unsubscribe();
      this.isConnected.next(false);
    });
  }

  loginInGoogle() {
    const provider = new firebase.default.auth.GoogleAuthProvider();
    this.auth.signInWithPopup(provider);
    this.isConnected.next(true);
  }
}
