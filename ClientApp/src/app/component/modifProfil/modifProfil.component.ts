import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { AuthService } from 'src/app/service/auth.service';
import { AppRoutingEnum } from './../../app-routing.enum';

@Component({
  selector: 'app-modifProfil',
  templateUrl: './modifProfil.component.html',
  styleUrls: ['./modifProfil.component.scss']
})
export class ModifProfilComponent implements OnInit {

modification:boolean;
AppRoutingEnum = AppRoutingEnum;

  constructor(public authService: AuthService) { 
    this.modification=false;
  }

  ngOnInit(): void {
  }
  emailFormControl = new FormControl('', [
    Validators.required,
    Validators.email,
  ]);

  modificationInfos(){
    this.modification=!this.modification;
  }

  annulerModif(){

  }

  enregistrerModif(){
    
  }
}
