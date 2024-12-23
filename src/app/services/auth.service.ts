import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {catchError, Observable, throwError} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  // private apiUrl = 'http://localhost:3000/api/login'; // Replace with your backend URL.
loginPath= "http://192.168.1.89:9000"
  constructor(private http: HttpClient) {}

  login(credentials: { username: string; password: string }): Observable<any> {
    const apiUrl = this.loginPath +'/api/login'; // Replace with your backend URL.
    const payload = {
      taiKhoan: credentials.username,
      matKhau: credentials.password
    };
    return this.http.post(apiUrl, payload);
  }


  register(userDetails: {
    hoTen: string;
    email: string;
    sdt: string;
    diaChi: string;
    matKhau: string;
    taiKhoan: string;
    trangThai: string;
    vaiTro: string;
  }): Observable<any> {
    const apiUrl =  this.loginPath + '/api/register'; // Replace with your backend URL.
    return this.http.post(apiUrl, userDetails).pipe(
      catchError(this.handleError) // Catch errors here and handle them
    );

  }

  private handleError(error: any): Observable<never> {
    let errorMessage = 'An unknown error occurred!';

    // Check if the error contains a message from the backend
    if (error.error && error.error.error) {
      // Assuming the backend returns an object with an 'error' field
      errorMessage = error.error.error;
    } else if (error.error && error.error.message) {
      // Check if there's a 'message' field in the error response
      errorMessage = error.error.message;
    } else if (error.status === 400 && error.error) {
      errorMessage = `Error: ${error.error}`;  // If the backend returns plain text
    }
    else if ( error.error) {
      errorMessage = `Error: ${error.error}`;  // If the backend returns plain text
    }

    console.error('Error:', errorMessage);
    return throwError(() => new Error(errorMessage));
  }
}
