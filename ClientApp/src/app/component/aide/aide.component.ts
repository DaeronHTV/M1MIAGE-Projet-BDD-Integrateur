import { Component, OnInit } from '@angular/core';
import { AppRoutingEnum } from './../../app-routing.enum';

@Component({
  selector: 'app-aide',
  templateUrl: './aide.component.html',
  styleUrls: ['./aide.component.scss']
})
export class AideComponent implements OnInit {

  AppRoutingEnum = AppRoutingEnum;

  constructor() { }

  ngOnInit(): void {
  }

}
