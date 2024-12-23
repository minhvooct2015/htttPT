import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { faAsterisk } from '@fortawesome/free-solid-svg-icons'; // Import FontAwesome icon
import { Router } from '@angular/router';
import { jwtDecode } from "jwt-decode";
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  registerForm: FormGroup;
  errorMessage: string = '';
  faAsterisk = faAsterisk; // FontAwesome asterisk icon

  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) {
    this.registerForm = this.fb.group({
      hoTen: ['', [Validators.required, Validators.minLength(3)]],
      email: ['', [Validators.required]],
      // sdt: ['', [Validators.required, Validators.pattern('^[0-9]{10,12}$')]],
      sdt: ['', [Validators.required]],
      diaChi: ['', [Validators.required]],
      matKhau: ['', [Validators.required, Validators.minLength(1)]],
      taiKhoan: ['', [Validators.required, Validators.minLength(3)]]
    });
  }

  onRegister() {
    console.log("start register");
    // if (this.registerForm.valid) {
      console.log("register")
      this.authService.register(this.registerForm.value).subscribe(
        (response) => {
          console.log('Registration successful!', response);
          // redirect only add in constructor and here
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
          // this.router.navigate(['/login']);
        },
        (error) => {
          this.errorMessage = 'Registration failed. Please try again.';
          this.errorMessage = error.message;
          console.log(error)
        }
      );
    // }
  }
}
