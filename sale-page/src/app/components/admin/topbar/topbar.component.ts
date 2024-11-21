import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-top-bar',
  templateUrl: './topbar.component.html',
  styleUrls: ['./topbar.component.css']
})
export class TopBarComponent {
 userName: string = 'User name -'; // User's name
   appName: string = 'App name'; // Application name
}
