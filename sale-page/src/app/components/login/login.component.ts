import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { jwtDecode } from "jwt-decode";
import {Router} from "@angular/router";



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm: FormGroup;
  errorMessage: string = '';
  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) {
    this.loginForm = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(3)]], // Updated field
      password: ['', [Validators.required, Validators.minLength(6)]],
    });
  }

  onLogin() {
    if (this.loginForm.valid)
      localStorage.clear();{
      this.authService.login(this.loginForm.value).subscribe(
        (response) => {
          console.log('Login successful!', response);
          localStorage.setItem('token', response.token)
          /// Decode the token and extract user information
          const decodedToken: any = jwtDecode(response.token);
          let tentk = decodedToken.upn;  // Username (user principal name)
          let userNumber = decodedToken.userNumber;  // User account (userNumber)
          let userName = decodedToken.tenUser
          let groups = decodedToken.groups || []
          // Store user information in the UserService for reuse
        localStorage.setItem("userName", userName)
          localStorage.setItem("userNumber", userNumber)
          localStorage.setItem("tentk", tentk)
          console.log('Decoded User Info:', userName, userNumber, tentk);
          // Redirect based on groups
          if (groups.includes('ADMIN')) {
            this.router.navigate(['admin/sanpham']); // Redirect to /admin
          } else {
            this.router.navigate(['/home']); // Redirect to /sanpham
          }
        },
      (error) => {
          this.errorMessage = 'Invalid credentials. Please try again.';
        }
      );
    }
  }
}
