import { Component, Input } from '@angular/core';
import {AdminService} from "../../../services/admin.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-top-bar',
  templateUrl: './topbar.component.html',
  styleUrls: ['./topbar.component.css']
})
export class TopBarComponent {
 userName: string = "Hello - " + localStorage.getItem("userName") + "-" + localStorage.getItem("userNumber"); // User's name
   appName: string = 'App name'; // Application name
  isLoginedUser: boolean = false;
  constructor(private adminService: AdminService, private router: Router) {}
  onLogout(): void {
    // Clear user-related data from localStorage
    this.adminService.onLogout()

    // Navigate to the login page
    this.router.navigate(['/login']);
  }

  ngOninit() {
    this.isLoginedUser = this.adminService.isLoginedUser();
  }
}
