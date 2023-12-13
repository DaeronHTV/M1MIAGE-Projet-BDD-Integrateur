import { Component } from '@angular/core';
import { AppRoutingEnum } from './app-routing.enum';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  AppRoutingEnum = AppRoutingEnum;
  title = 'ClientApp';
}
