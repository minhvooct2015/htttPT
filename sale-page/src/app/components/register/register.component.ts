import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { faAsterisk } from '@fortawesome/free-solid-svg-icons'; // Import FontAwesome icon
import { Router } from '@angular/router';  // Import the Router service

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
      email: ['', [Validators.required, Validators.email]],
      // sdt: ['', [Validators.required, Validators.pattern('^[0-9]{10,12}$')]],
      sdt: ['', [Validators.required]],
      diaChi: ['', [Validators.required]],
      matKhau: ['', [Validators.required, Validators.minLength(1)]],
      taiKhoan: ['', [Validators.required, Validators.minLength(3)]],
      trangThai: ['ACTIVE', Validators.required],
      vaiTro: ['KHACHHANG', Validators.required],
    });
  }

  onRegister() {
    if (this.registerForm.valid) {
      this.authService.register(this.registerForm.value).subscribe(
        (response) => {
          console.log('Registration successful!', response);
          // redirect only add in constructor and here
          this.router.navigate(['/login']);
        },
        (error) => {
          this.errorMessage = 'Registration failed. Please try again.';
          this.errorMessage = error.message;
          console.log(error)
        }
      );
    }
  }
}
