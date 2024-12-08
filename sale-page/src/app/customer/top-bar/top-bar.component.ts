import { Component } from '@angular/core';
import {AdminService} from "../../services/admin.service";
import {jwtDecode} from "jwt-decode";
import {UserInfor} from "../../components/admin/sanpham.model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-top-bar-cus',
  templateUrl: './top-bar.component.html',
  styleUrls: ['./top-bar.component.css']
})
export class TopBarComponentCus {
  isUserLoggedIn: boolean = false; // Set to true when user logs in
  userInfor: UserInfor = {
    userNumber: '', // Initialize with an empty string
    userName: '', // Initialize with an empty string
    role: '' ,
    tenTK: ''// Initialize with an empty string
  };

  ngOnInit() {
    const token = localStorage.getItem('token');
    this.userInfor = this.adminService.getUserInfor(token)
    if (token && this.isTokenValid(token)) {
      this.isUserLoggedIn = true;
    }

    // else {
    //   this.isUserLoggedIn = false;
    //   localStorage.removeItem('token'); // Clear invalid token
    // }
  }

  onLogout(): void {
    // Clear user-related data from localStorage
    this.adminService.onLogout()

    // Navigate to the login page
    this.router.navigate(['/login']);
  }

  isTokenValid(token: string): boolean {
    try {
      const decoded: any = jwtDecode(token);
      const now = Math.floor(Date.now() / 1000); // Current time in seconds
      return decoded.exp > now; // Check if the token is still valid
    } catch (error) {
      console.error('Invalid token:', error);
      return false;
    }
  }

  constructor(private adminService: AdminService, private router: Router) {
    // Simulate user login check (replace with actual authentication logic)
    // this.isUserLoggedIn = !!localStorage.getItem('token');
  }

  getUserInfor(): any{

  }
}
